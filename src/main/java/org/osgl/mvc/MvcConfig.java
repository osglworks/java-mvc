package org.osgl.mvc;

import org.osgl.$;
import org.osgl.Osgl;
import org.osgl.exception.NotAppliedException;
import org.osgl.http.H;
import org.osgl.http.HttpConfig;
import org.osgl.mvc.result.Result;
import org.osgl.util.C;
import org.osgl.util.E;
import org.osgl.util.S;

import java.util.Map;

import static org.osgl.http.H.Status.*;

public class MvcConfig extends HttpConfig {

    public static final String MSG_ID_CREATED = "osgl.result.created";
    public static final String MSG_ID_ACCEPTED = "osgl.result.accepted";

    public static final String MSG_ID_CLIENT_ERROR = "osgl.result.client_error";
    public static final String MSG_ID_BAD_REQUEST = "osgl.result.bad_request";
    public static final String MSG_ID_UNAUTHORIZED = "osgl.result.unauthorized";
    public static final String MSG_ID_PAYMENT_REQUIRED = "osgl.result.payment_required";
    public static final String MSG_ID_FORBIDDEN = "osgl.result.forbidden";
    public static final String MSG_ID_NOT_FOUND = "osgl.result.not_found";
    public static final String MSG_ID_METHOD_NOT_ALLOWED = "osgl.result.method_not_allowed";
    public static final String MSG_ID_NOT_ACCEPTABLE = "osgl.result.not_acceptable";
    public static final String MSG_ID_PROXY_AUTHENTICATION_REQUIRED = "osgl.result.proxy_authentication_required";
    public static final String MSG_ID_REQUREST_TIMEOUT = "osgl.result.requrest_timeout";
    public static final String MSG_ID_CONFLICT = "osgl.result.conflict";

    public static final String MSG_ID_SERVER_ERROR = "osgl.result.server_error";
    public static final String MSG_ID_NOT_IMPLEMENTED = "osgl.result.not_implemented";

    private static final Map<H.Status, String> messageMap = C.map(
            ACCEPTED, MSG_ID_ACCEPTED,
            CREATED, MSG_ID_CREATED,
            BAD_REQUEST, MSG_ID_BAD_REQUEST,
            UNAUTHORIZED, MSG_ID_UNAUTHORIZED,
            PAYMENT_REQUIRED, MSG_ID_PAYMENT_REQUIRED,
            FORBIDDEN, MSG_ID_FORBIDDEN,
            NOT_FOUND, MSG_ID_NOT_FOUND,
            METHOD_NOT_ALLOWED, MSG_ID_METHOD_NOT_ALLOWED,
            NOT_ACCEPTABLE, MSG_ID_NOT_ACCEPTABLE,
            PROXY_AUTHENTICATION_REQUIRED, MSG_ID_PROXY_AUTHENTICATION_REQUIRED,
            REQUEST_TIMEOUT, MSG_ID_REQUREST_TIMEOUT,
            CONFLICT, MSG_ID_CONFLICT,
            NOT_IMPLEMENTED, MSG_ID_NOT_IMPLEMENTED
    );

    // Stores English error message that are not defined in separate ErrorResult implementation, e.g. NotFound
    private static final Map<H.Status, String> enMessageMap = C.map(
            CREATED, "Created",
            ACCEPTED, "Accepted",
            BAD_REQUEST, "Bad Request",
            PAYMENT_REQUIRED, "Payment Required",
            FORBIDDEN, "Forbidden",
            NOT_FOUND, "Not Found",
            METHOD_NOT_ALLOWED, "Method Not Allowed",
            NOT_ACCEPTABLE, "Not Acceptable",
            PROXY_AUTHENTICATION_REQUIRED, "Proxy Authentication Required",
            REQUEST_TIMEOUT, "Request Timeout",
            CONFLICT, "Conflict",
            NOT_IMPLEMENTED, "Not Implemented"
    );

    public static final String DEF_COOKIE_PREFIX = "OSGL";

    private static final $.Func3<Result, H.Request<?>, H.Response<?>, Void> DUMB_COMMIT_RESULT_LISTENER =
            new $.F3<Result, H.Request<?>, H.Response<?>, Void>() {
                @Override
                public Void apply(Result result, H.Request request, H.Response response) throws NotAppliedException, Osgl.Break {
                    return null;
                }
            };

    static String sessionCookieName = DEF_COOKIE_PREFIX + "_SESSION";
    static String flashCookieName = DEF_COOKIE_PREFIX + "_FLASH";
    static int sessionExpire = -1;
    static String secret;
    static $.Function<Object, String> jsonSerializer;
    static $.Func3<Result, H.Request<?>, H.Response<?>, ?> beforeCommitResultHandler = DUMB_COMMIT_RESULT_LISTENER;
    static $.Func3<Result, H.Request<?>, H.Response<?>, ?> afterCommitResultHandler = DUMB_COMMIT_RESULT_LISTENER;
    static $.Function<String, String> messageTranlater = $.F.identity();

    /**
     * Set the cookie prefix for session and flash cookie
     *
     * @param prefix the prefix to prepend to session/flash cookie name
     */
    public static void cookiePrefix(String prefix) {
        E.illegalArgumentIf(S.blank(prefix));
        sessionCookieName = prefix + "_SESSION";
        flashCookieName = prefix = "_FLASH";
    }

    /**
     * Set session expire in seconds
     *
     * @param ttl the session expire in seconds
     */
    public static void sessionExpire(int ttl) {
        E.illegalArgumentIf(ttl < 0);
        sessionExpire = ttl;
    }

    public static void secret(String secret) {
        E.illegalArgumentIf(S.blank(secret));
        MvcConfig.secret = secret;
    }

    public static void jsonSerializer($.Function<Object, String> serializer) {
        MvcConfig.jsonSerializer = $.notNull(serializer);
    }

    public static void beforeCommitResultHandler($.Func3<Result, H.Request<?>, H.Response<?>, ?> beforeCommitResultHandler) {
        MvcConfig.beforeCommitResultHandler = $.notNull(beforeCommitResultHandler);
    }

    public static void applyBeforeCommitResultHandler(Result result, H.Request req, H.Response resp) {
        beforeCommitResultHandler.apply(result, req, resp);
    }

    public static void afterCommitResultHandler($.Func3<Result, H.Request<?>, H.Response<?>, ?> afterCommitResultHandler) {
        MvcConfig.afterCommitResultHandler = $.notNull(afterCommitResultHandler);
    }

    public static void applyAfterCommitResultHandler(Result result, H.Request<?> req, H.Response<?> resp) {
        afterCommitResultHandler.apply(result, req, resp);
    }

    public static void messageTranslater($.Function<String, String> translater) {
        MvcConfig.messageTranlater = $.notNull(translater);
    }

    public static $.Function<String, String> messageTranslater() {
        return messageTranlater;
    }

    public static $.Function<Object, String> jsonSerializer() {
        return jsonSerializer;
    }

    private static ErrorPageRenderer errorPageRenderer = new ErrorPageRenderer();

    public static void errorPageRenderer(ErrorPageRenderer renderer) {
        E.NPE(renderer);
        errorPageRenderer = renderer;
    }

    public static ErrorPageRenderer errorPageRenderer() {
        return errorPageRenderer;
    }

    private static boolean localizedErrorMsg = false;

    public static void enableLocalizedErrorMsg() {
        localizedErrorMsg = true;
    }

    public static boolean localizedErrorMsg() {
        return localizedErrorMsg;
    }

    /**
     * Returns default error message id from http status
     *
     * @param status the http status
     * @return the corresponding error message id
     */
    public static String errorMessage(H.Status status) {
        boolean i18n = MvcConfig.localizedErrorMsg();
        String msg = i18n ? messageMap.get(status) : enMessageMap.get(status);

        if (null == msg) {
            if (status.isClientError()) {
                msg = i18n ? MSG_ID_CLIENT_ERROR : "Client Error";
            } else if (status.isServerError()) {
                msg = i18n ? MSG_ID_SERVER_ERROR : "Server Error";
            } else {
                msg = "Unknown status";
            }
        }

        return msg;
    }

    /**
     * Returns default error message id from http status code
     *
     * @param code the http status code
     * @return the corresponding error message id
     */
    public static String errorMessage(int code) {
        return errorMessage(H.Status.of(code));
    }
}
