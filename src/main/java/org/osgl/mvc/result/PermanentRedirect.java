package org.osgl.mvc.result;

import org.osgl.http.Http;
import org.osgl.util.S;

public class PermanentRedirect extends RedirectBase {

    private static PermanentRedirect _INSTANCE = new PermanentRedirect() {
        @Override
        protected String url() {
            return payload().message;
        }
    };

    private PermanentRedirect() {
        super(Http.Status.PERMANENT_REDIRECT);
    }

    public PermanentRedirect(String url) {
        super(Http.Status.PERMANENT_REDIRECT, url);
    }

    public PermanentRedirect(String url, Object... args) {
        this(S.fmt(url, args));
    }

    public static PermanentRedirect of(String url) {
        payload.get().message(url);
        return _INSTANCE;
    }

    public static PermanentRedirect of(String url, Object... args) {
        payload.get().message(url, args);
        return _INSTANCE;
    }

}
