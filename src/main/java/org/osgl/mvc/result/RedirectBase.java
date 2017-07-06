package org.osgl.mvc.result;

import org.osgl.http.H;
import org.osgl.http.util.Path;
import org.osgl.util.S;

/**
 * Base class for different Redirect result
 */
public abstract class RedirectBase extends Result {
    protected String url;

    protected RedirectBase(H.Status status) {
        super(status);
    }

    public RedirectBase(H.Status status, String url) {
        super(status);
        this.url = url.trim();
    }

    public RedirectBase(H.Status status, String url, Object ... args) {
        super(status);
        this.url = S.fmt(url, args);
    }

    @Override
    public final void apply(H.Request req, H.Response resp) {
        try {
            _applyStatus(req, resp);
            applyCookies(resp);
            applyHeaders(resp);
            String url = fullUrl(req);
            resp.header("Location", url);
            applyBeforeCommitHandler(req, resp);
            resp.commit();
            applyAfterCommitHandler(req, resp);
        } finally {
            clearThreadLocals();
        }
    }

    protected String url() {
        return url;
    }

    protected void _applyStatus(H.Request request, H.Response response) {
        applyStatus(response);
    }

    protected String fullUrl(H.Request request) {
        return Path.fullUrl(this.url(), request);
    }


}
