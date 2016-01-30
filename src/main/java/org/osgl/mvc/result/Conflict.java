package org.osgl.mvc.result;

import static org.osgl.http.H.Status.CONFLICT;

/**
 * Indicate a 404 Not found response
 */
public class Conflict extends ErrorResult {

    public static final Conflict INSTANCE = new Conflict();

    public Conflict() {
        super(CONFLICT, "409 Conflict");
    }

    public Conflict(String message, Object... args) {
        super(CONFLICT, message, args);
    }

    public Conflict(Throwable cause, String message, Object... args) {
        super(CONFLICT, cause, message, args);
    }

    public Conflict(Throwable cause) {
        super(CONFLICT, cause, "409 Conflict");
    }
}
