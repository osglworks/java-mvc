package org.osgl.mvc.result;

import org.osgl.http.H;
import org.osgl.util.S;

/**
 * Created by luog on 23/03/14.
 */
public class RenderXML extends RenderContent {

    private static RenderXML _INSTANCE = new RenderXML() {
        @Override
        public String content() {
            return messageBag.get();
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

    public static RenderXML get(String xml) {
        messageBag.set(xml);
        return _INSTANCE;
    }

    public static RenderXML get(String xml, Object... args) {
        messageBag.set(S.fmt(xml, args));
        return _INSTANCE;
    }

}
