package org.osgl.mvc.annotation;

import org.osgl.util.StringValueResolver;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The {@code Bind} annotation is used to specify
 * {@link StringValueResolver} that can be applied
 * to field, parameter or an annotation
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
public @interface Resolve {
    /**
     * Specify the {@link StringValueResolver} implementations
     *
     * **Note** each resolver implementation must have different
     * {@link StringValueResolver#targetType()}
     */
    Class<? extends StringValueResolver>[] value();
}
