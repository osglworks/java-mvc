package org.osgl.mvc.result;

import org.osgl.http.H;
import org.osgl.http.Http;
import org.osgl.mvc.MvcConfig;
import org.osgl.util.KVStore;
import org.osgl.util.S;

/**
 * Base class for Error results, i.e result with status code between 400 and 600 (excluded)
 */
public class ErrorResult extends Result {

    private static final ErrorResult _INSTANCE = new ErrorResult(Http.Status.I_AM_A_TEAPOT) {
        @Override
        public Integer errorCode() {
            return payload().errorCode;
        }

        @Override
        public String getMessage() {
            return payload().message;
        }

        @Override
        public Http.Status status() {
            return payload().status;
        }

        @Override
        public long timestamp() {
            return payload().timestamp;
        }

        @Override
        public synchronized Throwable getCause() {
            return payload().cause;
        }

        @Override
        public ErrorResult attach(Object attachment) {
            payload().attach(attachment);
            return this;
        }

        @Override
        public <T> T attachment() {
            return (T) payload().attachment;
        }

        @Override
        public <T> T attachment(Class<T> type) {
            return (T) payload().attachment;
        }
    };

    /**
     * Stores the app defined error code
     */
    private Integer errorCode;

    /**
     * Store app defined payload
     */
    private Object attachment;

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

    public ErrorResult(Http.Status status, Integer errorCode) {
        super(status, MvcConfig.errorMessage(status));
        this.errorCode = errorCode;
    }

    public ErrorResult(Http.Status status, Integer errorCode, String message) {
        super(status, message);
        this.errorCode = errorCode;
    }

    public ErrorResult(Http.Status status, Integer errorCode, String message, Object... args) {
        super(status, message, args);
        this.errorCode = errorCode;
    }

    public ErrorResult(Http.Status status, Integer errorCode, Throwable cause) {
        super(status, cause, MvcConfig.errorMessage(status));
        this.errorCode = errorCode;
    }

    public ErrorResult(Http.Status status, Integer errorCode, Throwable cause, String message, Object... args) {
        super(status, cause, message, args);
        this.errorCode = errorCode;
    }

    public ErrorResult attach(Object attachment) {
        this.attachment = attachment;
        return this;
    }

    @SuppressWarnings("unchecked")
    public <T> T attachment() {
        return (T) attachment;
    }

    @SuppressWarnings("unchecked")
    public <T> T attachment(Class<T> type) {
        return (T) attachment;
    }

    @Override
    public String toString() {
        return "HTTP/1.1 " + statusCode() + " " + getLocalizedMessage();
    }

    public String toJsonString() {
        StringBuilder sb = new StringBuilder("{\"status\":")
                .append(statusCode()).append(", \"message\":\"").append(getLocalizedMessage());
        Integer errorCode = errorCode();
        if (null != errorCode) {
            sb.append("\", \"error\":").append(errorCode);
        } else {
            sb.append("\"");
        }
        return sb.append(", \"timestamp\":").append(timestamp()).append("}").toString();
    }

    /**
     * Export the error result data into a {@link KVStore}
     * @return a {@link KVStore KV store} of this error result
     */
    public KVStore toKVStore() {
        KVStore store = new KVStore();
        store.putValue("status", statusCode());
        store.putValue("message", getLocalizedMessage());
        store.putValue("timestamp", timestamp());
        Integer code = errorCode();
        if (null != code) {
            store.putValue("code", code);
        }
        Object payload = attachment();
        if (null != payload) {
            store.putValue("payload", payload);
        }
        return store;
    }

    public Integer errorCode() {
        return errorCode;
    }

    @Override
    public String getLocalizedMessage() {
        return MvcConfig.messageTranslater().apply(getMessage());
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

    public static ErrorResult of(H.Status status) {
        touchPayload().status(status).message(MvcConfig.errorMessage(status));
        return _INSTANCE;
    }

    public static ErrorResult of(H.Status status, int errorCode) {
        touchPayload().errorCode(errorCode);
        return of(status);
    }

    public static ErrorResult of(H.Status status, int errorCode, String message, Object... args) {
        touchPayload().errorCode(errorCode);
        return of(status, message, args);
    }

    public static ErrorResult of(H.Status status, String message, Object... args) {
        touchPayload().message(S.fmt(message, args));
        return of(status);
    }

    public static ErrorResult of(H.Status status, Throwable cause, String message, Object... args) {
        touchPayload().cause(cause);
        return of(status, message, args);
    }

    public static ErrorResult of(H.Status status, int errorCode, Throwable cause, String message, Object... args) {
        touchPayload().cause(cause);
        return of(status, errorCode, message, args);
    }

    public static ErrorResult of(H.Status status, int errorCode, Throwable cause) {
        return of(status, errorCode, cause, cause.getMessage());
    }

}
