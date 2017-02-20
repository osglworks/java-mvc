package org.osgl.mvc.result;

import org.osgl.http.H;
import org.osgl.util.IO;

/**
 * Render NO_CONTENT response
 */
public class NoResult extends Result {

    public static NoResult INSTANCE = new NoResult();

    private NoResult() {
        super(H.Status.NO_CONTENT);
    }

    @Override
    public void apply(H.Request req, H.Response resp) {
        try {
            applyBeforeCommitHandler(req, resp);
            super.applyStatus(resp);
            resp.commit();
            applyAfterCommitHandler(req, resp);
        } finally {
            clearThreadLocals();
        }
    }

    public static NoResult get() {
        return INSTANCE;
    }
}
