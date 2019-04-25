package com.hisun.ibscore.core.exception;

import com.hisun.ibscore.core.dto.IbsToaResponse;
import com.hisun.ibscore.core.utils.IbsTool;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IbsErrorIntercepter implements HandlerInterceptor {

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        try {
            System.out.print("---debug : IbsErrorIntercepter.afterCompletion \n");

            if(ex != null){
                throw new Exception(ex);
            }
            IbsToaResponse ibsToaResponse = null;
            if(request.getAttribute("ibsToaResponse") == null){
                throw new Exception();

            }else {
                ibsToaResponse = (IbsToaResponse)request.getAttribute("ibsToaResponse");
            }
            IbsTool.toaToBody(request,response,ibsToaResponse);
        }catch (Exception e){
            IbsToaResponse ibsToaResponse = new IbsToaResponse();
            IbsTool.FormatExceptionToa(e,ibsToaResponse);
            IbsTool.toaToBody(request,response,ibsToaResponse);
        }
    }
}
