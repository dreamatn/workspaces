package com.hisun.ibscore.core.intecpter;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckTiaIntercepter implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        System.out.print("---debug : CheckTiaIntercepter.preHandle \n");
        System.out.print("---debug : CheckTiaIntercepter.preHandle ibsMemoryTree \n" +
                request.getServletContext().getAttribute("ibsMemoryTree").toString()  +
                "\n");
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
