package org.osgl.mvc.result;

import org.osgl.http.H;
import org.osgl.http.Http;
import org.osgl.http.util.Path;
import org.osgl.util.E;
import org.osgl.util.IO;
import org.osgl.util.S;

public class Redirect extends Result {
    String url;
    public Redirect(String url) {
        super(Http.Status.FOUND);
        E.illegalArgumentIf(S.blank(url));
        this.url = url;
    }

    public Redirect(String url, Object... args) {
        this(S.fmt(url, args));
    }

    public Redirect(boolean permanent, String url) {
        super(permanent ? Http.Status.MOVED_PERMANENTLY : H.Status.FOUND);
        this.url = url;
    }

    public Redirect(boolean permanent, String url, Object... args) {
        this(permanent, S.fmt(url, args));
    }

    @Override
    public void apply(H.Request req, H.Response resp) {
        String url = Path.fullUrl(this.url, req);
        if (req.isAjax()) {
            resp.status(H.Status.FOUND_AJAX);
        } else {
            applyStatus(resp);
        }
        resp.header("Location", url);
        applyBeforeCommitHandler(req, resp);
        IO.close(resp.outputStream());
        applyAfterCommitHandler(req, resp);
    }
}
