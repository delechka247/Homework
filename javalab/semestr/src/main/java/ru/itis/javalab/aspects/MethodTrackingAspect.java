package ru.itis.javalab.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.javalab.dto.UsedMethodDto;
import ru.itis.javalab.services.UsedMethodsService;

@Aspect
@Component
public class MethodTrackingAspect {

    UsedMethodsService usedMethodsService;

    public MethodTrackingAspect(UsedMethodsService usedMethodsService){
        this.usedMethodsService = usedMethodsService;
    }

    @Around(value = "execution(* ru.itis.javalab.contollers.*.*(..))")
    public Object methodsTimeLogging(ProceedingJoinPoint joinPoint) throws Throwable {
        long before = System.currentTimeMillis();
        Object responseEntity = joinPoint.proceed();
        joinPoint.proceed();
        long after = System.currentTimeMillis();
        System.out.println("Class: " + joinPoint.getTarget().getClass().getName());
        System.out.println("Method: " + joinPoint.getSignature().getName());
        System.out.println("Time: " + (after - before));

        UsedMethodDto usedMethodDto = UsedMethodDto.builder()
                .name(joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName())
                .time(after - before)
                .build();
        usedMethodsService.addUsedMethod(usedMethodDto);

        return responseEntity;
    }

}
