package arden.java.kudago.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class LogTimeExecAspect {
    @Pointcut("@annotation(arden.java.kudago.aspect.LogTimeExec) || @within(arden.java.kudago.aspect.LogTimeExec)")
    public void hasAnnotation() {}

    @Around("hasAnnotation()")
    public Object logTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long endTime = System.currentTimeMillis();

        log.info("Time taken: {} ms, method name: {}, class name: {}",
                endTime - startTime,
                joinPoint.getSignature().getName(),
                joinPoint.getSignature().getDeclaringTypeName());

        return proceed;
    }
}
