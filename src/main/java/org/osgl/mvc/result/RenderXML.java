package org.osgl.mvc.result;

import org.osgl.http.H;
import org.osgl.http.Http;
import org.osgl.util.S;

public class RenderXML extends RenderContent {

    private static RenderXML _INSTANCE = new RenderXML() {
        @Override
        public String content() {
            return payload().message;
        }

        @Override
        public Http.Status status() {
            Http.Status status = payload().status;
            return null == status ? super.status() : status;
        }
    };

    private RenderXML() {
        super(H.Format.XML);
    }

    public RenderXML(String xml, Object... args) {
        super(S.fmt(xml, args), H.Format.XML);
    }

    public RenderXML(H.Status status, String xml, Object... args) {
        super(status, S.fmt(xml, args), H.Format.XML);
    }

    public RenderXML(String xmlStr) {
        super(xmlStr, H.Format.XML);
    }

    public RenderXML(H.Status status, String xmlStr) {
        super(status, xmlStr, H.Format.XML);
    }

    public static RenderXML of(String xml) {
        payload.get().message(xml);
        return _INSTANCE;
    }

    public static RenderXML of(H.Status status, String xml) {
        payload.get().message(xml).status(status);
        return _INSTANCE;
    }

    public static RenderXML of(String xml, Object... args) {
        payload.get().message(xml, args);
        return _INSTANCE;
    }

    public static RenderXML of(H.Status status, String xml, Object... args) {
        payload.get().message(xml, args).status(status);
        return _INSTANCE;
    }

}
