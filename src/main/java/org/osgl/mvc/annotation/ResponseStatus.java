package org.osgl.mvc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Mark a certain class so when the implementation framework encountered a
 * return result of the class, it will automatically set the response status
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ResponseStatus {
    /**
     * Specify the response status code
     * @return the response status code
     * @see org.osgl.http.H.Status
     */
    int value();
}
