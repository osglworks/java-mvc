package org.osgl.mvc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The {@code Finally} annotation is used to mark a
 * method (the finally handler) that should be executed
 * after view rendered and sent to response
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Finally {
    /**
     * Optionally specify {@code priority} of the finally handler.
     * The lesser the priority value, the higher the priority
     * the finally handler has
     */
    int priority() default 0;

    /**
     * Optionally specify the actions that should not
     * be intercepted by this finally handler
     *
     * @see #only()
     */
    String[] unless() default {};

    /**
     * Optionally specify the only actions that should
     * be intercepted by this finally handler
     *
     * <p>
     * Note if both {@code only()} and {@link #unless()}
     * is specified, and an action method name appears
     * in both {@code only()} and {@code unless()}, then
     * it will NOT be intercepted by this finally handler
     * </p>
     */
    String[] only() default {};
}
