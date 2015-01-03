package org.osgl.mvc.result;

import org.osgl.http.H;
import org.osgl.http.Http;
import org.osgl.util.S;

/**
 * Created by luog on 20/03/2014.
 */
public class NotModified extends Result {
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
