package org.osgl.mvc.result;

import org.osgl.exception.FastRuntimeException;
import org.osgl.http.H;
import org.osgl.http.Http;
import org.osgl.logging.LogManager;
import org.osgl.logging.Logger;
import org.osgl.mvc.MvcConfig;
import org.osgl.util.E;
import org.osgl.util.S;

import static org.osgl.http.H.Status.*;

/**
 * The base class of all MVC results in which the data can be applied to
 * {@link H.Response}
 *
 * The `Result` is modeled as a {@link FastRuntimeException} sub class so that the
 * program can either `return` or `throw` the result back to control point.
 */
public class Result extends FastRuntimeException {

    /**
     * Singleton instance for an empty {@link H.Status#OK} result
     */
    public static final Result OK = of(H.Status.OK);

    /**
     * Singleton instance for an empty {@link H.Status#CREATED} result
     */
    public static final Result CREATED = of(H.Status.CREATED);

    /**
     * Singleton instance for an empty {@link H.Status#CREATED} result
     */
    public static final Result ACCEPTED = of(H.Status.ACCEPTED);

    /**
     * Singleton instance for an empty {@link H.Status#NO_CONTENT} result
     */
    public static final Result NO_CONTENT = of(H.Status.NO_CONTENT);

    /**
     * Singleton instance for an empty {@link H.Status#NOT_MODIFIED} result
     */
    public static final Result NOT_MODIFIED = of(H.Status.NOT_MODIFIED);

    /**
     * Singleton instance for an empty {@link H.Status#BAD_REQUEST} result
     */
    public static final Result BAD_REQUEST = of(H.Status.BAD_REQUEST);

    /**
     * Singleton instance for an empty {@link H.Status#UNAUTHORIZED} result
     */
    public static final Result UNAUTHORIZED = of(H.Status.UNAUTHORIZED);

    /**
     * Singleton instance for an empty {@link H.Status#FORBIDDEN} result
     */
    public static final Result FORBIDDEN = of(H.Status.FORBIDDEN);

    /**
     * Singleton instance for an empty {@link H.Status#NOT_FOUND} result
     */
    public static final Result NOT_FOUND = of(H.Status.NOT_FOUND);

    /**
     * Singleton instance for an empty {@link H.Status#CONFLICT} result
     */
    public static final Result CONFLICT = of(H.Status.CONFLICT);

    private static final Logger LOGGER = LogManager.get(Result.class);

    /**
     * The response status
     */
    protected Http.Status status;

    /**
     * Construct a Result with {@link H.Status#OK} status
     */
    public Result() {
        this(H.Status.OK);
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
                + "\"}";
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
     * Returns the cause of this result
     * @return the cause
     */
    public final Throwable cause() {
        return getCause();
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
    protected final void applyBeforeCommitHandler(H.Request req, H.Response resp) {
        MvcConfig.applyBeforeCommitResultHandler(this, req, resp);
    }

    /**
     * Call back listeners after commit the result into response
     * @param req the request
     * @param resp the response
     */
    protected final void applyAfterCommitHandler(H.Request req, H.Response resp) {
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
        try {
            applyStatus(resp);
            applyBeforeCommitHandler(req, resp);
            applyBody(req, resp);
        } finally {
            resp.commit();
            applyAfterCommitHandler(req, resp);
        }
    }


    /**
     * Create a result of the status specified
     * @param status the response status
     * @return an new result with the status
     */
    public static RichResult of(H.Status status) {
        if (status.isRedirect()) {
            LOGGER.warn("Constructing a general Redirect result, consider using specific factory methods for redirect instead");
        } else if (status.isError()) {
            LOGGER.warn("Constructing a general error result, consider using specific factory methods for error instead");
        }
        return new RichResult(status);
    }

    /**
     * Create a result of the status code specified
     * @param statusCode specify the response status
     * @return an new result with the status code
     */
    public static RichResult ofStatus(int statusCode) {
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
    public static RichResult ok() {
        return of(H.Status.OK);
    }

    /**
     * Create a 201 Created result
     * @return the result as described
     */
    public static RichResult created() {
        return of(H.Status.CREATED);
    }

    /**
     * Create a 201 Created result with location which will be set to
     * the "Location" header of this result
     *
     * @param location the URL point to the new resource that are created
     * @return the result as described
     */
    public static RichResult created(String location) {
        return of(H.Status.CREATED).location(location);
    }

    /**
     * Create a 202 Accepted result
     * @return the result created
     */
    public static RichResult accepted() {
        return of(H.Status.ACCEPTED);
    }

    /**
     * Create a 204 No Content result
     * @return the result created
     */
    public static RichResult noContent() {
        return of(H.Status.NO_CONTENT);
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
    public static RichResult notModified() {
        return of(H.Status.NOT_MODIFIED);
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
        return errorOf(H.Status.BAD_REQUEST);
    }

    /**
     * Create a 400 Bad Request result with error message specified
     * @return the result
     */
    public static ErrorResult badRequest(String message, Object ... args) {
        return errorOf(H.Status.BAD_REQUEST, message, args);
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
        return errorOf(H.Status.FORBIDDEN);
    }

    /**
     * Create a 403 Forbidden result with error message specified
     * @param message the message template
     * @param args the message arguments
     * @return the result
     */
    public static ErrorResult forbidden(String message, Object ... args) {
        return errorOf(H.Status.FORBIDDEN, message, args);
    }

    /**
     * Create a 404 Not Found result
     * @return the result
     */
    public static ErrorResult notFound() {
        return errorOf(H.Status.NOT_FOUND);
    }

    /**
     * Create a 404 Not Found result with error message specified
     * @param message the message template
     * @param args the message arguments
     * @return the result
     */
    public static ErrorResult notFound(String message, Object ... args) {
        return errorOf(H.Status.NOT_FOUND, message, args);
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
        return errorOf(H.Status.CONFLICT);
    }

    /**
     * Create a 409 Conflict result with error message specified
     * @param message the message template
     * @param args the message arguments
     * @return the result
     */
    public static ErrorResult conflict(String message, Object ... args) {
        return errorOf(H.Status.CONFLICT, message, args);
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
