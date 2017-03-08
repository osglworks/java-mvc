package org.osgl.mvc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The {@code PatchAction} annotation is used to mark a
 * method (the action handler) that should be executed
 * to handle a PATCH http request
 *
 * <p>Thus {@code @PatchAction("/foo")} shall be treated as
 * {@code Action(value="/foo", methods={H.Method.PATCH})}</p>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface PatchAction {

    /**
     * Returns the request paths that this
     * action mapped to.
     *
     * It is possible to use variable in the path, like
     * {@code /service/{id}}, however it is up to
     * the MVC server implementation to decide the
     * format
     */
    String[] value() default {};

}
