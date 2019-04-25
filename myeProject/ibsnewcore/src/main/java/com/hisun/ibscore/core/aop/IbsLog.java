package com.hisun.ibscore.core.aop;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class IbsLog {

    @Pointcut("execution(public * com.hisun.ibscore.app.bp.controller.UserController.find*(..))")
    public void log(){}

    @Before("log()")
    public void doBefore(JoinPoint joinPoint){
        System.out.print("--- ibsLog --- info : IbsLog.doBefore log test " +
                joinPoint.toString() +
                "\n\n");
    }
}
