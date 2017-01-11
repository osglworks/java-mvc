package org.osgl.mvc;

import org.osgl.$;
import org.osgl.Osgl;
import org.osgl.exception.NotAppliedException;
import org.osgl.http.H;
import org.osgl.http.HttpConfig;
import org.osgl.mvc.result.Result;
import org.osgl.util.E;
import org.osgl.util.S;

public class MvcConfig extends HttpConfig {

    public static final String MSG_ID_FORBIDDEN = "osgl.result.forbidden";
    public static final String MSG_ID_BAD_REQUEST = "osgl.result.bad_request";
    public static final String MSG_ID_CONFLICT = "osgl.result.conflict";
    public static final String MSG_ID_METHOD_NOT_ALLOWED = "osgl.result.method_not_allowed";
    public static final String MSG_ID_NOT_FOUND = "osgl.result.not_found";
    public static final String MSG_ID_NOT_IMPLEMENTED = "osgl.result.not_implemented";
    public static final String MSG_ID_SERVER_ERROR = "osgl.result.server_error";

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
}
