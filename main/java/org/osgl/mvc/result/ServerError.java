package org.osgl.mvc.result;

import org.osgl.http.Http;
import org.osgl.util.S;

/**
 * Created by luog on 20/03/2014.
 */
public class ServerError extends ErrorResult {
    public ServerError() {
        super(Http.Status.INTERNAL_SERVER_ERROR);
    }
    public ServerError(String message) {
        super(Http.Status.INTERNAL_SERVER_ERROR, message);
    }
    public ServerError(String message, Object... args) {
        this(S.fmt(message, args));
    }
    public ServerError(Throwable t, String message) {
        super(Http.Status.INTERNAL_SERVER_ERROR, t, message);
    }
    public ServerError(Throwable t, String message, Object... args) {
        this(t, S.fmt(message, args));
    }
}
