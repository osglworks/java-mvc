package org.osgl.mvc.result;

import org.osgl.http.H;

/**
 * Created by luog on 23/03/14.
 */
public class RenderText extends RenderContent {
    public RenderText(String text) {
        super(text, H.Format.txt);
    }
}
