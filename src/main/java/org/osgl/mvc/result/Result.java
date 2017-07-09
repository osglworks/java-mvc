package org.osgl.mvc.result;

import org.osgl.exception.FastRuntimeException;
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

import static org.osgl.http.H.Status.*;

/**
 * Encapsulate data to be applied into a {@link H.Response response}
 *
 * The `Result` is modeled as a {@link FastRuntimeException} sub class so that the
 * program can either `return` or `throw` the result back to control point.
 */
public class Result extends FastRuntimeException {

    public static final Logger LOGGER = LogManager.get(Result.class);

    /**
     * The response status
     */
    private Http.Status status;

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
     * Construct a Result with {@link H.Status#OK} status
     */
    public Result() {
        this(OK);
    }

    /**
     * Construct a Result with specified {@link H.Status status}
     * @param status the response status
     */
    public Result(Http.Status status) {
        this(status, MvcConfig.messageOf(status));
    }

    /**
     * Construct a Result with specified {@link H.Status status} and message template and
     * arguments.
     *
     * Note message template and arguments will be converted into message string via
     * {@link S#fmt(String, Object...)} call
     *
     * @param status the response status
     * @param message the message template
     * @param args the message arguments
     */
    public Result(Http.Status status, String message, Object... args) {
        super(message, args);
        this.status = status;
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
    public Result(H.Context context) {
        this(context, H.Status.OK);
    }

    /**
     * Construct a Result with HTTP context and specified {@link H.Status status}
     * @param context the HTTP context
     * @param status the response status
     */
    protected Result(H.Context context, Http.Status status) {
        this(context, status, MvcConfig.messageOf(status));
    }

    /**
     * Construct a Result with specified {@link H.Status status} and message template and
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
    protected Result(H.Context context, Http.Status status, String message, Object... args) {
        super(message, args);
        this.status = status;
        this.flash = context.flash();
        this.session = context.session();
        this.response = context.resp();
    }


    /**
     * {@inheritDoc}
     *
     * @return localized message
     */
    @Override
    public final String getLocalizedMessage() {
        return MvcConfig.messageTranslater().apply(getMessage());
    }

    /**
     * {@inheritDoc}
     *
     * @return String representation of this result
     */
    @Override
    public final String toString() {
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
     * Report if this result is a success result
     *
     * A result is considered to be a success result if the status code is between 200 and 300 exclusive
     *
     * @return `true` if this result is a success result of `false` otherwise
     */
    public final boolean isSuccess() {
        int code = statusCode();
        return code > 199 && code < 300;
    }

    /**
     * Report if this result is a redirection result
     *
     * A result is considered to be a redirection result if the status code is between 300 and 400 exclusive
     *
     * @return `true` if this result is a redirection result of `false` otherwise
     */
    public final boolean isRedirection() {
        int code = statusCode();
        return code > 299 && code < 400;
    }

    /**
     * Report if this result is a client error result
     *
     * A result is considered to be a client error result if the status code is between 400 and 500 exclusive
     *
     * @return `true` if this result is a client error result of `false` otherwise
     */
    public final boolean isClientError() {
        int code = statusCode();
        return code > 399 && code < 500;
    }

    /**
     * Report if this result is a server error result
     *
     * A result is considered to be a server error result if the status code is between 500 and 600 exclusive
     *
     * @return `true` if this result is a server error result of `false` otherwise
     */
    public final boolean isServerError() {
        int code = statusCode();
        return code > 499 && code < 600;
    }

    /**
     * Report if this result is a client error or server error result
     *
     * A result is considered to be a client or server error result if the status code
     * is between 300 and 600 exclusive
     *
     * @return `true` if this result is a client or error result of `false` otherwise
     */
    public final boolean isError() {
        int code = statusCode();
        return code > 399 && code < 600;
    }

    /**
     * Return the response passed to this result upon construction
     * @return the response or `null` if no response passed into this result
     */
    public final H.Response<?> response() {
        return response;
    }

    /**
     * Return the session passed to this result upon construction
     * @return the session or `null` if no session passed into this result
     */
    public final H.Session session() {
        return session;
    }

    public final H.Flash flash() {
        return flash;
    }

    /**
     * Returns the {@link H.Status status}
     * @return the status
     */
    public Http.Status status() {
        return status;
    }

    /**
     * Returns the status code
     * @return the status code
     */
    public final int statusCode() {
        return status().code();
    }

    /**
     * Overwrite the status
     * @param status the new status
     * @return this result after status overwritten
     */
    public Result overwriteStatus(H.Status status) {
        this.status = status;
        return this;
    }

    /**
     * {@inheritDoc}
     *
     * @param cause the cause
     * @return this result
     */
    public Result initCause(Throwable cause) {
        return this;
    }

    /**
     * Returns the cause of this result
     * @return the cause
     */
    public final Throwable cause() {
        return getCause();
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
    public Result header(String name, String value) {
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
    public Result header(H.Header header) {
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
    public Result addHeader(String name, String... values) {
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
    public Result cookie(H.Cookie cookie) {
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
    public Result session(String name, Object val) {
        return updateKV(name, val, session(), sessionVars);
    }

    /**
     * Set flash variable
     * @param name the flash variable name
     * @param val the flash variable value
     * @return this result
     */
    public Result flash(String name, Object val) {
        return updateKV(name, val, flash(), flashVars);
    }

    private Result updateKV(String name, Object val, H.KV<?> kv, Map<String, Object> tmpStore) {
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
    public Result location(String location) {
        return this.header(H.Header.Names.LOCATION, location);
    }

    /**
     * Set a "ETag" header to this result
     * @param etag the etag value
     * @return this result
     */
    public Result etag(String etag) {
        return this.header(H.Header.Names.ETAG, etag);
    }

    /**
     * Set content type with {@link H.Format media format}
     * @param contentType the media format
     * @return this result
     */
    public Result contentType(H.Format contentType) {
        contentTypeFormat = contentType;
        return this;
    }

    /**
     * Set the "Content-Type" header to this result
     * @param contentType the content type string
     * @return this result
     */
    public Result contentType(String contentType) {
        contentTypeString = contentType;
        return this;
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
     * Apply the status of this result into response
     * @param response the response into which the status code will be applied
     */
    protected final void applyStatus(H.Response response) {
        response.status(statusCode());
    }

    /**
     * Call back listeners before commit the result into response
     * @param req the request
     * @param resp the response
     */
    private final void applyBeforeCommitHandler(H.Request req, H.Response resp) {
        MvcConfig.applyBeforeCommitResultHandler(this, req, resp);
    }

    /**
     * Call back listeners after commit the result into response
     * @param req the request
     * @param resp the response
     */
    private final void applyAfterCommitHandler(H.Request req, H.Response resp) {
        MvcConfig.applyAfterCommitResultHandler(this, req, resp);
    }

    /**
     * Apply message of this result into the response
     * @param request the request
     * @param response the response
     */
    protected void applyBody(H.Request request, H.Response response) {
        String msg = getMessage();
        if (S.notBlank(msg)) {
            response.writeContent(msg);
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

    /**
     * Create a result of the status specified
     * @param status the response status
     * @return an new result with the status
     */
    public static Result of(H.Status status) {
        if (status.isRedirect()) {
            LOGGER.warn("Constructing a general Redirect result, consider using specific factory methods for redirect instead");
        } else if (status.isError()) {
            LOGGER.warn("Constructing a general error result, consider using specific factory methods for error instead");
        }
        return new Result(status);
    }

    /**
     * Create a result of the status code specified
     * @param statusCode specify the response status
     * @return an new result with the status code
     */
    public static Result ofStatus(int statusCode) {
        return of(H.Status.of(statusCode));
    }

    private static ErrorResult errorOf(H.Status status) {
        E.illegalArgumentIf(!status.isError(), "Error status expected");
        return new ErrorResult(status);
    }

    private static ErrorResult errorOf(H.Status status, String message, Object ... args) {
        E.illegalArgumentIf(!status.isError(), "Error status expected");
        return new ErrorResult(status, message, args);
    }

    // ----------------------------------------------------------
    // 2xx Success
    // ----------------------------------------------------------

    /**
     * Create a 200 OK result
     * @return the result as described
     */
    public static Result ok() {
        return of(OK);
    }

    /**
     * Create a 201 Created result
     * @return the result as described
     */
    public static Result created() {
        return of(CREATED);
    }

    /**
     * Create a 201 Created result with location which will be set to
     * the "Location" header of this result
     *
     * @param location the URL point to the new resource that are created
     * @return the result as described
     */
    public static Result created(String location) {
        return of(CREATED).location(location);
    }

    /**
     * Create a 202 Accepted result
     * @return the result created
     */
    public static Result accepted() {
        return of(ACCEPTED);
    }

    /**
     * Create a 204 No Content result
     * @return the result created
     */
    public static Result noContent() {
        return of(NO_CONTENT);
    }

    private static RenderContent render(String content) {
        return new RenderContent(content);
    }

    /**
     * Create a 200 OK with text content
     * @param content the content
     * @return the result
     */
    public static RenderContent renderText(String content) {
        return render(content).contentType(H.Format.TXT);
    }

    /**
     * Create a 200 OK with HTML content
     * @param content the content
     * @return the result
     */
    public static RenderContent renderHtml(String content) {
        return render(content).contentType(H.Format.HTML);
    }

    /**
     * Create a 200 OK with JSON content
     * @param content the content
     * @return the result
     */
    public static RenderContent renderJson(String content) {
        return render(content).contentType(H.Format.JSON);
    }

    /**
     * Create a 200 OK with XML content
     * @param content the content
     * @return the result
     */
    public static RenderContent renderXml(String content) {
        return render(content).contentType(H.Format.XML);
    }

    /**
     * Create a 200 OK with CSV content
     * @param content the content
     * @return the result
     */
    public static RenderContent renderCsv(String content) {
        return render(content).contentType(H.Format.CSV);
    }

    // ----------------------------------------------------------
    // 3xx Redirection
    // ----------------------------------------------------------

    /**
     * Create a 301 Moved Permanently result
     * @param url the URL to which the user agent to be redirected
     * @return the result created
     */
    public static Redirect movedPermanently(String url) {
        return new Redirect(MOVED_PERMANENTLY, url);
    }

    /**
     * Create a 302 Found result
     * @param url the URL to which the user agent to be redirected
     * @return the result created
     */
    public static Redirect found(String url) {
        return new Redirect(FOUND, url);
    }

    /**
     * Create a 303 See Other result
     * @param url the URL to which the user agent to be redirected
     * @return the result created
     */
    public static Redirect seeOther(String url) {
        return new Redirect(SEE_OTHER, url);
    }

    /**
     * Create a 304 Not Modified result
     * @return the result created
     */
    public static Result notModified() {
        return of(NOT_MODIFIED);
    }

    /**
     * Create a 307 Temporary Redirect result
     * @param url the URL to which the user agent to be redirected
     * @return the result created
     */
    public static Redirect temporaryRedirect(String url) {
        return new Redirect(TEMPORARY_REDIRECT, url);
    }


    /**
     * Create a 308 Permanent Redirect result
     * @param url the URL to which the user agent to be redirected
     * @return the result created
     */
    public static Redirect permanentRedirect(String url) {
        return new Redirect(PERMANENT_REDIRECT, url);
    }

    // ----------------------------------------------------------
    // 4xx Client Error
    // ----------------------------------------------------------

    /**
     * Create a 400 Bad Request result
     * @return the result
     */
    public static ErrorResult badRequest() {
        return errorOf(BAD_REQUEST);
    }

    /**
     * Create a 400 Bad Request result with error message specified
     * @return the result
     */
    public static ErrorResult badRequest(String message, Object ... args) {
        return errorOf(BAD_REQUEST, message, args);
    }

    /**
     * Create a 401 Unauthorized result.
     *
     * This method will return a specialized `ErrorResult`: {@link Unauthorized}.
     * The result can be chained call the {@link Unauthorized#realm(String)} and
     * {@link Unauthorized#type(Unauthorized.Type)} method to set the
     * {@link H.Header.Names#WWW_AUTHENTICATE} header
     *
     * @return the result
     */
    public static Unauthorized unauthorized() {
        return new Unauthorized();
    }

    /**
     * Create a 402 Payment Required result
     * @return the result
     */
    public static ErrorResult paymentRequired() {
        return errorOf(PAYMENT_REQUIRED);
    }

    /**
     * Create a 402 Payment Required result with error message specified
     * @param message the message template
     * @param args the message arguments
     * @return the result
     */
    public static ErrorResult paymentRequired(String message, Object ... args) {
        return errorOf(PAYMENT_REQUIRED, message, args);
    }

    /**
     * Create a 403 Forbidden result
     * @return the result
     */
    public static ErrorResult forbidden() {
        return errorOf(FORBIDDEN);
    }

    /**
     * Create a 403 Forbidden result with error message specified
     * @param message the message template
     * @param args the message arguments
     * @return the result
     */
    public static ErrorResult forbidden(String message, Object ... args) {
        return errorOf(FORBIDDEN, message, args);
    }

    /**
     * Create a 404 Not Found result
     * @return the result
     */
    public static ErrorResult notFound() {
        return errorOf(NOT_FOUND);
    }

    /**
     * Create a 404 Not Found result with error message specified
     * @param message the message template
     * @param args the message arguments
     * @return the result
     */
    public static ErrorResult notFound(String message, Object ... args) {
        return errorOf(NOT_FOUND, message, args);
    }

    /**
     * Create a 405 Method Not Allowed result
     * @return the result
     */
    public static ErrorResult methodNotAllowed() {
        return errorOf(METHOD_NOT_ALLOWED);
    }

    /**
     * Create a 405 Method Not Allowed result with error message specified
     * @param message the message template
     * @param args the message arguments
     * @return the result
     */
    public static ErrorResult methodNotAllowed(String message, Object ... args) {
        return errorOf(METHOD_NOT_ALLOWED, message, args);
    }

    /**
     * Create a 406 Not Acceptable result
     * @return the result
     */
    public static ErrorResult notAcceptable() {
        return errorOf(NOT_ACCEPTABLE);
    }

    /**
     * Create a 406 Not Acceptable result with error message specified
     * @param message the message template
     * @param args the message arguments
     * @return the result
     */
    public static ErrorResult notAcceptable(String message, Object ... args) {
        return errorOf(NOT_ACCEPTABLE, message, args);
    }


    /**
     * Create a 408 Request Timeout result
     * @return the result
     */
    public static ErrorResult requestTimeout() {
        return errorOf(REQUEST_TIMEOUT);
    }

    /**
     * Create a 408 Request Timeout result with error message specified
     * @param message the message template
     * @param args the message arguments
     * @return the result
     */
    public static ErrorResult requestTimeout(String message, Object ... args) {
        return errorOf(REQUEST_TIMEOUT, message, args);
    }


    /**
     * Create a 409 Conflict result
     * @return the result
     */
    public static ErrorResult conflict() {
        return errorOf(CONFLICT);
    }

    /**
     * Create a 409 Conflict result with error message specified
     * @param message the message template
     * @param args the message arguments
     * @return the result
     */
    public static ErrorResult conflict(String message, Object ... args) {
        return errorOf(CONFLICT, message, args);
    }

    /**
     * Create a 410 Gone result
     * @return the result
     */
    public static ErrorResult gone() {
        return errorOf(GONE);
    }

    /**
     * Create a 410 Gone result with error message specified
     * @param message the message template
     * @param args the message arguments
     * @return the result
     */
    public static ErrorResult gone(String message, Object ... args) {
        return errorOf(GONE, message, args);
    }


    /**
     * Create a 412 Precondition Failed result
     * @return the result
     */
    public static ErrorResult preconditionFailed() {
        return errorOf(PRECONDITION_FAILED);
    }

    /**
     * Create a 412 Precondition Failed result with error message specified
     * @param message the message template
     * @param args the message arguments
     * @return the result
     */
    public static ErrorResult preconditionFailed(String message, Object ... args) {
        return errorOf(PRECONDITION_FAILED, message, args);
    }


    /**
     * Create a 415 Unsupported Media Type result
     * @return the result
     */
    public static ErrorResult unsupportedMediaType() {
        return errorOf(UNSUPPORTED_MEDIA_TYPE);
    }

    /**
     * Create a 415 Unsupported Media Type result with error message specified
     * @param message the message template
     * @param args the message arguments
     * @return the result
     */
    public static ErrorResult unsupportedMediaType(String message, Object ... args) {
        return errorOf(UNSUPPORTED_MEDIA_TYPE, message, args);
    }


    /**
     * Create a 417 Expectation Failed result
     * @return the result
     */
    public static ErrorResult expectationFailed() {
        return errorOf(EXPECTATION_FAILED);
    }

    /**
     * Create a 417 Expectation Failed result with error message specified
     * @param message the message template
     * @param args the message arguments
     * @return the result
     */
    public static ErrorResult expectationFailed(String message, Object ... args) {
        return errorOf(EXPECTATION_FAILED, message, args);
    }


    /**
     * Create a 422 Unprocessable Entity result
     * @return the result
     */
    public static ErrorResult unprocessableEntity() {
        return errorOf(UNPROCESSABLE_ENTITY);
    }

    /**
     * Create a 422 Unprocessable Entity result with error message specified
     * @param message the message template
     * @param args the message arguments
     * @return the result
     */
    public static ErrorResult unprocessableEntity(String message, Object ... args) {
        return errorOf(UNPROCESSABLE_ENTITY, message, args);
    }


    /**
     * Create a 423 Locked result
     * @return the result
     */
    public static ErrorResult locked() {
        return errorOf(LOCKED);
    }

    /**
     * Create a 423 Locked result with error message specified
     * @param message the message template
     * @param args the message arguments
     * @return the result
     */
    public static ErrorResult locked(String message, Object ... args) {
        return errorOf(LOCKED, message, args);
    }


    /**
     * Create a 424 Failed Dependency result
     * @return the result
     */
    public static ErrorResult failedDependency() {
        return errorOf(FAILED_DEPENDENCY);
    }

    /**
     * Create a 424 Failed Dependency result with error message specified
     * @param message the message template
     * @param args the message arguments
     * @return the result
     */
    public static ErrorResult failedDependency(String message, Object ... args) {
        return errorOf(FAILED_DEPENDENCY, message, args);
    }


    /**
     * Create a 428 Precondition Required result
     * @return the result
     */
    public static ErrorResult preconditionRequired() {
        return errorOf(PRECONDITION_REQUIRED);
    }

    /**
     * Create a 428 Precondition Required result with error message specified
     * @param message the message template
     * @param args the message arguments
     * @return the result
     */
    public static ErrorResult preconditionRequired(String message, Object ... args) {
        return errorOf(PRECONDITION_REQUIRED, message, args);
    }


    /**
     * Create a 429 Too Many Requests result
     * @return the result
     */
    public static ErrorResult tooManyRequests() {
        return errorOf(TOO_MANY_REQUESTS);
    }

    /**
     * Create a 429 Too Many Requests result with error message specified
     * @param message the message template
     * @param args the message arguments
     * @return the result
     */
    public static ErrorResult tooManyRequests(String message, Object ... args) {
        return errorOf(TOO_MANY_REQUESTS, message, args);
    }


    /**
     * Create a 451 Unavailable for legal reasons result
     * @return the result
     */
    public static ErrorResult unavailableForLegalReasons() {
        return errorOf(UNAVAILABLE_FOR_LEGAL_REASONS);
    }

    /**
     * Create a 451 Unavailable for legal reasons result with error message specified
     * @param message the message template
     * @param args the message arguments
     * @return the result
     */
    public static ErrorResult unavailableForLegalReasons(String message, Object ... args) {
        return errorOf(UNAVAILABLE_FOR_LEGAL_REASONS, message, args);
    }

    // ----------------------------------------------------------
    // 5xx Server Error
    // ----------------------------------------------------------


    /**
     * Create a 500 Internal Server Error result
     * @return the result
     */
    public static ErrorResult internalServerError() {
        return errorOf(INTERNAL_SERVER_ERROR);
    }

    /**
     * Create a 500 Internal Server Error result with error message specified
     * @param message the message template
     * @param args the message arguments
     * @return the result
     */
    public static ErrorResult internalServerError(String message, Object ... args) {
        return errorOf(INTERNAL_SERVER_ERROR, message, args);
    }

    /**
     * Create a 500 Internal Server Error result with cause and error message specified
     * @param cause the cause of the server error
     * @param message the message template
     * @param args the message arguments
     * @return the result
     */
    public static ErrorResult internalServerError(Throwable cause, String message, Object ... args) {
        return errorOf(INTERNAL_SERVER_ERROR, message, args).initCause(cause);
    }


    /**
     * Create a 501 Not Implemented result
     * @return the result
     */
    public static ErrorResult notImplemented() {
        return errorOf(NOT_IMPLEMENTED);
    }

    /**
     * Create a 501 Not Implemented result with error message specified
     * @param message the message template
     * @param args the message arguments
     * @return the result
     */
    public static ErrorResult notImplemented(String message, Object ... args) {
        return errorOf(NOT_IMPLEMENTED, message, args);
    }

    /**
     * Create a 501 Not Implemented result with cause and error message specified
     * @param cause the cause of this error
     * @param message the message template
     * @param args the message arguments
     * @return the result
     */
    public static ErrorResult notImplemented(Throwable cause, String message, Object ... args) {
        return errorOf(NOT_IMPLEMENTED, message, args).initCause(cause);
    }



    /**
     * Create a 507 Insufficient Storage result
     * @return the result
     */
    public static ErrorResult insufficientStorage() {
        return errorOf(INSUFFICIENT_STORAGE);
    }

    /**
     * Create a 507 Insufficient Storage result with error message specified
     * @param message the message template
     * @param args the message arguments
     * @return the result
     */
    public static ErrorResult insufficientStorage(String message, Object ... args) {
        return errorOf(INSUFFICIENT_STORAGE, message, args);
    }

    /**
     * Create a 507 Insufficient Storage result with cause and error message specified
     * @param cause the cause of this error
     * @param message the message template
     * @param args the message arguments
     * @return the result
     */
    public static ErrorResult insufficientStorage(Throwable cause, String message, Object ... args) {
        return errorOf(INSUFFICIENT_STORAGE, message, args).initCause(cause);
    }
}
