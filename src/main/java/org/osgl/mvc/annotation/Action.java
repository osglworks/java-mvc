package org.osgl.mvc.annotation;

import org.osgl.http.H;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The {@code Action} annotation is used to mark a
 * method (the action handler) that should be executed
 * to handle a certain http request
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Action {

    /**
     * Returns the request paths that this
     * action mapped to.
     *
     * <p>It is possible to use variable in the path, like
     * {@code /service/{id}}, however it is up to
     * the MVC server implementation to decide the
     * format</p>
     *
     * <p>When value is not specified, the default value is
     * an empty string, in which case the annotation could be
     * used to mark a method is subject to bytecode enhancement
     * by the underline framework</p>
     */
    String[] value() default {};

    /**
     * Indicate the http methods this action is bind to.
     * If not specified, then it shall bind to all allowed
     * http method which is again subject to the mvc server
     * implementation
     */
    H.Method[] methods() default {};

}
