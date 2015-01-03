package org.osgl.mvc.result;

import org.osgl.http.H;

public class RenderJSON extends RenderContent {
    public RenderJSON(String jsonStr) {
        super(jsonStr, H.Format.json);
    }
}
