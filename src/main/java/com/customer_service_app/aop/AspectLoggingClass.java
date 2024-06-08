package com.customer_service_app.aop;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import static java.lang.StringTemplate.STR;

@Slf4j
@Aspect
@Component
public class AspectLoggingClass {

    @Before("forSavePointcut()")
    public void beforeSaveAdvice() {
        log.info("-----------------BEFORE ADVICE--------------------------");
    }

    @After("forSavePointcut()")
    public void afterSaveAdvice() {
        log.info("-----------------AFTER ADVICE--------------------------");
    }

    @AfterReturning("execution(* findAll())")
    public void afterReturningFindAllAdvice() {
        log.info("-----------------AFTER RETURNING ADVICE--------------------------");
    }

    @AfterThrowing("execution(* findBy*(Long))")
    public void afterThrowingFindAllByCountryAdvice() {
        log.info("-----------------AFTER THROWING ADVICE--------------------------");
    }

    @Around("execution(public String com.customer_service_app.service.CustomerService.forAroundAdvice(String))")
    public Object aroundAdviceUpdateDuringExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("-----------------AROUND ADVICE START--------------------------");
        val args = joinPoint.getArgs();
        args[0] = STR."\{args[0]} THIS STRING WAS CHANGED FROM AROUND ADVICE";
        log.info("-----------------AROUND ADVICE END--------------------------");
        return joinPoint.proceed(args);
    }

    @Pointcut("execution(* save(..))")
    private void forSavePointcut() {
    }
}
