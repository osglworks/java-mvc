package org.osgl.mvc.result;

import org.osgl.http.H;
import org.osgl.http.Http;
import org.osgl.util.S;

import static org.osgl.http.H.Status.NOT_FOUND;
import static org.osgl.http.H.Status.NOT_IMPLEMENTED;

/**
 * Indicate a 501 Not implemented response
 */
public class NotImplemented extends ErrorResult {

    public static final NotImplemented INSTANCE = new NotImplemented();

    public NotImplemented() {
        super(NOT_IMPLEMENTED, "501 Not Implemented");
    }

    public NotImplemented(String message, Object... args) {
        super(NOT_IMPLEMENTED, message, args);
    }

    public NotImplemented(Throwable cause, String message, Object... args) {
        super(NOT_IMPLEMENTED, cause, message, args);
    }

    public NotImplemented(Throwable cause) {
        super(NOT_IMPLEMENTED, cause, "501 Not Implemented");
    }
}
