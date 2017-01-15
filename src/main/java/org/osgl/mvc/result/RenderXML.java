package org.osgl.mvc.result;

import org.osgl.http.H;
import org.osgl.util.S;

public class RenderXML extends RenderContent {

    private static RenderXML _INSTANCE = new RenderXML() {
        @Override
        public String content() {
            return payload().message;
        }
    };

    private RenderXML() {
        super(H.Format.TXT);
    }

    public RenderXML(String xml, Object... args) {
        super(S.fmt(xml, args), H.Format.txt);
    }

    public RenderXML(String xmlStr) {
        super(xmlStr, H.Format.xml);
    }

    public static RenderXML of(String xml) {
        payload.get().message(xml);
        return _INSTANCE;
    }

    public static RenderXML of(String xml, Object... args) {
        payload.get().message(xml, args);
        return _INSTANCE;
    }

}
