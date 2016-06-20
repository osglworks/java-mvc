package org.osgl.mvc.result;

import static org.osgl.http.H.Status.INTERNAL_SERVER_ERROR;

/**
 * Indicate an internal server error and set 500 status on response
 */
public class ServerError extends ErrorResult {
    public ServerError() {
        this("Internal Error");
    }
    public ServerError(String message, Object... args) {
        super(INTERNAL_SERVER_ERROR, message, args);
    }
    public ServerError(Throwable t) {
        this(t, "Internal Error");
    }
    public ServerError(Throwable t, String message, Object... args) {
        super(INTERNAL_SERVER_ERROR, t, message, args);
    }
}
