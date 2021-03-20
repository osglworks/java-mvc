package org.osgl.mvc.annotation;

/*-
 * #%L
 * OSGL MVC
 * %%
 * Copyright (C) 2014 - 2017 OSGL (Open Source General Library)
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import org.osgl.http.H;
import org.osgl.util.MimeType;

import java.lang.annotation.*;

/**
 * Mark an action handler shall produce response with media type specified
 *
 * Developer can specify content type by either through {@link #mediaType()}
 * or {@link #contentType()}. If none of them is specified on the annotation, then
 * the behavior is undefined.
 *
 * Note {@link #contentType()} has higher priority than {@link #mediaType()},
 * therefore if both are specified then the `contentType` specification takes win.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@Documented
@Inherited
public @interface ResponseContentType {

    /**
     * Specify the content type by {@link H.MediaType} on the response.
     *
     * This method is deprecated. Please use {@link #mediaType()} instead.
     *
     * @return the content type media type.
     */
    @Deprecated
    H.MediaType value() default H.MediaType._UNKNOWN;

    /**
     * Specify the content type by literal string on the response.
     *
     * Note, this method has high priority on
     *
     * @return the content type string.
     */
    String contentType() default "";

    /**
     * Specify the content type by {@link H.MediaType} on the response.
     *
     * @return the content type media type.
     */
    H.MediaType mediaType() default H.MediaType._UNKNOWN;

}
