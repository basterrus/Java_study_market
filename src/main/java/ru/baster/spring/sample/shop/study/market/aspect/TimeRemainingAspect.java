package ru.baster.spring.sample.shop.study.market.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Aspect
@Component
class TimeRemainingAspect {

    @Pointcut("@within(ru.baster.spring.sample.shop.study.market.aspect.ResourceNotFoundException)")
    public void annotatedClassPointcut() {

    }

    @Pointcut("@annotation(ru.baster.spring.sample.shop.study.market.aspect.ResourceNotFoundException)")
    public void annotatedMethodPointcut() {

    }

    @Pointcut("annotatedClassPointcut() || annotatedMethodPointcut()")
    public void targetPointcut() {

    }
    @Around("targetPointcut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        try {
            return pjp.proceed();

        } catch (NullPointerException e) {

            long start = System.currentTimeMillis();
            System.out.println(start);

            Class<?> beanClass = pjp.getTarget().getClass();
            String methodName = pjp.getSignature().getName();

            String errorMessage = extractErrorMessage(e, pjp);
            log.error("Во время выполнения {}#{} произошло исключение: {}", beanClass.getName(), methodName, errorMessage);

            long finish = System.currentTimeMillis();
            System.out.println(finish);
            System.out.println(finish - start);
            return null;
        }
    }

    private String extractErrorMessage(NullPointerException e, ProceedingJoinPoint pjp) {
        MethodSignature signature = (MethodSignature) pjp.getSignature();

        ResourceNotFoundException annoOnMethod =
                signature.getMethod().getAnnotation(ResourceNotFoundException.class);

        if (annoOnMethod != null) {
            if (!Objects.equals("null", annoOnMethod.value())) {
                return annoOnMethod.value();
            }
        } else {
            Class<?> beanClass = pjp.getTarget().getClass();
            ResourceNotFoundException annoOnClass =
                    beanClass.getAnnotation(ResourceNotFoundException.class);

            if (annoOnClass != null && !Objects.equals("null", annoOnClass.value())) {
                return annoOnClass.value();
            }
        }

        return e.getMessage();
    }

}