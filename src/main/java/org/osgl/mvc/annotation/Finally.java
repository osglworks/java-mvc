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

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The {@code Finally} annotation is used to mark a
 * method (the finally interceptor) that should be executed
 * after view rendered and sent to response
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Finally {
    /**
     * Optionally specify {@code priority} of the finally interceptor.
     * it's up to the framework to decide how to interpret the
     * priority in terms of int value
     */
    int priority() default 0;

    /**
     * Optionally specify the actions that should not
     * be intercepted by this finally interceptor
     *
     * @see #only()
     */
    String[] except() default {};

    /**
     * Optionally specify the only actions that should
     * be intercepted by this finally interceptor
     *
     * <p>
     * Note if both {@code only()} and {@link #except()}
     * is specified, and an action method name appears
     * in both {@code only()} and {@code except()}, then
     * it will NOT be intercepted by this finally interceptor
     * </p>
     *
     * @see #except()
     */
    String[] only() default {};
}
