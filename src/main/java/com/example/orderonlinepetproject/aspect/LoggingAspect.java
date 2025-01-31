package com.example.orderonlinepetproject.aspect;

import org.apache.logging.log4j.simple.SimpleLogger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.fusesource.jansi.Ansi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Around("@within(MyLogFromMethod)")
    public Object   logMethodExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();

        try{
            String resString = String.format("Метод: %s, из класса: %s выполняется.", methodName, className);
            logger.info(String.valueOf(Ansi.ansi().fgGreen().a(resString)));

            Object result = joinPoint.proceed();

            resString = String.format("Метод: %s, из класса: %s завершил выполнение.", methodName, className);

            logger.info(String.valueOf(Ansi.ansi().fgGreen().a(resString)));

            return result;

        }catch (Exception e){
            logger.info("Ошибка при выполнении метода: {}.{}() - {}", className, methodName, e.getMessage());
            throw e;
        }

    }
}
