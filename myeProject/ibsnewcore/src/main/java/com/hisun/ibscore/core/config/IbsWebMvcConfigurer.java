package com.hisun.ibscore.core.config;

import com.hisun.ibscore.core.exception.IbsErrorIntercepter;
import com.hisun.ibscore.core.handler.IbsRequestResponseBodyMethodProcessor;
import com.hisun.ibscore.core.intecpter.CheckLoginIntercepter;
import com.hisun.ibscore.core.intecpter.CheckTiaIntercepter;
import com.hisun.ibscore.core.intecpter.ReciveTiaIntercepter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
class IbsWebMvcConfigurer implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ReciveTiaIntercepter()).addPathPatterns("/ibscore/**");
        registry.addInterceptor(new CheckLoginIntercepter()).addPathPatterns("/ibscore/**");
        registry.addInterceptor(new CheckTiaIntercepter()).addPathPatterns("/ibscore/**");
        registry.addInterceptor(new IbsErrorIntercepter()).addPathPatterns("/error/**");
        WebMvcConfigurer.super.addInterceptors(registry);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new IbsRequestResponseBodyMethodProcessor());
        WebMvcConfigurer.super.addArgumentResolvers(resolvers);
    }

    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> handlers) {
        handlers.add(new IbsRequestResponseBodyMethodProcessor());
        WebMvcConfigurer.super.addReturnValueHandlers(handlers);
    }



}