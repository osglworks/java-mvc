package org.osgl.mvc.result;

import org.osgl.http.H;
import org.osgl.util.S;

/**
 * Render a text message
 */
public class RenderText extends RenderContent {

    private static RenderText _INSTANCE = new RenderText() {
        @Override
        public String content() {
            return payload().message;
        }
    };

    private static RenderText _INSTANCE2 = new RenderText() {
        @Override
        public String content() {
            return payload().message;
        }

        @Override
        public H.Format format() {
            return payload().format;
        }
    };

    private RenderText() {
        super(H.Format.TXT);
        setOutputEncoding(false);
    }

    public RenderText(String text, Object... args) {
        super(S.fmt(text, args), H.Format.TXT, false);
    }

    public RenderText(H.Format fmt, String text, Object... args) {
        super(S.fmt(text, args), fmt, false);
    }

    public static RenderText of(String text) {
        payload.get().message(text);
        return _INSTANCE;
    }

    public static RenderText of(String text, Object... args) {
        payload.get().message(text, args);
        return _INSTANCE;
    }

    public static RenderText of(H.Format fmt, String text, Object... args) {
        payload.get().message(text, args).format(fmt);
        return _INSTANCE2;
    }
}
