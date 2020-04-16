package com.alta_v2.aop.executionTime

import com.google.inject.BindingAnnotation

/**
 * Provides the annotation to mark method that for which execution time should be logged.
 */
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
@BindingAnnotation
annotation class PrintExecutionTime(val operationName: String = "")