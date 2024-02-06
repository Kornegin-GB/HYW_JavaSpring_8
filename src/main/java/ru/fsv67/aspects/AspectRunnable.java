package ru.fsv67.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class AspectRunnable {
    @Pointcut("within(@ru.fsv67.aspects.Time *)")
    public void beansAnnotatedWith() {

    }

    @Pointcut("@annotation(ru.fsv67.aspects.Time)")
    public void methodsAnnotatedWith() {

    }

    @Pointcut("@annotation(ru.fsv67.aspects.RecoverException)")
    public void methodsExceptionWith() {

    }

    @Around("beansAnnotatedWith() || methodsAnnotatedWith()")
    public Object getStringAspect(ProceedingJoinPoint joinPoint) throws Throwable {
        Long start = System.currentTimeMillis();
        joinPoint.proceed();
        Long end = System.currentTimeMillis();
        log.warn(
                "{} - {} # {} секунд",
                joinPoint.getTarget().getClass().getName(),
                joinPoint.getSignature().getName(),
                (end - start) / 1000
        );
        return null;
    }

    @Around("methodsExceptionWith()")
    public Object getStringAspectException(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        RecoverException annotation = signature.getMethod().getAnnotation(RecoverException.class);
        try {
            joinPoint.proceed();
        } catch (Throwable e) {
            for (int i = 0; i < annotation.noRecoverFor().length; i++) {
                if (annotation.noRecoverFor()[i].isAssignableFrom(e.getClass())) {
                    throw e;
                }
            }
            log.warn(e.getClass().getName() + " " + e.getMessage());
        }
        return null;
    }
}
