package org.osgl.mvc.result;

import org.osgl.http.H;

/**
 * Unlike normal {@link H.Status#FOUND Found redirect} result, `LoginRedirect` will
 * put status code as {@link H.Status.Code#FOUND_AJAX} for AJAX request and
 * {@link H.Status.Code#FOUND} for non-AJAX request
 */
public class LoginRedirect extends Redirect {

    /**
     * Construct a `Redirect` with status code and the URL the user agent shall be redirected
     * @param url the URL to be redirected
     */
    public LoginRedirect(String url) {
        super(H.Status.FOUND, url);
        location(url);
        // TODO set default message
        // See requirement defined
        // at http://www.restpatterns.org/HTTP_Status_Codes/301_-_Moved_Permanently
    }

    @Override
    protected void prepareStatus(H.Context context) {
        if (context.req().isAjax()) {
            overwriteStatus(H.Status.FOUND_AJAX);
        }
    }

}
