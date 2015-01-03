package org.osgl.mvc;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The {@code After} annotation is used to mark a
 * method (the after handler) that should be executed
 * after executing controller action method
 * and before rendering view to response
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface After {

    /**
     * Optionally specify {@code priority} of the after handler.
     * The lesser the priority value, the higher the priority
     * the after handler has
     */
    int priority() default 0;

    /**
     * Optionally specify the actions that should not
     * be intercepted by this after handler
     *
     * @see #only() 
     */
    String[] unless() default {};

    /**
     * Optionally specify the only actions that should
     * be intercepted by this after handler
     *
     * <p>
     * Note if both {@code only()} and {@link #unless()}
     * is specified, and an action method name appears
     * in both {@code only()} and {@code unless()}, then
     * it will NOT be intercepted by this after handler
     * </p>
     */
    String[] only() default {};
}
