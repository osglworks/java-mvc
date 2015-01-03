package org.osgl.mvc.result;

import org.osgl.http.H;
import org.osgl.http.Http;
import org.osgl.mvc.MvcConfig;

/**
 * Created by luog on 24/03/14.
 */
public class ErrorResult extends Result {

    protected ErrorResult(Http.Status status) {
        super(status);
    }

    protected ErrorResult(Http.Status status, String message) {
        super(status, message);
    }

    protected ErrorResult(Http.Status status, String message, Object... args) {
        super(status, message, args);
    }

    protected ErrorResult(Http.Status status, Throwable cause) {
        super(status, cause);
    }

    protected ErrorResult(Http.Status status, Throwable cause, String message, Object... args) {
        super(status, cause, message, args);
    }

    @Override
    protected void applyMessage(H.Request request, H.Response response) {
        MvcConfig.errorPageRenderer().apply(request, response, this);
    }
}
