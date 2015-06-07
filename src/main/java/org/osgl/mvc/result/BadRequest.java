package org.osgl.mvc.result;

import org.osgl.http.Http;
import org.osgl.util.S;

/**
 * Indicate a client error
 */
public class BadRequest extends ErrorResult {
    public static final BadRequest INSTANCE = new BadRequest();
    public BadRequest() {
        super(Http.Status.BAD_REQUEST);
    }
    public BadRequest(String message) {
        super(Http.Status.BAD_REQUEST, message);
    }
    public BadRequest(String message, Object ... args) {
        this(S.fmt(message, args));
    }
}
