package org.osgl.mvc.result;

import org.osgl.http.H;
import org.osgl.http.Http;
import org.osgl.util.S;

public class NotModified extends Result {

    /**
     * The static NotModified result
     */
    public static final NotModified INSTANCE = new NotModified();

    private static final NotModified _INSTANCE = new NotModified() {
        @Override
        protected String etag() {
            return messageBag.get();
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
        resp.status(status());
        String etag = etag();
        if (null != etag) {
            resp.header(H.Header.Names.ETAG, etag);
        }
    }

    /**
     * Returns the {@link #INSTANCE static NotModified instance}
     * @return the static instance
     */
    public static NotModified get() {
        return INSTANCE;
    }

    /**
     * Returns a static NotModified instance which when calling on
     * {@link #etag()} method, will return whatever stored in the
     * {@link #messageBag} thread local.
     *
     * Before return the static instance, the specified etag will
     * be stored into the {@link #messageBag} thread local
     *
     * @param etag the etag
     * @return a static instance
     */
    public static NotModified get(String etag) {
        messageBag.set(etag);
        return _INSTANCE;
    }

    /**
     * Returns a static NotModified instance which when calling on
     * {@link #etag()} method, will return whatever stored in the
     * {@link #messageBag} thread local.
     *
     * Before return the static instance, the specified etag will
     * be stored into the {@link #messageBag} thread local
     *
     * @param etag the etag
     * @param args the args used to populate the etag
     * @return a static instance
     */
    public static NotModified get(String etag, Object... args) {
        messageBag.set(S.fmt(etag, args));
        return _INSTANCE;
    }
}
