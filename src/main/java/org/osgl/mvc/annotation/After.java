package org.osgl.mvc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The {@code After} annotation is used to mark a
 * method (the after interceptor) that should be executed
 * after executing controller action method
 * and before rendering view to response
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface After {

    /**
     * Optionally specify {@code priority} of the after interceptor.
     * it's up to the framework to decide how to interpret the
     * priority in terms of int value
     */
    int priority() default 0;

    /**
     * Optionally specify the actions that should not
     * be intercepted by this after interceptor
     *
     * @see #only() 
     */
    String[] except() default {};

    /**
     * Optionally specify the only actions that should
     * be intercepted by this after interceptor
     *
     * <p>
     * Note if both {@code only()} and {@link #except()}
     * is specified, and an action method name appears
     * in both {@code only()} and {@code except()}, then
     * it will NOT be intercepted by this after interceptor
     * </p>
     *
     * @see #except()
     */
    String[] only() default {};
}
