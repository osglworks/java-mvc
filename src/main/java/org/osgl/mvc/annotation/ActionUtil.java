package org.osgl.mvc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Mark an method as an action util method. An action util method is supposed to be called
 * by action methods (i.e. the method that handles requests).
 *
 * ActFramework will treat action util method as action method so it will apply the bytecode
 * enhancement on those methods
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ActionUtil {
}
