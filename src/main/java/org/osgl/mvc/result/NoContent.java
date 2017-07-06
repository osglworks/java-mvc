package org.osgl.mvc.result;

import org.osgl.http.H;

/**
 * Render NO_CONTENT response
 */
public class NoContent extends Result {

    public static NoContent INSTANCE = new NoContent();

    protected NoContent() {
        super(H.Status.NO_CONTENT);
    }

    @Override
    public void apply(H.Request req, H.Response resp) {
        try {
            applyStatus(resp);
            applyCookies(resp);
            applyHeaders(resp);
            applyBeforeCommitHandler(req, resp);
            resp.commit();
            applyAfterCommitHandler(req, resp);
        } finally {
            clearThreadLocals();
        }
    }

    public static NoContent get() {
        return INSTANCE;
    }
}
