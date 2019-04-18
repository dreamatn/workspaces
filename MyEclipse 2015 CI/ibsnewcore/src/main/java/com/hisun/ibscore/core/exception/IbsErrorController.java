package com.hisun.ibscore.core.exception;

import com.hisun.ibscore.core.annotation.IbsRequestBody;
import com.hisun.ibscore.core.annotation.IbsResponseBody;
import com.hisun.ibscore.core.dto.IbsGwaContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
@IbsResponseBody
@EnableConfigurationProperties({ServerProperties.class})
public class IbsErrorController implements ErrorController {

    private ErrorAttributes errorAttributes;
    private static final String PATH = "/error";

    @Autowired
    private ServerProperties serverProperties;

    /**
     * 初始化ExceptionController
     * @param errorAttributes ErrorAttributes
     */
    @Autowired
    public IbsErrorController(ErrorAttributes errorAttributes) {
        Assert.notNull(errorAttributes, "ErrorAttributes must not be null");
        this.errorAttributes = errorAttributes;
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }

    @RequestMapping(value = PATH)
    private IbsGwaContext httpErrorProcess(@IbsRequestBody IbsGwaContext ibsGwaContext, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> httpErrorDesc = getErrorAttributes(request,
                isIncludeStackTrace(request));
        ibsGwaContext.setMsgType("E");
        ibsGwaContext.setMsgId("SC9999");
        ibsGwaContext.getOutpArea().put("httpErrorDesc",httpErrorDesc);
        System.out.print("---debug : IbsErrorController.error ibsGwaContext \n" +
                ibsGwaContext.toString()  +
                "\n");

        return ibsGwaContext;
    }

    /**
     * Determine if the stacktrace attribute should be included.
     * @param request the source request
     * @return if the stacktrace attribute should be included
     */
    private boolean isIncludeStackTrace(HttpServletRequest request) {
        ErrorProperties.IncludeStacktrace include = this.serverProperties.getError().getIncludeStacktrace();
        if (include == ErrorProperties.IncludeStacktrace.ALWAYS) {
            return true;
        }
        if (include == ErrorProperties.IncludeStacktrace.ON_TRACE_PARAM) {
            return getTraceParameter(request);
        }
        return false;
    }

    /**
     * 是否包含trace
     * @param request HttpServletRequest
     * @return boolean
     */
    private boolean getTraceParameter(HttpServletRequest request) {
        String parameter = request.getParameter("trace");
        if (parameter == null) {
            return false;
        }
        return !"false".equals(parameter.toLowerCase());
    }

    /**
     * 获取错误编码
     * @param request HttpServletRequest
     * @return HttpStatus
     */
    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request
                .getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        try {
            return HttpStatus.valueOf(statusCode);
        }
        catch (Exception ex) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    /**
     * 获取错误的信息
     * @param request HttpServletRequest
     * @param includeStackTrace boolean
     * @return Map<String, Object>
     */
    private Map<String, Object> getErrorAttributes(HttpServletRequest request, boolean includeStackTrace) {
        WebRequest webRequest = new ServletWebRequest(request);
        return this.errorAttributes.getErrorAttributes(webRequest, includeStackTrace);
    }

}
