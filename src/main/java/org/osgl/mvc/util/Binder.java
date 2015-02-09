package org.osgl.mvc.util;

import org.osgl._;
import org.osgl.exception.NotAppliedException;

import java.util.Map;

/**
 * A {@code Binder} resolves to a certain type of argument out from a String-String map
 */
public abstract class Binder<T> extends _.F2<String, Map<String, String[]>, T> {

    public abstract T resolve(String argName, Map<String, String[]> params);

    @Override
    public T apply(String argName, Map<String, String[]> params) throws NotAppliedException, _.Break {
        return resolve(argName, params);
    }
}
