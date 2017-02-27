package org.osgl.mvc.annotation;

import org.osgl.http.H;

import java.lang.annotation.*;

/**
 * Mark an action handler shall produce response with media type specified
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@Documented
public @interface ResponseContentType {
    /**
     * Specify the content type string, e.g. "application/json"
     * @return the content type string.
     */
    H.MediaType value();
}
