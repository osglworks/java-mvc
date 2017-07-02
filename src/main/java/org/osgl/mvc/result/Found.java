package org.osgl.mvc.result;

import org.osgl.http.Http;
import org.osgl.util.S;

public class Found extends RedirectBase {

    private static Found _INSTANCE = new Found() {
        @Override
        protected String url() {
            return payload().message;
        }
    };

    private Found() {
        super(Http.Status.FOUND);
    }

    public Found(String url) {
        super(Http.Status.FOUND, url);
    }

    public Found(String url, Object... args) {
        this(S.fmt(url, args));
    }

    public static Found of(String url) {
        payload.get().message(url);
        return _INSTANCE;
    }

    public static Found of(String url, Object... args) {
        payload.get().message(url, args);
        return _INSTANCE;
    }

}
