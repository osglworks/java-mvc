package org.osgl.mvc.result;

import org.osgl.http.H;
import org.osgl.util.S;

public class RenderHtml extends RenderContent {
    public RenderHtml(String html, Object ... args) {
        super(S.fmt(html, args), H.Format.html);
    }
}
