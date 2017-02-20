package org.osgl.mvc.result;

import org.osgl.http.H;
import org.osgl.util.S;

public class RenderHtml extends RenderContent {

    private static RenderHtml _INSTANCE = new RenderHtml() {
        @Override
        public String content() {
            return payload().message;
        }
    };

    private RenderHtml() {
        super(H.Format.HTML);
    }

    public RenderHtml(String html) {
        super(html, H.Format.HTML);
    }

    public RenderHtml(String html, Object ... args) {
        super(S.fmt(html, args), H.Format.HTML);
    }

    public RenderHtml(H.Status status, String html) {
        super(status, html, H.Format.HTML);
    }

    public RenderHtml(H.Status status, String html, Object ... args) {
        super(status, S.fmt(html, args), H.Format.HTML);
    }

    public static RenderHtml of(String html) {
        payload.get().message(html);
        return _INSTANCE;
    }

    public static RenderHtml of(String html, Object... args) {
        payload.get().message(html, args);
        return _INSTANCE;
    }

    public static RenderHtml of(H.Status status, String html) {
        payload.get().message(html).status(status);
        return _INSTANCE;
    }

    public static RenderHtml of(H.Status status, String html, Object... args) {
        payload.get().message(html, args).status(status);
        return _INSTANCE;
    }

}
