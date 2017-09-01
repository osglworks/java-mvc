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

import org.osgl.mvc.util.Binder;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The {@code Bind} annotation is used to specify a
 * binding from a group of HTTP parameters to an action
 * handler method parameter, controller field or
 * an annotation
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
public @interface Bind {
    /**
     * Specify the {@link Binder} implementations
     *
     * **Note** each binder implementation must have different
     * {@link Binder#targetType()}
     */
    Class<? extends Binder>[] value();

    /**
     * Optionally specify the model attribute name the binder should
     * used to resolve request parameters into the domain model
     * object. Example:
     * <p>HTML code</p>
     * <pre>
     * <code>
     * &lt;form&gt;
     *     &lt;input name="u.name"&gt;
     *     &lt;input name="u.password" type="password" &gt;
     * &lt;/form&gt;
     * </code>
     * </pre>
     * <p>Java code</p>
     * <pre>
     * <code>
     * public Result createUser(@Binder(value = UserBinder.class, model = "u") User user) {
     *     ...
     * }
     * </code>
     * </pre>
     * <p>If <code>model</code> is ignored, then implementation should use the
     * parameter name (<code>user</code>) in our case to resolve the model instance; and
     * the frontend developer shall not use {@code u.name} and {@code u.password} to refer
     * the model, instead {@code user.name} and {@code user.password} shall be used</p>
     */
    String model() default "";
}
