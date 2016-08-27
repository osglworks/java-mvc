package org.osgl.mvc.result;

import org.osgl.http.H;
import org.osgl.util.IO;

/**
 * Do nothing render result
 */
public class NoResult extends Result {
    public NoResult() {
    }

    @Override
    public void apply(H.Request req, H.Response resp) {
        applyBeforeCommitHandler(req, resp);
        IO.close(resp.outputStream());
        applyAfterCommitHandler(req, resp);
    }
}
