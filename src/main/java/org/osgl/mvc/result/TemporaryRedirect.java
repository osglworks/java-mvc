package org.osgl.mvc.result;

import org.osgl.http.Http;
import org.osgl.util.S;

public class TemporaryRedirect extends RedirectBase {

    private static TemporaryRedirect _INSTANCE = new TemporaryRedirect() {
        @Override
        protected String url() {
            return payload().message;
        }
    };

    private TemporaryRedirect() {
        super(Http.Status.TEMPORARY_REDIRECT);
    }

    public TemporaryRedirect(String url) {
        super(Http.Status.TEMPORARY_REDIRECT, url);
    }

    public TemporaryRedirect(String url, Object... args) {
        this(S.fmt(url, args));
    }

    public static TemporaryRedirect of(String url) {
        payload().message(url);
        return _INSTANCE;
    }

    public static TemporaryRedirect of(String url, Object... args) {
        payload().message(url, args);
        return _INSTANCE;
    }

}
