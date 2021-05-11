package ru.kpfu.itis.transportsem.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Pointcut("execution(public * ru.kpfu.itis.transportsem.services.ReservationsServiceImpl.*(..))")
    public void reservationsServicePointcut(){}


    @Before("reservationsServicePointcut()")
    public void beforeAop(JoinPoint joinPoint){
        System.out.println("Before " + joinPoint.getSignature().getDeclaringType()
        + "." + joinPoint.getSignature().getName());
    }
}
