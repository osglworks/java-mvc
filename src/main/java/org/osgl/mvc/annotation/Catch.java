package org.osgl.mvc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The {@code Catch} annotation is used to mark a
 * method (the exception interceptor) that should be executed
 * when there is an exception thrown out during the
 * action method executing
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Catch {

    /**
     * Optionally specify the exception classes should be captured
     * by this exception interceptor. By default it captures all
     * {@code java.lang.Exception}
     */
    Class<? extends Throwable>[] value() default {Exception.class};

    /**
     * Optionally specify {@code priority} of the catch interceptor.
     * it's up to the framework to decide how to interpret the
     * priority in terms of int value
     */
    int priority() default 0;

    /**
     * Optionally specify the actions that should not
     * be intercepted by this exception interceptor
     *
     * @see #only()
     */
    String[] except() default {};

    /**
     * Optionally specify the only actions that should
     * be intercepted by this catch interceptor
     *
     * <p>
     * Note if both {@code only()} and {@link #except()}
     * is specified, and an action method name appears
     * in both {@code only()} and {@code except()}, then
     * it will NOT be intercepted by this exception interceptor
     * </p>
     *
     * @see #except()
     */
    String[] only() default {};
}
