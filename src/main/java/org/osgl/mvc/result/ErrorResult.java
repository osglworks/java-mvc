package org.osgl.mvc.result;

import org.osgl.http.H;
import org.osgl.mvc.MvcConfig;
import org.osgl.util.E;
import org.osgl.util.KVStore;

/**
 * Base class for Error results, i.e result with status code between 400 and 600 (excluded)
 */
public class ErrorResult extends RichResult {

    /**
     * Stores the app defined error code
     */
    private Integer errorCode;

    /**
     * Store app defined payload
     */
    private Object attachment;

    /**
     * {@inheritDoc}
     * @param status the status
     */
    public ErrorResult(H.Status status) {
        super(validateStatusCode(status));
    }

    /**
     * {@inheritDoc}
     *
     * @param status the status
     * @param message the message template
     * @param args the message arguments
     */
    public ErrorResult(H.Status status, String message, Object... args) {
        super(validateStatusCode(status), message, args);
    }

    /**
     * {@inheritDoc}
     * @param cause the cause
     * @return this error result
     */
    @Override
    public ErrorResult initCause(Throwable cause) {
        super.initCause(cause);
        return this;
    }

    /**
     * {@inheritDoc}
     *
     * @param status the new status
     * @return this error result
     */
    @Override
    public ErrorResult overwriteStatus(H.Status status) {
        super.overwriteStatus(status);
        return this;
    }

    /**
     * {@inheritDoc}
     * @param name the header name
     * @param value the header value
     * @return this error result
     */
    @Override
    public ErrorResult header(String name, String value) {
        super.header(name, value);
        return this;
    }

    /**
     * {@inheritDoc}
     *
     * @param header the header
     * @return this error result
     */
    @Override
    public ErrorResult header(H.Header header) {
        super.header(header);
        return this;
    }

    /**
     * {@inheritDoc}
     *
     * @param name the header name
     * @param values the values to be added
     * @return this error result
     */
    @Override
    public ErrorResult addHeader(String name, String... values) {
        super.addHeader(name, values);
        return this;
    }

    /**
     * {@inheritDoc}
     * @param cookie the cookie to be set
     * @return this error result
     */
    @Override
    public ErrorResult cookie(H.Cookie cookie) {
        super.cookie(cookie);
        return this;
    }

    /**
     * {@inheritDoc}
     * @param name the session variable name
     * @param val the session variable value
     * @return this error result
     */
    @Override
    public ErrorResult session(String name, Object val) {
        super.session(name, val);
        return this;
    }

    /**
     * {@inheritDoc}
     * @param name the flash variable name
     * @param val the flash variable value
     * @return this error result
     */
    @Override
    public ErrorResult flash(String name, Object val) {
        super.flash(name, val);
        return this;
    }

    /**
     * {@inheritDoc}
     * @param location the location URL
     * @return this result
     */
    @Override
    public ErrorResult location(String location) {
        super.location(location);
        return this;
    }

    /**
     * {@inheritDoc}
     * @param contentType the content type string
     * @return this result
     */
    @Override
    public ErrorResult contentType(String contentType) {
        super.contentType(contentType);
        return this;
    }

    /**
     * {@inheritDoc}
     * @param contentType the media format
     * @return this result
     */
    @Override
    public ErrorResult contentType(H.Format contentType) {
        super.contentType(contentType);
        return this;
    }

    /**
     * {@inheritDoc}
     * @param etag the etag value
     * @return this result
     */
    @Override
    public ErrorResult etag(String etag) {
        super.etag(etag);
        return this;
    }

    /**
     * Initialize the error code for this error result. This can be used for application
     * to set customized error code. Note app shall at most call this method for one time
     * on any given error result instance. Otherwise, it will raise `IllegalStateException`
     *
     * @param errorCode the error code to be set
     * @return this error result
     * @throws IllegalStateException if error code has already been set on this error result
     */
    public ErrorResult initErrorCode(int errorCode) throws IllegalStateException {
        E.illegalStateIf(null != this.errorCode, "Error code already initialized");
        this.errorCode = errorCode;
        return this;
    }

    /**
     * Returns the error code set on this error result throw calling {@link #initErrorCode(int)}
     * method
     *
     * @return the error code set on this error result
     */
    public Integer errorCode() {
        return errorCode;
    }

    /**
     * Attach an object to this `ErrorResult`. If there are already one attachment
     * it will be replaced with the new one
     *
     * @param attachment the object to be attached
     * @return this error result
     */
    public ErrorResult attach(Object attachment) {
        this.attachment = attachment;
        return this;
    }

    /**
     * Returns an object attached to this error result
     * @param <T> the generic type of the attachment
     * @return the attachment
     */
    @SuppressWarnings("unchecked")
    public <T> T attachment() {
        return (T) attachment;
    }


    /**
     * {@inheritDoc}
     *
     * @return JSON string of this error result
     */
    @Override
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

    /**
     * Render an error page of this error result
     * @param request the request
     * @param response the response
     */
    @Override
    protected void applyBody(H.Request request, H.Response response) {
        MvcConfig.errorPageRenderer().apply(request, response, this);
    }


    private static H.Status validateStatusCode(H.Status status) {
        int statusCode = status.code();
        E.illegalArgumentIf(statusCode < 400 || statusCode > 599, "Invalid error status code: " + statusCode);
        return status;
    }

}
