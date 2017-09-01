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
