package org.osgl.mvc.result;

import org.osgl.http.H;
import org.osgl.http.Http;
import org.osgl.http.util.Path;
import org.osgl.util.E;
import org.osgl.util.IO;
import org.osgl.util.S;

public class Redirect extends Result {

    private static Redirect _INSTANCE = new Redirect() {
        @Override
        protected String url() {
            return payload().message;
        }
    };

    private static Redirect _MOVED = new Redirect(true) {
        @Override
        protected String url() {
            return payload().message;
        }
    };

    protected String url;

    private Redirect() {
        super(Http.Status.FOUND);
    }

    private Redirect(boolean permanent) {
        super(permanent ? Http.Status.MOVED_PERMANENTLY : H.Status.FOUND);
    }

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
        try {
            String url = fullUrl(req);
            if (req.isAjax()) {
                resp.status(H.Status.FOUND_AJAX);
            } else {
                applyStatus(resp);
            }
            resp.header("Location", url);
            applyBeforeCommitHandler(req, resp);
            IO.close(resp.outputStream());
            applyAfterCommitHandler(req, resp);
        } finally {
            clearThreadLocals();
        }
    }

    protected String fullUrl(H.Request request) {
        return Path.fullUrl(this.url(), request);
    }

    protected String url() {
        return url;
    }

    public static Redirect of(String url) {
        payload.get().message(url);
        return _INSTANCE;
    }

    public static Redirect of(String url, Object... args) {
        payload.get().message(url, args);
        return _INSTANCE;
    }

    public static Redirect moved(String url) {
        payload.get().message(url);
        return _MOVED;
    }

    public static Redirect moved(String url, Object... args) {
        payload.get().message(url, args);
        return _MOVED;
    }
}
