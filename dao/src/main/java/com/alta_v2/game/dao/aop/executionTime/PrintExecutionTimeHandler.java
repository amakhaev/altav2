package com.alta_v2.game.dao.aop.executionTime;

import com.google.common.base.Strings;
import lombok.extern.log4j.Log4j2;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Method;
import java.time.Duration;
import java.time.Instant;

@Log4j2
public class PrintExecutionTimeHandler implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Method method = invocation.getMethod();

        Instant start = Instant.now();
        Object result = invocation.proceed();
        long executionTime = Duration.between(start, Instant.now()).toMillis();

        String operation = method.getAnnotation(PrintExecutionTime.class).operationName();
        String message;
        if (Strings.isNullOrEmpty(operation)) {
            message = String.format(
                    "Method %s for class %s took %d ms",
                    method.getName(), method.getDeclaringClass().getSimpleName(), executionTime
            );
        } else {
            message = String.format(
                    "Operation %s in method %s for class %s took %d ms",
                    operation, method.getName(), method.getDeclaringClass().getSimpleName(), executionTime
            );
        }

        log.info(message);
        return result;
    }
}
