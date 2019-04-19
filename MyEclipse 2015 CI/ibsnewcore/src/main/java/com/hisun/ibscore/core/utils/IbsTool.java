package com.hisun.ibscore.core.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.hisun.ibscore.core.config.IbsCoreConfigurer;
import com.hisun.ibscore.core.dto.IbsGwaContext;
import com.hisun.ibscore.core.dto.IbsTiaRequest;
import com.hisun.ibscore.core.dto.IbsToaResponse;
import com.hisun.ibscore.core.exception.IbsException;
import com.hisun.ibscore.core.service.IbsExceptionService;
import com.hisun.ibscore.core.service.impl.IbsExceptionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.GenericHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;

@Component
public class IbsTool {

    @Autowired
    private IbsCoreConfigurer ibsCoreConfigurer;

    public static void IbsExcp(String msgId) throws Exception{
        IbsExceptionService ibsExceptionService = new IbsExceptionServiceImpl();
        ibsExceptionService.process(msgId);
    }

    public static IbsTool ibsTool;

    @PostConstruct
    public void init(){
        ibsTool = this;
        ibsTool.ibsCoreConfigurer = this.ibsCoreConfigurer;
    }

    /**
     * JSON格式字符串转换为Tia
     *
     * @param strJSON 字符串
     * @return JSON数据转换后的IbsTiaRequest
     * @throws Exception
     */
    public static IbsTiaRequest jsonToTia(String strJSON) throws Exception {
        ObjectMapper dataMapper = new ObjectMapper();
        IbsTiaRequest data = dataMapper.readValue(strJSON, IbsTiaRequest.class);
        return data;
    }

//    /**
//     * JSON格式字符串转换为JavaBean
//     *
//     * @param webRequest NativeWebRequest
//     * @return JSON数据转换IbsTiaRequest
//     * @throws Exception
//     */
//    public static IbsTiaRequest bodyToTia(NativeWebRequest webRequest) throws Exception {
//        String ibsHttpHeaderType = webRequest.getHeader("Content-Type");
//        String strJSON = bodyToString(webRequest);
//        IbsTiaRequest ibsTiaRequest = new IbsTiaRequest();
//
//        if(("application/json").equals(ibsHttpHeaderType)){
//            ObjectMapper objectMapper = new ObjectMapper();
//            ibsTiaRequest = objectMapper.readValue(strJSON, IbsTiaRequest.class);
//            ibsTiaRequest.setVersionNo(ibsTiaRequest.getVersionNo() + " json格式");
//        } else if(("application/xml").equals(ibsHttpHeaderType) || ("text/xml").equals(ibsHttpHeaderType)){
//            XmlMapper xmlMapper = new XmlMapper();
//            ibsTiaRequest = xmlMapper.readValue(strJSON,IbsTiaRequest.class);
//            ibsTiaRequest.setVersionNo(ibsTiaRequest.getVersionNo() + " xml格式");
//        } else if(("text/plain").equals(ibsHttpHeaderType)){
//            ibsTiaRequest.setVersionNo("old cobol format");
//        } else {
//            ibsTiaRequest.setVersionNo("err format");
//        }
//
//        return ibsTiaRequest;
//}
    /**
     * HTTP body 字符串转换为Tia
     *
     * @param servletRequest ServletRequest
     * @return 字符串转换后的IbsTiaRequest
     * @throws Exception
     */
    public static IbsTiaRequest bodyToTia(ServletRequest servletRequest) throws Exception {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        String ibsHttpHeaderType = request.getHeader("Content-Type");
        String strJSON = bodyToString(request);
        IbsTiaRequest ibsTiaRequest = new IbsTiaRequest();

        if(("application/json").equals(ibsHttpHeaderType)){
            ObjectMapper objectMapper = new ObjectMapper();
            ibsTiaRequest = objectMapper.readValue(strJSON, IbsTiaRequest.class);
            ibsTiaRequest.setVersionNo(ibsTool.ibsCoreConfigurer.getVersion() + " json格式");
        } else if(("application/xml").equals(ibsHttpHeaderType) || ("text/xml").equals(ibsHttpHeaderType)){
            XmlMapper xmlMapper = new XmlMapper();
            ibsTiaRequest = xmlMapper.readValue(strJSON,IbsTiaRequest.class);
            ibsTiaRequest.setVersionNo(ibsTool.ibsCoreConfigurer.getVersion() + " xml格式");
        } else if(("text/plain").equals(ibsHttpHeaderType)){
            ibsTiaRequest.setVersionNo("old cobol format");
        } else {
            ibsTiaRequest.setVersionNo("err format");
        }

        return ibsTiaRequest;
    }

    /**
     * HTTP body 字符串转换为Tia
     *
     * @param webRequest NativeWebRequest
     * @param ibsToaResponse IbsToaResponse
     * @param returnType MethodParameter
     * @return 字符串转换后的IbsTiaRequest
     * @throws Exception
     */
    public static void toaToBody(NativeWebRequest webRequest,IbsToaResponse ibsToaResponse,MethodParameter returnType) throws Exception {
        writeWithMessageConverters(ibsToaResponse,
                new ServletServerHttpRequest(webRequest.getNativeRequest(HttpServletRequest.class)),
                new ServletServerHttpResponse(webRequest.getNativeResponse(HttpServletResponse.class)));

    }

    public static void toaToBody(HttpServletRequest request,HttpServletResponse response,IbsToaResponse ibsToaResponse) throws Exception {
        if (request.getAttribute("javax.servlet.error.status_code") == null) {
            ibsToaResponse.setHttpCode(200);
        }else {
            ibsToaResponse.setHttpCode((Integer) request.getAttribute("javax.servlet.error.status_code"));
        }
        writeWithMessageConverters(ibsToaResponse,
                new ServletServerHttpRequest(request),
                new ServletServerHttpResponse(response));

    }

    public static void FormatExceptionToa(Exception e,IbsToaResponse ibsToaResponse) throws Exception {
        e.printStackTrace();
        ibsToaResponse.setMsgType("E");
        ibsToaResponse.setMsgCode("SC6001");

        StackTraceElement[] traceElements = e.getStackTrace();
        ArrayList<String> errTrace = new ArrayList <String>();
        for (StackTraceElement traceElement : traceElements){
            if(traceElement.toString().contains("com.hisun")) {
                errTrace.add(traceElement.toString());
            }
        }

        errTrace.add(0,e.getMessage());
        ibsToaResponse.getOutpData().put("errDesc",errTrace);


    }

    /**
     * Tia转换至Gwa
     *
     * @param ibsTiaRequest NativeWebRequest
     * @return JSON数据转换IbsTiaRequest
     * @throws Exception
     */
    public static IbsGwaContext TiaToGwa(IbsTiaRequest ibsTiaRequest) throws Exception {
        IbsGwaContext ibsGwaContext = new IbsGwaContext();

        //赋值
        ibsGwaContext.setApMmo(ibsTiaRequest.getTrAp());
        ibsGwaContext.setMsgType("N");
        ibsGwaContext.setMsgId("0000");
        ibsGwaContext.setTrId(ibsTiaRequest.getTrId());
        ibsGwaContext.setTrBank(ibsTiaRequest.getTrBank());
        ibsGwaContext.setBrDp(ibsTiaRequest.getTrBr());
        ibsGwaContext.setAwaArea(ibsTiaRequest.getInpData());

        return ibsGwaContext;
    }

    /**
     * Gwa转换至Toa
     *
     * @param ibsTiaRequest NativeWebRequest
     * @return JSON数据转换IbsTiaRequest
     * @throws Exception
     */
    public static IbsToaResponse GwaToToa(IbsTiaRequest ibsTiaRequest,IbsGwaContext ibsGwaContext) throws Exception {
        IbsToaResponse ibsToaResponse = new IbsToaResponse();

        if(ibsTiaRequest != null){
            ibsToaResponse.setVersionNo(ibsTiaRequest.getVersionNo());
            ibsToaResponse.setTrBank(ibsTiaRequest.getTrBank());
            ibsToaResponse.setTrBranch(ibsTiaRequest.getTrBr());
        }
        //赋值
        ibsToaResponse.setMsgType(ibsGwaContext.getMsgType());
        ibsToaResponse.setMsgApMmo(ibsGwaContext.getApMmo());
        ibsToaResponse.setMsgCode(ibsGwaContext.getMsgId());
        ibsToaResponse.setOutpData(ibsGwaContext.getOutpArea());

        return ibsToaResponse;
    }

    /**
     * HttpBody转换为字符串
     *
     * @param request HTTP请求
     * @return HttpBody转换后的字符串
     * @throws Exception
     */
    public static String bodyToString(HttpServletRequest request) throws Exception {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
        InputStream inputStream = request.getInputStream();

        if (inputStream != null) {
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            char[] charBuffer = new char[128];
            int bytesRead = -1;
            while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                stringBuilder.append(charBuffer, 0, bytesRead);
            }
        } else {
            stringBuilder.append("");
        }
        return stringBuilder.toString();
    }

    public static String bodyToString(NativeWebRequest webRequest) throws Exception {
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        return bodyToString(request);

    }

    private static void writeWithMessageConverters(@Nullable Object outputValue,
                                            ServletServerHttpRequest inputMessage, ServletServerHttpResponse outputMessage) throws Exception {

        MediaType selectedMediaType = null;
//        Type dType = new Type() {
//            @Override
//            public String getTypeName() {
//                return null;
//            }
//        };
        Type declaredType = IbsTool.class.getMethod("GwaToToa", IbsTiaRequest.class, IbsGwaContext.class).getGenericReturnType();

        String ibsHttpHeaderType = inputMessage.getHeaders().getContentType().getSubtype();

        if (("json").equals(ibsHttpHeaderType)){
            selectedMediaType = MediaType.parseMediaType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            GenericHttpMessageConverter genericHttpMessageConverter = new MappingJackson2HttpMessageConverter();
            genericHttpMessageConverter.write(outputValue, declaredType, selectedMediaType, outputMessage);
        } else if (("xml").equals(ibsHttpHeaderType)) {
            selectedMediaType = MediaType.parseMediaType(MediaType.APPLICATION_XML_VALUE);
            GenericHttpMessageConverter genericHttpMessageConverter = new MappingJackson2XmlHttpMessageConverter();
            genericHttpMessageConverter.write(outputValue, declaredType, selectedMediaType, outputMessage);
        } else if(("plain").equals(ibsHttpHeaderType)){
            selectedMediaType = MediaType.parseMediaType(MediaType.TEXT_XML_VALUE);
            StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter();
            String outputValueString = "old cobol format";
            stringHttpMessageConverter.write(outputValueString,selectedMediaType,outputMessage);
        } else {
            selectedMediaType = MediaType.parseMediaType(MediaType.TEXT_XML_VALUE);
            StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter();
            String outputValueString = "error MediaType";
            stringHttpMessageConverter.write(outputValueString,selectedMediaType,outputMessage);
        }

    }
}
