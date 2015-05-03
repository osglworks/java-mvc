package org.osgl.mvc.result;

import org.osgl.http.Http;
import org.osgl.util.S;

/**
 * Indicate a 404 Not found response
 */
public class NotFound extends ErrorResult {

    public NotFound() {
        super(Http.Status.NOT_FOUND, "404 Not Found");
    }

    public NotFound(String message) {
        super(Http.Status.NOT_FOUND, message);
    }

    public NotFound(String message, Object... args) {
        this(S.fmt(message, args));
    }
}
