package org.osgl.mvc.result;

import static org.osgl.http.H.Status.METHOD_NOT_ALLOWED;

/**
 * Indicate a 405 Method not allowed response
 */
public class MethodNotAllowed extends ErrorResult {

    public static final MethodNotAllowed INSTANCE = new MethodNotAllowed();

    public MethodNotAllowed() {
        super(METHOD_NOT_ALLOWED, "405 Method Not Allowed");
    }

    public MethodNotAllowed(String message, Object... args) {
        super(METHOD_NOT_ALLOWED, message, args);
    }

    public MethodNotAllowed(Throwable cause, String message, Object ... args) {
        super(METHOD_NOT_ALLOWED, cause, message, args);
    }

    public MethodNotAllowed(Throwable cause) {
        super(METHOD_NOT_ALLOWED, cause, "405 Method Not Allowed");
    }
}
