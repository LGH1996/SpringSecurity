package com.lgh.client;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyAop {
    @Pointcut("execution(public * org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.*(..))")
    public void attemptAuthentication() {
    }

    @Before("attemptAuthentication()")
    public void myAttemptAuthentication(){
        System.out.println("aaaaaaaaaaaa");
    }
}
