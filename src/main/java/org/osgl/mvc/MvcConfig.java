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
    public static void setCookiePrefix(String prefix) {
        E.illegalArgumentIf(S.empty(prefix));
        sessionCookieName = prefix + "_SESSION";
        flashCookieName = prefix = "_FLASH";
    }

    /**
     * Set session expire in seconds
     *
     * @param ttl the session expire in seconds
     */
    public static void setSessionExpire(int ttl) {
        E.illegalArgumentIf(ttl < 0);
        sessionExpire = ttl;
    }

    public static void setSecret(String secret) {
        E.illegalArgumentIf(S.empty(secret));
        MvcConfig.secret = secret;
    }


    private static ErrorPageRenderer errorPageRenderer = new ErrorPageRenderer();

    public static void setErrorPageRenderer(ErrorPageRenderer renderer) {
        E.NPE(renderer);
        errorPageRenderer = renderer;
    }

    public static ErrorPageRenderer errorPageRenderer() {
        return errorPageRenderer;
    }
}
