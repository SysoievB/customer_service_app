package com.customer_service_app.aop;

import com.customer_service_app.entity.Customer;
import com.customer_service_app.repo.CustomerJpaRepo;
import com.customer_service_app.service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

import static java.lang.StringTemplate.STR;

@Slf4j
@Aspect
@Component
@AllArgsConstructor
public class AspectLoggingClass {
    private final CustomerJpaRepo customerJpaRepo;

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

    @Around("forAfterReturningAndAroundAdvicePointcut()")
    public Object aroundAdviceUpdateDuringExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("-----------------AROUND ADVICE START--------------------------");
        val args = joinPoint.getArgs();
        args[0] = STR."\{args[0]} THIS STRING WAS CHANGED FROM AROUND ADVICE";
        log.info("-----------------AROUND ADVICE END--------------------------");
        return joinPoint.proceed(args);
    }

    @AfterReturning("forAfterReturningAndAroundAdvicePointcut()")
    public Object afterReturningAdvice(JoinPoint joinPoint) {
        log.info("-----------------AFTER RETURNING ADVICE START--------------------------");

        val methodSignature = (MethodSignature) joinPoint.getSignature();
        log.info(STR."Method: \{joinPoint.getSignature().getName()}");
        log.info(STR."Arguments: \{Arrays.toString(joinPoint.getArgs())}");
        log.info(STR."ClassName: \{joinPoint.getTarget().getClass().getName()}");
        log.info(STR."ReturnType: \{methodSignature.getReturnType()}");
        log.info(STR."AnnotatedSuperclass: \{methodSignature.getReturnType().getAnnotatedSuperclass()}");

        log.info("-----------------AFTER RETURNING ADVICE END--------------------------");
        return joinPoint;
    }

    @AfterReturning(value = "execution(* setCountryToEachCustomer(..))", returning = "customers")
    public void afterReturningAdviceUpdateDuringExecution(JoinPoint joinPoint, List<Customer> customers) {
        log.info("-----------------AFTER RETURNING ADVICE START--------------------------");

        val args = joinPoint.getArgs();
        val updated = customers
                .stream()
                .peek(customer -> customer.setCountry((String) args[0]))
                .map(customerJpaRepo::save)
                .toList();

        updated.forEach(customer -> System.out.println(customer.toString()));

        log.info("-----------------AFTER RETURNING ADVICE END--------------------------");
    }

    @Pointcut("execution(* save(..))")
    private void forSavePointcut() {
    }

    @Pointcut("execution(* forAroundAdvice(..))")
    private void forAfterReturningAndAroundAdvicePointcut() {
    }
}
