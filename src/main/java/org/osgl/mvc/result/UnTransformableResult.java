package org.osgl.mvc.result;

import org.osgl.http.Http;
import org.osgl.mvc.util.ResultTransformer;
import org.osgl.util.E;

/**
 * the base class of {@link Result} that is not {@link org.osgl.mvc.util.ResultTransformer transformable}
 */
public abstract class UnTransformableResult extends Result {
    public UnTransformableResult() {
    }

    public UnTransformableResult(Http.Status status) {
        super(status);
    }

    public UnTransformableResult(Http.Status status, String message) {
        super(status, message);
    }

    public UnTransformableResult(Http.Status status, String message, Object... args) {
        super(status, message, args);
    }

    public UnTransformableResult(Http.Status status, Throwable cause) {
        super(status, cause);
    }

    public UnTransformableResult(Http.Status status, Throwable cause, String message, Object... args) {
        super(status, cause, message, args);
    }

    @Override
    public final void setTransformer(ResultTransformer transformer) {
        throw E.unsupport();
    }
}
