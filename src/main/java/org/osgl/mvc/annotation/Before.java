package org.osgl.mvc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The {@code Before} annotation is used to mark a
 * method (the before handler) that should be executed
 * before executing controller action method.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Before {

    /**
     * Optionally specify {@code priority} of the before handler.
     * The lesser the priority value, the higher the priority
     * the before handler has
     */
    int priority() default 0;

    /**
     * Optionally specify the actions that should not
     * be intercepted by this before handler
     *
     * @see #only()
     */
    String[] unless() default {};

    /**
     * Optionally specify the only actions that should
     * be intercepted by this before handler
     *
     * <p>
     * Note if both {@code only()} and {@link #unless()}
     * is specified, and an action method name appears
     * in both {@code only()} and {@code unless()}, then
     * it will NOT be intercepted by this before handler
     * </p>
     */
    String[] only() default {};
}
