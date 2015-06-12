package org.osgl.mvc.util;

import org.osgl._;
import org.osgl.exception.NotAppliedException;
import org.osgl.http.H;

import java.util.Map;

/**
 * A {@code Binder} resolves to a certain type of argument out from a String-String map
 */
public abstract class Binder<T> extends _.F2<String, ParamValueProvider, T> {

    public abstract T resolve(String model, ParamValueProvider params);

    @Override
    public final T apply(String argName, ParamValueProvider params) throws NotAppliedException, _.Break {
        return resolve(argName, params);
    }
}
