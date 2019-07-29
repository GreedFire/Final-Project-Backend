package com.kodilla.backend;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

// AOP
@Aspect
@Component
public class Watcher {
    private static final Logger LOGGER = LoggerFactory.getLogger(Watcher.class);

    @Around("execution(* com.kodilla.backend.controller.*.*(..))")
    private Object logControllerEvent(ProceedingJoinPoint pjp){
        Object result = null;
        LOGGER.info("+++ RUNNING CONTROLLER: " + pjp.getTarget().getClass().getSimpleName() + " USING METHOD: " +
                pjp.getSignature().getName());
        try {
            long start = System.currentTimeMillis();
            result = pjp.proceed();
            long end = System.currentTimeMillis();
            LOGGER.info("+++ Time consumed: " + (end - start) + "[ms]");
        }
        catch (Throwable e){
            LOGGER.error(e.getMessage());
        }
        return result;
    }

}
