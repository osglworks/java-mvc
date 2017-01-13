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
public class ErrorResult extends Result {

    public ErrorResult(Http.Status status) {
        super(status, MvcConfig.errorMessage(status));
    }

    public ErrorResult(Http.Status status, String message) {
        super(status, message);
    }

    public ErrorResult(Http.Status status, String message, Object... args) {
        super(status, message, args);
    }

    public ErrorResult(Http.Status status, Throwable cause) {
        super(status, cause, MvcConfig.errorMessage(status));
    }

    public ErrorResult(Http.Status status, Throwable cause, String message, Object... args) {
        super(status, cause, message, args);
    }

    @Override
    public String toString() {
        return statusCode() + " " + getMessage();
    }

    @Override
    protected void applyMessage(H.Request request, H.Response response) {
        MvcConfig.errorPageRenderer().apply(request, response, this);
    }

    protected static String defaultMessage(H.Status status) {
        return MvcConfig.errorMessage(status);
    }

    protected static boolean _localizedErrorMsg() {
        return MvcConfig.localizedErrorMsg();
    }

}
