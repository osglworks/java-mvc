package org.osgl.mvc.result;

import org.osgl.http.H;
import org.osgl.mvc.MvcConfig;
import org.osgl.util.S;

public class RenderJSON extends RenderContent {

    private static RenderJSON _INSTANCE = new RenderJSON() {
        @Override
        public String content() {
            return messageBag.get();
        }
    };

    private RenderJSON() {
        super(H.Format.HTML);
    }

    public RenderJSON(String jsonStr) {
        super(jsonStr, H.Format.json);
    }
    public RenderJSON(String jsonFormat, Object ... args) {
        super(S.fmt(jsonFormat, args), H.Format.json);
    }
    public RenderJSON(Object v) {
        this(MvcConfig.jsonSerializer().apply(v));
    }

    public static RenderJSON get(String jsonStr) {
        messageBag.set(jsonStr);
        return _INSTANCE;
    }

    public static RenderJSON get(String jsonFormt, Object... args) {
        messageBag.set(S.fmt(jsonFormt, args));
        return _INSTANCE;
    }

    public static RenderJSON get(Object v) {
        String s = v instanceof String ? (String) v : MvcConfig.jsonSerializer().apply(v);
        messageBag.set(s);
        return _INSTANCE;
    }
}
