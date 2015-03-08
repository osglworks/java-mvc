package org.osgl.mvc.util;

import org.osgl._;
import org.osgl.exception.NotAppliedException;
import org.osgl.http.H;

import java.util.Map;

/**
 * A {@code Binder} resolves to a certain type of argument out from a String-String map
 */
public abstract class Binder<T> extends _.F2<String, H.Request, T> {

    public abstract T resolve(String argName, H.Request request);

    @Override
    public T apply(String argName, H.Request request) throws NotAppliedException, _.Break {
        return resolve(argName, request);
    }
}
