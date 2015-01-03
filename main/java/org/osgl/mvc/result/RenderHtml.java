package org.osgl.mvc.result;

import org.osgl.http.H;

/**
 * Created by luog on 23/03/14.
 */
public class RenderHtml extends RenderContent {
    public RenderHtml(String html) {
        super(html, H.Format.html);
    }
}
