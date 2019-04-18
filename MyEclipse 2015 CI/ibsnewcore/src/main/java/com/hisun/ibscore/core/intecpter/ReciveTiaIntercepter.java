package com.hisun.ibscore.core.intecpter;

import com.hisun.ibscore.core.config.IbsCoreConfigurer;
import com.hisun.ibscore.core.dto.IbsTiaRequest;
import com.hisun.ibscore.core.dto.IbsToaResponse;
import com.hisun.ibscore.core.utils.IbsTool;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReciveTiaIntercepter implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        System.out.print("---debug : ReciveTiaIntercepter.preHandle \n");

        request.setAttribute("ibsTiaRequest",IbsTool.bodyToTia(request));

        IbsCoreConfigurer ibsCoreConfigurer = new IbsCoreConfigurer();
        System.out.print("---debug : ReciveTiaIntercepter.preHandle ibsCoreConfigurer Version \n" +
                        ibsCoreConfigurer.getVersion()  +
                        "\n");

        System.out.print("---debug : ReciveTiaIntercepter.preHandle ibsTiaRequest \n" +
                request.getAttribute("ibsTiaRequest").toString()  +
                "\n");

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        try {
            System.out.print("---debug : ReciveTiaIntercepter.afterCompletion \n");

            if(ex != null){
                throw new Exception(ex);
            }
//            int a=1/0;
            IbsToaResponse ibsToaResponse = null;
            if(request.getAttribute("ibsToaResponse") == null){
//            if(ibsToaResponse == null){
                throw new Exception();

            }else {
                ibsToaResponse = (IbsToaResponse)request.getAttribute("ibsToaResponse");
            }
            IbsTool.toaToBody(request,response,ibsToaResponse);
        }catch (Exception e){
//            e.printStackTrace();
            IbsToaResponse ibsToaResponse = new IbsToaResponse();
            IbsTool.FormatExceptionToa(e,ibsToaResponse);
            IbsTool.toaToBody(request,response,ibsToaResponse);
        }

//        if(ex != null){
//            System.out.print("---debug : ReciveTiaIntercepter.afterCompletion ibsTiaRequest \n" +
//                    ex.getMessage() +
//                    "\n");
//            IbsTool.toaToBody(request,response);
//            ex = null;
//        }
    }
}
