package org.osgl.mvc.result;

import static org.osgl.http.H.Status.FORBIDDEN;

/**
 * HTTP 403 Forbidden
 */
public class Forbidden extends ErrorResult {
    public static final Forbidden INSTANCE = new Forbidden();

    public Forbidden() {
        super(FORBIDDEN, "403 Forbidden");
    }

    public Forbidden(String message, Object... args) {
        super(FORBIDDEN, message, args);
    }

    public Forbidden(Throwable cause, String message, Object... args) {
        super(FORBIDDEN, cause, message, args);
    }

    public Forbidden(Throwable cause) {
        super(FORBIDDEN, cause, "404 Forbidden");
    }
}
