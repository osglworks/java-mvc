package org.osgl.mvc.result;

import org.osgl.http.H;
import org.osgl.http.Http;
import org.osgl.mvc.util.ResultTransformer;
import org.osgl.util.E;
import org.osgl.util.S;

public class NotModified extends UnTransformableResult {

    /**
     * The static NotModified result
     */
    public static final NotModified INSTANCE = new NotModified();

    private static final NotModified _INSTANCE = new NotModified() {
        @Override
        protected String etag() {
            return payload().message;
        }
    };

    private String etag;

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

    protected String etag() {
        return etag;
    }

    @Override
    public void apply(H.Request req, H.Response resp) {
        String etag = etag();
        if (null != etag) {
            resp.header(H.Header.Names.ETAG, etag);
        }
        super.apply(req, resp);
    }

    /**
     * Returns the {@link #INSTANCE static NotModified instance}
     *
     * @return the static instance
     */
    public static NotModified get() {
        return INSTANCE;
    }

    /**
     * Returns a static NotModified instance which when calling on
     * {@link #etag()} method, will return whatever stored in the
     * {@link #payload} thread local.
     * <p>
     * Before return the static instance, the specified etag will
     * be stored into the {@link #payload} thread local
     *
     * @param etag the etag
     * @return a static instance
     */
    public static NotModified of(String etag) {
        payload.get().message(etag);
        return _INSTANCE;
    }

    /**
     * Returns a static NotModified instance which when calling on
     * {@link #etag()} method, will return whatever stored in the
     * {@link #payload} thread local.
     * <p>
     * Before return the static instance, the specified etag will
     * be stored into the {@link #payload} thread local
     *
     * @param etag the etag
     * @param args the args used to populate the etag
     * @return a static instance
     */
    public static NotModified of(String etag, Object... args) {
        payload.get().message(etag, args);
        return _INSTANCE;
    }
}
