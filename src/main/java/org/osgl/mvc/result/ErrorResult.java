package org.osgl.mvc.result;

import org.osgl.http.H;
import org.osgl.http.Http;
import org.osgl.mvc.MvcConfig;

/**
 * Base class for Error results:
 * <ul>
 *     <li>{@link NotFound}</li>
 *     <li>{@link ServerError}</li>
 *     <li>{@link Forbidden}</li>
 * </ul>
 */
public class ErrorResult extends UnTransformableResult {

    public ErrorResult(Http.Status status) {
        super(status);
    }

    public ErrorResult(Http.Status status, String message) {
        super(status, message);
    }

    public ErrorResult(Http.Status status, String message, Object... args) {
        super(status, message, args);
    }

    public ErrorResult(Http.Status status, Throwable cause) {
        super(status, cause);
    }

    public ErrorResult(Http.Status status, Throwable cause, String message, Object... args) {
        super(status, cause, message, args);
    }



    @Override
    protected void applyMessage(H.Request request, H.Response response) {
        MvcConfig.errorPageRenderer().apply(request, response, this);
    }
}
