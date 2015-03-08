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
     * Specify the {@link Binder} implementation
     */
    Class<? extends Binder> value();

    /**
     * Optionally specify the model attribute name the binder should
     * used to resolve request parameters into the domain model
     * object. Example:
     * <p>HTML code</p>
     * <code>
     * <pre>
     * &lt;form&gt;
     *     &lt;input name="u.name"&gt;
     *     &lt;input name="u.password" type="password" &gt;
     * &lt;/form&gt;
     * </pre>
     * </code>
     * <p>Java code</p>
     * <code>
     * <pre>
     * public Result createUser(@Binder(value = UserBinder.class, model = "p") User user) {
     *     ...
     * }
     * </pre>
     * </code>
     * <p>If <code>model</code> is ignored, then implementation should use the
     * parameter name (<code>user</code>) in our case to resolve the model instance; and
     * the developer shall not use {@code u.name} and {@code u.password} to refer
     * the model, instead {@code user.name} and {@code user.password} shall be used</p>
     */
    String model() default "";
}
