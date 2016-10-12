package org.osgl.mvc.annotation;

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
