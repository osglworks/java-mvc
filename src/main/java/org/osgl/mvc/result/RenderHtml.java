package org.osgl.mvc.result;

import org.osgl.http.H;

public class RenderHtml extends RenderContent {
    public RenderHtml(String html) {
        super(html, H.Format.html);
    }
}
