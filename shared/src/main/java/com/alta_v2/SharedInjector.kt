package com.alta_v2

import com.alta_v2.aop.executionTime.PrintExecutionTime
import com.alta_v2.aop.executionTime.PrintExecutionTimeHandler
import com.google.inject.AbstractModule
import com.google.inject.matcher.Matchers

class SharedInjector : AbstractModule() {
    override fun configure() {
        bindInterceptor(Matchers.any(), Matchers.annotatedWith(PrintExecutionTime::class.java), PrintExecutionTimeHandler())
    }
}