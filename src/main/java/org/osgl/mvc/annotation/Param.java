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

import org.osgl.util.StringValueResolver;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The {@code Param} annotation is used to specify a
 * binding of HTTP parameter to an action handler method
 * parameter
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER, ElementType.FIELD})
public @interface Param {
    /**
     * Specify the http parameter name
     * <p>Default: empty string. Leave to framework to decide the bind parameter name</p>
     */
    String value() default "";

    String defVal() default "";
    boolean defBooleanVal() default false;
    char defCharVal() default '\0';
    byte defByteVal() default 0;
    short defShortVal() default 0;
    int defIntVal() default 0;
    float defFloatVal() default 0.0f;
    long defLongVal() default 0L;
    double defDoubleVal() default 0.0d;

    /**
     * Optionally specify the resolver class
     *
     * This is deprecated. Please use {@link Resolve} annotation instead
     */
    @Deprecated
    Class<? extends StringValueResolver> resolverClass() default DEFAULT_RESOLVER.class;

    public static final class DEFAULT_RESOLVER extends StringValueResolver {
        @Override
        public Object resolve(String value) {
            return null;
        }
    }
}
