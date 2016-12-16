package org.osgl.mvc.result;

import org.osgl.http.H;
import org.osgl.http.Http;
import org.osgl.mvc.util.ResultTransformer;
import org.osgl.util.E;
import org.osgl.util.S;

public class NotModified extends UnTransformableResult {

    public static final NotModified INSTANCE = new NotModified();

    String etag;
    public NotModified() {
        super(Http.Status.NOT_MODIFIED);
    }
    public NotModified(String etag) {
        super(Http.Status.NOT_MODIFIED);
        this.etag = etag;
    }
    public NotModified(String etag, Object... args) {
        this(S.fmt(etag, args));
    }

    @Override
    public void apply(H.Request req, H.Response resp) {
        resp.status(status());
        if (null != etag) {
            resp.header(H.Header.Names.ETAG, etag);
        }
    }
}
