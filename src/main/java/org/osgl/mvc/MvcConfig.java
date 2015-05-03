package org.osgl.mvc;

import org.osgl.http.HttpConfig;
import org.osgl.util.E;
import org.osgl.util.S;

/**
 * Created by luog on 25/03/14.
 */
public class MvcConfig extends HttpConfig {

    public static final String DEF_COOKIE_PREFIX = "OSGL";

    static String sessionCookieName = DEF_COOKIE_PREFIX + "_SESSION";
    static String flashCookieName = DEF_COOKIE_PREFIX + "_FLASH";
    static int sessionExpire = -1;
    static String secret;

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


    private static ErrorPageRenderer errorPageRenderer = new ErrorPageRenderer();

    public static void errorPageRenderer(ErrorPageRenderer renderer) {
        E.NPE(renderer);
        errorPageRenderer = renderer;
    }

    public static ErrorPageRenderer errorPageRenderer() {
        return errorPageRenderer;
    }
}
