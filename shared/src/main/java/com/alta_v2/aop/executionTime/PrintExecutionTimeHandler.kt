package com.alta_v2.aop.executionTime

import com.google.common.base.Strings
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
            "Method ${method.name} for class ${method.declaringClass.simpleName} took $executionTime ms"
        } else {
            "Operation '$operation' took $executionTime ms"
        }
        log.info(message)
        return result
    }
}