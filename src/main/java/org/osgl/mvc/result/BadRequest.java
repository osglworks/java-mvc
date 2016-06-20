package org.osgl.mvc.result;

import static org.osgl.http.H.Status.BAD_REQUEST;

/**
 * Indicate a client error
 */
public class BadRequest extends ErrorResult {
    public static final BadRequest INSTANCE = new BadRequest();
    public BadRequest() {
        super(BAD_REQUEST, "400 Bad Request");
    }
    public BadRequest(String message, Object ... args) {
        super(BAD_REQUEST, message, args);
    }
    public BadRequest(Throwable cause, String message, Object ... args) {
        super(BAD_REQUEST, cause, message, args);
    }

    public BadRequest(Throwable cause) {
        super(BAD_REQUEST, cause, "400 Bad Request");
    }
}
