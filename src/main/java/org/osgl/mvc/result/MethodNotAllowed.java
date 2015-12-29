package org.osgl.mvc.result;

import org.osgl.http.H;
import org.osgl.http.Http;
import org.osgl.util.S;

/**
 * Indicate a 405 Method not allowed response
 */
public class MethodNotAllowed extends ErrorResult {

    public static final MethodNotAllowed INSTANCE = new MethodNotAllowed();

    public MethodNotAllowed() {
        super(Http.Status.METHOD_NOT_ALLOWED, "405 Method Not Allowed");
    }

    public MethodNotAllowed(String message) {
        super(Http.Status.METHOD_NOT_ALLOWED, message);
    }

    public MethodNotAllowed(String message, Object... args) {
        this(S.fmt(message, args));
    }
}
