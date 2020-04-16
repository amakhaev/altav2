package com.alta_v2.aop.executionTime

import com.google.common.base.Strings
import lombok.extern.log4j.Log4j2
import mu.KotlinLogging
import org.aopalliance.intercept.MethodInterceptor
import org.aopalliance.intercept.MethodInvocation
import java.time.Duration
import java.time.Instant

class PrintExecutionTimeHandler : MethodInterceptor {

    private val log = KotlinLogging.logger {  }

    @Throws(Throwable::class)
    override fun invoke(invocation: MethodInvocation): Any {
        val method = invocation.method
        val start = Instant.now()
        val result = invocation.proceed()

        val executionTime = Duration.between(start, Instant.now()).toMillis()
        val operation: String = method.getAnnotation(PrintExecutionTime::class.java).operationName
        val message: String
        message = if (Strings.isNullOrEmpty(operation)) {
            String.format(
                    "Method %s for class %s took %d ms",
                    method.name, method.declaringClass.simpleName, executionTime
            )
        } else {
            String.format(
                    "Operation '%s' in method %s for class %s took %d ms",
                    operation, method.name, method.declaringClass.simpleName, executionTime
            )
        }
        log.info(message)
        return result
    }
}