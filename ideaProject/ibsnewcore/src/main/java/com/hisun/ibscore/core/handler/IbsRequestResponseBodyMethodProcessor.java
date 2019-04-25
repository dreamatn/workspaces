package com.hisun.ibscore.core.handler;

import com.hisun.ibscore.core.annotation.IbsRequestBody;
import com.hisun.ibscore.core.annotation.IbsResponseBody;
import com.hisun.ibscore.core.constant.IbsMsgConstant;
import com.hisun.ibscore.core.dto.IbsGwaContext;
import com.hisun.ibscore.core.dto.IbsTiaRequest;
import com.hisun.ibscore.core.dto.IbsToaResponse;
import com.hisun.ibscore.core.utils.IbsTool;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.http.MediaType;
import org.springframework.http.converter.GenericHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Type;

import static org.springframework.web.context.request.RequestAttributes.SCOPE_REQUEST;


public class IbsRequestResponseBodyMethodProcessor implements HandlerMethodArgumentResolver,HandlerMethodReturnValueHandler {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        System.out.print("---debug : IbsRequestResponseBodyMethodProcessor.supportsParameter \n");

        return (AnnotatedElementUtils.hasAnnotation(parameter.getContainingClass(), IbsRequestBody.class) ||
                parameter.hasParameterAnnotation(IbsRequestBody.class) ||
                parameter.hasMethodAnnotation(IbsRequestBody.class)) &&
                (IbsGwaContext.class.isAssignableFrom(parameter.getParameterType()));

    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        System.out.print("---debug : IbsRequestResponseBodyMethodProcessor.resolveArgument \n");

        //Tia转Gwa
        if (webRequest.getAttribute("ibsTiaRequest",SCOPE_REQUEST) == null){
            IbsTool.IbsExcp(IbsMsgConstant.M_SC_TIA_IS_NULL);
        }else {
            webRequest.setAttribute("ibsGwaContext",
                    IbsTool.TiaToGwa((IbsTiaRequest) webRequest.getAttribute("ibsTiaRequest",SCOPE_REQUEST)),
                    SCOPE_REQUEST);
        }

        System.out.print("---debug : IbsRequestResponseBodyMethodProcessor.resolveArgument ibsGwaContext \n" +
                webRequest.getAttribute("ibsGwaContext",SCOPE_REQUEST).toString()  +
                "\n");

        return  webRequest.getAttribute("ibsGwaContext",SCOPE_REQUEST);
    }

    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        System.out.print("---debug : IbsRequestResponseBodyMethodProcessor.supportsReturnType  \n");
        boolean flg =(AnnotatedElementUtils.hasAnnotation(returnType.getContainingClass(), IbsResponseBody.class) ||
                returnType.hasMethodAnnotation(IbsResponseBody.class) ||
                returnType.hasParameterAnnotation(IbsResponseBody.class)) &&
                (IbsGwaContext.class.isAssignableFrom(returnType.getParameterType()));
        System.out.print("---debug : IbsRequestResponseBodyMethodProcessor.supportsReturnType flg \n" +
                flg  +
                "\n");
        return (AnnotatedElementUtils.hasAnnotation(returnType.getContainingClass(), IbsResponseBody.class) ||
                returnType.hasMethodAnnotation(IbsResponseBody.class) ||
                returnType.hasParameterAnnotation(IbsResponseBody.class)) &&
                (IbsGwaContext.class.isAssignableFrom(returnType.getParameterType()));
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception {
        try {
            System.out.print("---debug : IbsRequestResponseBodyMethodProcessor.handleReturnValue \n");
            mavContainer.setRequestHandled(true);
            //test
//            int b=1/0;

            //GWA转TOA
            webRequest.setAttribute("ibsToaResponse",
                    IbsTool.GwaToToa((IbsTiaRequest) webRequest.getAttribute("ibsTiaRequest",SCOPE_REQUEST), (IbsGwaContext) returnValue),
                    SCOPE_REQUEST);

            System.out.print("---debug : IbsRequestResponseBodyMethodProcessor.handleReturnValue ibsToaResponse " +
                    webRequest.getAttribute("ibsToaResponse",SCOPE_REQUEST).toString() +
                    "\n");

        }catch (Exception e){
            e.printStackTrace();
            IbsToaResponse ibsToaResponse = new IbsToaResponse();
            IbsTool.FormatExceptionToa(e,ibsToaResponse);
            webRequest.setAttribute("ibsToaResponse",ibsToaResponse,SCOPE_REQUEST);
        }
    }


}
