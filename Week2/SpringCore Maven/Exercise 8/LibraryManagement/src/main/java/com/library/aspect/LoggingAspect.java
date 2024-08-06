package com.library.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* com.library.service.*.*(..))")
    public void logBeforeMethodExecution(JoinPoint joinPoint) {
        System.out.println("Before executing method: " + joinPoint.getSignature());
    }

    @After("execution(* com.library.service.*.*(..))")
    public void logAfterMethodExecution(JoinPoint joinPoint) {
        System.out.println("After executing method: " + joinPoint.getSignature());
    }
}