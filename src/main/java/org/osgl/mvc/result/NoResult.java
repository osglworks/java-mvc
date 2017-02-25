package org.osgl.mvc.result;

/**
 * Render NO_CONTENT response. Please use {@link NoContent} instead
 */
@Deprecated
public class NoResult extends NoContent {

    public static NoResult INSTANCE = new NoResult();

    private NoResult() {}

    public static NoResult get() {
        return INSTANCE;
    }
}
