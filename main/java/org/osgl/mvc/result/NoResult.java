package org.osgl.mvc.result;

import org.osgl.http.H;

/**
 * Do nothing render result
 */
public class NoResult extends Result {
    public NoResult() {
    }

    @Override
    public void apply(H.Request req, H.Response resp) {
    }
}
