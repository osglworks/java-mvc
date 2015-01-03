package org.osgl.mvc.result;

import org.osgl.http.H;

/**
 * Created by luog on 23/03/14.
 */
public class RenderXML extends RenderContent {
    public RenderXML(String xmlStr) {
        super(xmlStr, H.Format.xml);
    }
}
