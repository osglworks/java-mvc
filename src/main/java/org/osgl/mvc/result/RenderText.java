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
            return messageBag.get();
        }
    };

    private RenderText() {
        super(H.Format.TXT);
    }

    public RenderText(String text, Object... args) {
        super(S.fmt(text, args), H.Format.txt);
    }

    public RenderText(H.Format fmt, String text, Object... args) {
        super(S.fmt(text, args), fmt);
    }

    public static RenderText get(String text) {
        messageBag.set(text);
        return _INSTANCE;
    }

    public static RenderText get(String text, Object... args) {
        messageBag.set(S.fmt(text, args));
        return _INSTANCE;
    }
}
