package org.osgl.mvc.annotation;

import org.osgl.mvc.util.StringValueResolver;

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
@Target(ElementType.PARAMETER)
public @interface Param {
    /**
     * Specify the http parameter name
     */
    String value();

    /**
     * Optionally specify the resolver class
     */
    Class<? extends StringValueResolver> resolverClass() default DEFAULT.class;

    public static final class DEFAULT extends StringValueResolver {
        @Override
        public Object resolve(String value) {
            return null;
        }
    }
}
