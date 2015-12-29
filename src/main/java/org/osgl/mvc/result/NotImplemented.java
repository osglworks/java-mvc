package org.osgl.mvc.result;

import org.osgl.http.H;
import org.osgl.http.Http;
import org.osgl.util.S;

/**
 * Indicate a 501 Not implemented response
 */
public class NotImplemented extends ErrorResult {

    public static final NotImplemented INSTANCE = new NotImplemented();

    public NotImplemented() {
        super(Http.Status.NOT_IMPLEMENTED, "501 Not Implemented");
    }

    public NotImplemented(String message) {
        super(Http.Status.NOT_IMPLEMENTED, message);
    }

    public NotImplemented(String message, Object... args) {
        this(S.fmt(message, args));
    }
}
