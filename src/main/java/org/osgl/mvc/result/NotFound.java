package org.osgl.mvc.result;

import org.osgl.http.Http;
import org.osgl.util.S;

import static org.osgl.http.H.Status.NOT_FOUND;

/**
 * Indicate a 404 Not found response
 */
public class NotFound extends ErrorResult {

    public static final NotFound INSTANCE = new NotFound();

    public NotFound() {
        super(NOT_FOUND, "404 Not Found");
    }

    public NotFound(String message, Object... args) {
        super(NOT_FOUND, message, args);
    }

    public NotFound(Throwable cause, String message, Object... args) {
        super(NOT_FOUND, cause, message, args);
    }

    public NotFound(Throwable cause) {
        super(NOT_FOUND, cause, "404 Not Found");
    }
}
