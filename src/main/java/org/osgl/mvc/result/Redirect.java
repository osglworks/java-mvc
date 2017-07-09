package org.osgl.mvc.result;

import org.osgl.http.H;
import org.osgl.util.E;

/**
 * Encapsulate {@link RichResult} with status code of
 *
 * * 301 Moved Permanently
 * * 302 Found
 * * 303 See Other
 * * 307 Temporary Redirect
 * * 308 Permanent Redirect
 */
public class Redirect extends RichResult {

    /**
     * Construct a `Redirect` with status code and the URL the user agent shall be redirected
     * @param status the redirect status, must be one of 301, 302, 303, 307 and 308
     * @param url the URL to be redirected
     */
    public Redirect(H.Status status, String url) {
        super(validateStatusCode(status));
        location(url);
        // TODO set default message
        // See requirement defined
        // at http://www.restpatterns.org/HTTP_Status_Codes/301_-_Moved_Permanently
    }

    private static H.Status validateStatusCode(H.Status status) {
        int statusCode = status.code();
        E.illegalArgumentIf(statusCode < 300 || statusCode > 399, "Invalid redirect status code: " + statusCode);
        return status;
    }
}
