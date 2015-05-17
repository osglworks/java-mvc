package org.osgl.mvc.result;

import org.osgl.http.H;
import org.osgl.util.S;

/**
 * Render a text message
 */
public class RenderText extends RenderContent {
    public RenderText(String text, Object args) {
        super(S.fmt(text, args), H.Format.txt);
    }

    public RenderText(H.Format fmt, String text, Object args) {
        super(S.fmt(text, args), fmt);
    }
}
