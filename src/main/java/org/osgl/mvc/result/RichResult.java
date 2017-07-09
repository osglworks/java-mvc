package org.osgl.mvc.result;

import org.osgl.http.H;
import org.osgl.http.Http;
import org.osgl.logging.LogManager;
import org.osgl.logging.Logger;
import org.osgl.mvc.MvcConfig;
import org.osgl.util.C;
import org.osgl.util.E;
import org.osgl.util.S;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Extended {@link Result} and allow developer to set body/header/cookie/session/flash data into
 * the result
 */
public class RichResult extends Result {

    private static final Logger LOGGER = LogManager.get(RichResult.class);

    /**
     * Log the time when this result is issued
     */
    private long timestamp = System.currentTimeMillis();

    /**
     * store content type in {@link H.Format}
     *
     * If {@link #contentTypeString} is set then this field will not effect
     */
    private H.Format contentTypeFormat;

    /**
     * store content type in string
     */
    private String contentTypeString;

    /**
     * the headers
     */
    private Map<String, H.Header> headers = new HashMap<>();

    /**
     * the cookies
     */
    private Map<String, H.Cookie> cookies = new HashMap<>();

    /**
     * the session variables
     */
    private Map<String, Object> sessionVars = new HashMap<>();

    /**
     * the flash variables
     */
    private Map<String, Object> flashVars = new HashMap<>();

    /**
     * Response that are passed to result upon construction.
     * If response is presented headers and cookies are set
     * directly to response
     */
    private transient H.Response response;

    /**
     * Session that are passed to result upon construction.
     * If session is presented, then
     */
    private transient H.Session session;

    private transient H.Flash flash;

    /**
     * Construct a NormalResult with {@link H.Status#OK} status
     */
    public RichResult() {
        this(H.Status.OK);
    }

    /**
     * Construct a NormalResult with specified {@link H.Status status}
     * @param status the response status
     */
    public RichResult(Http.Status status) {
        this(status, MvcConfig.messageOf(status));
    }

    /**
     * Construct a NormalResult with specified {@link H.Status status} and message template and
     * arguments.
     *
     * Note message template and arguments will be converted into message string via
     * {@link S#fmt(String, Object...)} call
     *
     * @param status the response status
     * @param message the message template
     * @param args the message arguments
     */
    public RichResult(Http.Status status, String message, Object... args) {
        super(status, message, args);
    }

    /**
     * Construct a result with {@link H.Context HTTP context} which will
     * provides {@link H.Flash flash}, {@link H.Session session} and
     * {@link H.Response response}
     *
     * The status will default to {@link H.Status#OK} status
     *
     * @param context the HTTP context
     */
    public RichResult(H.Context context) {
        this(context, H.Status.OK);
    }

    /**
     * Construct a NormalResult with HTTP context and specified {@link H.Status status}
     * @param context the HTTP context
     * @param status the response status
     */
    protected RichResult(H.Context context, Http.Status status) {
        this(context, status, MvcConfig.messageOf(status));
    }

    /**
     * Construct a NormalResult with specified {@link H.Status status} and message template and
     * arguments.
     *
     * Note message template and arguments will be converted into message string via
     * {@link S#fmt(String, Object...)} call
     *
     * @param context the HTTP context
     * @param status the response status
     * @param message the message template
     * @param args the message arguments
     */
    protected RichResult(H.Context context, Http.Status status, String message, Object... args) {
        super(status, message, args);
        this.flash = context.flash();
        this.session = context.session();
        this.response = context.resp();
    }

    /**
     * {@inheritDoc}
     *
     * @return String representation of this result
     */
    @Override
    public String toString() {
        return "HTTP/1.1 " + statusCode() + " " + getLocalizedMessage();
    }

    /**
     * Returns JSON formatted string representation of this error result
     * @return the JSON string of this result
     */
    public String toJsonString() {
        return "{\"status\":"
                + statusCode()
                + ", \"message\":\""
                + getLocalizedMessage()
                + "\", \"timestamp\":"
                + timestamp() + "}";
    }

    /**
     * Overwrite the status
     * @param status the new status
     * @return this result after status overwritten
     */
    public RichResult overwriteStatus(H.Status status) {
        this.status = status;
        return this;
    }

    /**
     * {@inheritDoc}
     *
     * @param cause the cause
     * @return this result
     */
    public RichResult initCause(Throwable cause) {
        return this;
    }

    /**
     * Returns the timestamp when this result is issued
     * @return the timestamp of this result
     */
    public final long timestamp() {
        return timestamp;
    }

    /**
     * Set a header to this result
     * @param name the header name
     * @param value the header value
     * @return this result
     */
    public RichResult header(String name, String value) {
        H.Response resp = response();
        if (null != resp) {
            resp.header(name, value);
        } else {
            this.headers.put(name, new H.Header(name, value));
        }
        return this;
    }

    /**
     * Set a {@link H.Header header} to this result
     * @param header the header
     * @return this result after header set
     */
    public RichResult header(H.Header header) {
        H.Response resp = response();
        if (null != resp) {
            List<String> values = header.values();
            resp.addHeaderValues(header.name(), values.toArray(new String[values.size()]));
        } else {
            this.headers.put(header.name(), header);
        }
        return this;
    }

    /**
     * Add header values to this result. If the header does not exist then
     * create a header, otherwise add the supplied value into existing header
     * @param name the header name
     * @param values the values to be added
     * @return this result
     */
    public RichResult addHeader(String name, String... values) {
        H.Header header = this.headers.get(name);
        if (null == header) {
            header = new H.Header(name, values);
        } else {
            header = new H.Header(name, C.list(header.values()).append(C.listOf(values)));
        }

        H.Response resp = response();
        if (null != resp) {
            resp.header(header);
        } else {
            this.headers.put(name, header);
        }
        return this;
    }

    /**
     * Set a cookie to this result
     * @param cookie the cookie to be set
     * @return this result
     */
    public RichResult cookie(H.Cookie cookie) {
        H.Response resp = response();
        if (null != resp) {
            resp.addCookie(cookie);
        } else {
            this.cookies.put(cookie.name(), cookie);
        }
        return this;
    }

    /**
     * Set session variable
     * @param name the session variable name
     * @param val the session variable value
     * @return this result
     */
    public RichResult session(String name, Object val) {
        return updateKV(name, val, session(), sessionVars);
    }

    /**
     * Set flash variable
     * @param name the flash variable name
     * @param val the flash variable value
     * @return this result
     */
    public RichResult flash(String name, Object val) {
        return updateKV(name, val, flash(), flashVars);
    }

    private RichResult updateKV(String name, Object val, H.KV<?> kv, Map<String, Object> tmpStore) {
        if (null != kv) {
            if (null == val) {
                kv.remove(name);
            } else {
                kv.put(name, val);
            }
        } else {
            if (null == val) {
                tmpStore.remove(name);
            } else {
                tmpStore.put(name, val);
            }
        }
        return this;
    }

    /**
     * Set a "Location" header to this result
     * @param location the location URL
     * @return this result
     */
    public RichResult location(String location) {
        return this.header(H.Header.Names.LOCATION, location);
    }

    /**
     * Set a "ETag" header to this result
     * @param etag the etag value
     * @return this result
     */
    public RichResult etag(String etag) {
        return this.header(H.Header.Names.ETAG, etag);
    }

    /**
     * Set content type with {@link H.Format media format}
     * @param contentType the media format
     * @return this result
     */
    public RichResult contentType(H.Format contentType) {
        contentTypeFormat = contentType;
        return this;
    }

    /**
     * Set the "Content-Type" header to this result
     * @param contentType the content type string
     * @return this result
     */
    public RichResult contentType(String contentType) {
        contentTypeString = contentType;
        return this;
    }

    /**
     * Return the response passed to this result upon construction
     * @return the response or `null` if no response passed into this result
     */
    protected H.Response<?> response() {
        return response;
    }

    /**
     * Return the session passed to this result upon construction
     * @return the session or `null` if no session passed into this result
     */
    protected H.Session session() {
        return session;
    }

    protected H.Flash flash() {
        return flash;
    }

    /**
     * Apply headers in this result into response
     * @param response the response into which the headers will be applied
     */
    protected final void applyHeaders(H.Response response) {
        if (headers.isEmpty()) {
            return;
        }
        for (H.Header header : headers.values()) {
            String name = header.name();
            for (String value : header.values()) {
                response.addHeader(name, value);
            }
        }
    }

    /**
     * Apply cookie in this result into response
     * @param response the response into which the cookies will be applied
     */
    protected final void applyCookies(H.Response response) {
        if (cookies.isEmpty()) {
            return;
        }
        for (H.Cookie cookie : cookies.values()) {
            response.addCookie(cookie);
        }
    }

    protected final void applySessionVars(H.Session session) {
        applyKVStore(session, sessionVars);
    }

    protected final void applyFlashVars(H.Flash flash) {
        applyKVStore(flash, flashVars);
    }

    private void applyKVStore(H.KV<?> kv, Map<String, Object> tmpStore) {
        if (tmpStore.isEmpty()) {
            return;
        }
        for (Map.Entry<String, Object> entry : tmpStore.entrySet()) {
            kv.put(entry.getKey(), entry.getValue());
        }
    }

    /**
     * Apply this result into the response
     * @param context the HTTP context
     */
    public void apply(H.Context context) {
        H.Response resp = context.resp();
        H.Request req = context.req();
        H.Session session = context.session();
        H.Flash flash = context.flash();
        try {
            H.Session mySess = session();
            E.illegalArgumentIf(null != mySess && mySess != session, "session object does not match result object");

            H.Flash myFlash = flash();
            E.illegalArgumentIf(null != myFlash && myFlash != flash, "flash object does not match result object");

            H.Response myResp = response();
            E.illegalArgumentIf(null != myResp && myResp != resp, "response object does not match result object");

            if (null == mySess) {
                applySessionVars(session);
            }
            if (null == myFlash) {
                applyFlashVars(flash);
            }
            prepareStatus(context);
            applyStatus(resp);
            applyContentType(resp);
            prepareHeaderAndCookies(context);
            if (null == myResp) {
                applyCookies(resp);
                applyHeaders(resp);
            }
            applyBeforeCommitHandler(req, resp);
            applyBody(req, resp);
        } finally {
            resp.commit();
            applyAfterCommitHandler(req, resp);
        }
    }

    private void applyContentType(H.Response response) {
        if (null != contentTypeString) {
            response.contentType(contentTypeString);
        } else if (null != contentTypeFormat) {
            response.contentType(contentTypeFormat.contentType() + "; charset=" + response.characterEncoding());
        }
    }

    /**
     * Called before applying cookies and headers to response
     *
     * Sub class can use this to generate derived headers or cookies
     *
     * @param context the HTTP context
     */
    protected void prepareHeaderAndCookies(H.Context context) {
    }

    /**
     * Called before applying status to response
     *
     * Sub class can use this to overwrite status
     *
     * @param context the HTTP context
     */
    protected void prepareStatus(H.Context context) {
    }
}
