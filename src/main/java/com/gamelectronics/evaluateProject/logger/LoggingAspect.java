package com.gamelectronics.evaluateProject.logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

@Aspect
@Configuration
public class LoggingAspect {

    private final Logger logger = MyLogger.loggerRegister();

    @AfterThrowing(pointcut = "within(com.gamelectronics.evaluateProject.business.impl..*)", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
        logger.log(Level.WARNING, "Exception in " +
                joinPoint.getSignature().getDeclaringTypeName() +
                "." +
                joinPoint.getSignature().getName() +
                "() with cause = " +
                e.getMessage());
    }

    @Before("within(com.gamelectronics.evaluateProject.controller.impl..*)||within(com.gamelectronics.evaluateProject.business.impl..*)")
    public void logBefore(JoinPoint joinPoint) {
        logger.info("Enter: " + joinPoint.getSignature().getDeclaringTypeName() + "."
                + joinPoint.getSignature().getName() + "() with argument[s] = " + Arrays.toString(joinPoint.getArgs()));
    }

    @Around("within(com.gamelectronics.evaluateProject.controller.impl..*)||within(com.gamelectronics.evaluateProject.business.impl..*)")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            Object result = joinPoint.proceed();
            logger.log(Level.FINE, "Exit: " + joinPoint.getSignature().getDeclaringTypeName() + "."
                    + joinPoint.getSignature().getName() + "() with result =" + result);
            return result;
        } catch (IllegalArgumentException e) {
            logger.warning("Illegal argument: "
                    + Arrays.toString(joinPoint.getArgs()) + " in "
                    + joinPoint.getSignature().getDeclaringTypeName() + "."
                    + joinPoint.getSignature().getName() + "()");
            throw e;
        }
    }


}
