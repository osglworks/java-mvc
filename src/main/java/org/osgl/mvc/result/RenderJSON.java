package org.osgl.mvc.result;

import org.osgl.http.H;
import org.osgl.mvc.MvcConfig;
import org.osgl.util.S;

public class RenderJSON extends RenderContent {

    private static RenderJSON _INSTANCE = new RenderJSON() {
        @Override
        public String content() {
            return payload().message;
        }
    };

    private RenderJSON() {
        super(H.Format.JSON);
        setOutputEncoding(false);
    }

    public RenderJSON(String jsonStr) {
        super(jsonStr, H.Format.JSON, false);
    }
    public RenderJSON(String jsonFormat, Object ... args) {
        super(S.fmt(jsonFormat, args), H.Format.JSON, false);
    }
    public RenderJSON(Object v) {
        this(MvcConfig.jsonSerializer().apply(v));
    }

    public RenderJSON(H.Status status, String jsonStr) {
        super(status, jsonStr, H.Format.JSON, false);
    }
    public RenderJSON(H.Status status, String jsonFormat, Object ... args) {
        super(status, S.fmt(jsonFormat, args), H.Format.JSON, false);
    }
    public RenderJSON(H.Status status, Object v) {
        this(status, MvcConfig.jsonSerializer().apply(v));
    }

    public static RenderJSON of(String jsonStr) {
        payload.get().message(jsonStr);
        return _INSTANCE;
    }

    public static RenderJSON of(String jsonFormt, Object... args) {
        payload.get().message(jsonFormt, args);
        return _INSTANCE;
    }

    public static RenderJSON of(Object v) {
        String s = v instanceof String ? (String) v : MvcConfig.jsonSerializer().apply(v);
        payload.get().message(s);
        return _INSTANCE;
    }

    public static RenderJSON of(H.Status status, String jsonStr) {
        payload.get().message(jsonStr).status(status);
        return _INSTANCE;
    }

    public static RenderJSON of(H.Status status, String jsonFormt, Object... args) {
        payload.get().message(jsonFormt, args).status(status);
        return _INSTANCE;
    }

    public static RenderJSON of(H.Status status, Object v) {
        String s = v instanceof String ? (String) v : MvcConfig.jsonSerializer().apply(v);
        payload.get().message(s).status(status);
        return _INSTANCE;
    }
}

