package org.osgl.mvc;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The {@code Catch} annotation is used to mark a
 * method (the exception handler) that should be executed
 * when there is an exception thrown out during the
 * action method executing
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Catch {

    /**
     * Optionally specify the exception classes should be captured
     * by this exception handler. By default it captures all
     * {@code java.lang.Exception}
     */
    Class<? extends Throwable>[] value() default {Exception.class};

    /**
     * Optionally specify {@code priority} of the before handler.
     * The lesser the priority value, the higher the priority
     * the before handler has
     */
    int priority() default 0;

}
