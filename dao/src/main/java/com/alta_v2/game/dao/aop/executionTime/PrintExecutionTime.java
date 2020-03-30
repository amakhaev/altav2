package com.alta_v2.game.dao.aop.executionTime;

import com.google.inject.BindingAnnotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Provides the annotation to mark method that for which execution time should be logged.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@BindingAnnotation
public @interface PrintExecutionTime {

    String operationName() default "";

}
