package org.osgl.mvc.annotation;

import org.osgl.mvc.util.Binder;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The {@code Bind} annotation is used to specify a
 * binding from a group of HTTP parameters to an action
 * handler method parameter
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface Bind {
    /**
     * Optionally specify the resolver class
     */
    Class<? extends Binder> value();
}
