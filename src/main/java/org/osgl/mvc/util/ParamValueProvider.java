package org.osgl.mvc.util;

import java.util.Set;

/**
 * Provide data to {@link Binder}
 */
public interface ParamValueProvider {
    Set<String> paramKeys();
    String paramVal(String key);
    String[] paramVals(String key);
}
