package com.example.orderonlinepetproject.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.fusesource.jansi.Ansi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component

public class HibernateLoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(HibernateLoggingAspect.class);


    @Around("@within(LogHibernateOperation)")
    public Object logHibernateOperation(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();

        String resString;
        try {
            Object result = joinPoint.proceed();

            resString = String.format("Успешно выполнен метод: %s.%s()", className, methodName);

            logger.info(String.valueOf(Ansi.ansi().fgGreen().a(resString)));

            return result;

        } catch (Exception e) {
            resString = String.format("Ошибка при выполнении метода: %s.%s() - {}", className, methodName, e.getMessage());

            logger.info(String.valueOf(Ansi.ansi().fgGreen().a(resString)));

            throw e;
        }

    }
}
