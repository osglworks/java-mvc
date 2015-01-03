package org.osgl.mvc.result;

import org.osgl.http.H;
import org.osgl.http.Http;
import org.osgl.http.util.Path;
import org.osgl.util.E;
import org.osgl.util.S;

public class Redirect extends Result {
    String url;
    public Redirect(String url) {
        super(Http.Status.FOUND);
        E.illegalArgumentIf(S.empty(url));
        this.url = url;
    }

    public Redirect(String url, Object... args) {
        this(S.fmt(url, args));
    }

    public Redirect(boolean permanent, String url) {
        super(Http.Status.MOVED_PERMANENTLY);
        this.url = url;
    }

    public Redirect(boolean permanent, String url, Object... args) {
        this(permanent, S.fmt(url, args));
    }

    @Override
    public void apply(H.Request req, H.Response resp) {
        String url = Path.fullUrl(this.url, req);
        applyStatus(resp);
        resp.header(H.Header.Names.LOCATION, url);
    }
}
