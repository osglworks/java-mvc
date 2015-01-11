package org.osgl.mvc.result;

import org.osgl.exception.FastRuntimeException;
import org.osgl.http.H;
import org.osgl.http.Http;
import org.osgl.util.S;

/**
 * Created by luog on 16/01/14.
 */
public class Result extends FastRuntimeException {

    private final Http.Status status;

    protected Result() {status = null;}

    protected Result(Http.Status status) {
        this.status = status;
    }

    protected Result(Http.Status status, String message) {
        super(message);
        this.status = status;
    }

    protected Result(Http.Status status, String message, Object... args) {
        super(message, args);
        this.status = status;
    }

    protected Result(Http.Status status, Throwable cause) {
        super(cause);
        this.status = status;
    }

    protected Result(Http.Status status, Throwable cause, String message, Object... args) {
        super(cause, message, args);
        this.status = status;
    }

    public Http.Status status() {
        return status;
    }

    public int statusCode() {
        return status().code();
    }

    protected final void applyStatus(H.Response response) {
        if (null != status) response.status(status);
    }

    protected void applyMessage(H.Request request, H.Response response) {
        String msg = getMessage();
        if (S.notBlank(msg)) {
            response.writeContent(msg);
        }
    }

    public void apply(H.Request req, H.Response resp) {
        applyStatus(resp);
        applyMessage(req, resp);
    }

}
