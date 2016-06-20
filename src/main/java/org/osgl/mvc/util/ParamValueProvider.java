package org.osgl.mvc.util;

/**
 * Provide data to {@link Binder}
 */
public interface ParamValueProvider {
    String paramVal(String key);
    String[] paramVals(String key);
}
