package org.osgl.mvc.result;

import org.osgl.http.Http;
import org.osgl.util.S;

/**
 * HTTP 403 Forbidden
 */
public class Forbidden extends ErrorResult {
    public static final Forbidden INSTANCE = new Forbidden();

    public Forbidden() {
        super(Http.Status.FORBIDDEN, "404 Forbidden");
    }
    public Forbidden(String message) {
        super(Http.Status.FORBIDDEN, message);
    }
    public Forbidden(String message, Object... args) {
        this(S.fmt(message, args));
    }
}
