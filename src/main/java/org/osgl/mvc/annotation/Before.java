package org.osgl.mvc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The {@code Before} annotation is used to mark a
 * method (the before interceptor) that should be executed
 * before executing controller action method.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Before {

    /**
     * Optionally specify {@code priority} of the before interceptor.
     * it's up to the framework to decide how to interpret the
     * priority in terms of int value
     */
    int priority() default 0;

    /**
     * Optionally specify the actions that should not
     * be intercepted by this before interceptor
     *
     * @see #only()
     */
    String[] except() default {};

    /**
     * Optionally specify the only actions that should
     * be intercepted by this before interceptor
     *
     * <p>
     * Note if both {@code only()} and {@link #except()}
     * is specified, and an action method name appears
     * in both {@code only()} and {@code except()}, then
     * it will NOT be intercepted by this before interceptor
     * </p>
     *
     * @see #except()
     */
    String[] only() default {};
}
