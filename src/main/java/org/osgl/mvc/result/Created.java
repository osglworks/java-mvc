package org.osgl.mvc.result;

import org.osgl.$;
import org.osgl.http.H;
import org.osgl.http.Http;
import org.osgl.util.S;

public class Created extends Result {

    public static final Created INSTANCE = new Created("");

    private static final Created _INSTANCE = new Created("") {
        @Override
        protected String location() {
            return payload().message;
        }

        @Override
        public Created location(String location) {
            payload().message = location;
            return this;
        }

        @Override
        protected String etag() {
            return payload().etag();
        }

        @Override
        public Created etag(String etag) {
            payload().etag(etag);
            return this;
        }

        @Override
        public long timestamp() {
            return payload().timestamp;
        }
    };

    private String location;
    private String etag;

    public Created(String resourceGetUrl) {
        super(Http.Status.CREATED, "201 Created");
        this.location = $.notNull(resourceGetUrl);
    }

    public Created etag(String etag) {
        this.etag = etag;
        return this;
    }

    public Created location(String location) {
        this.location = location;
        return this;
    }

    protected String etag() {
        return this.etag;
    }

    @Override
    public boolean equals(Object obj) {
        return obj == this || (obj instanceof Created && ((Created) obj).location().equals(location()));
    }

    @Override
    public String toString() {
        return "201 Created";
    }

    protected String location() {
        return location;
    }

    @Override
    public void apply(H.Request req, H.Response resp) {
        String location = location();
        if (S.notBlank(location)) {
            resp.header(H.Header.Names.LOCATION, location);
        }
        String etag = etag();
        if (S.notBlank(etag)) {
            resp.header(H.Header.Names.ETAG, etag);
        }
        super.apply(req, resp);
    }

    /**
     * This method is deprecated. Please use {@link #withLocation(String)} instead
     */
    @Deprecated
    public static Created of(String location) {
        touchPayload().message(location);
        return _INSTANCE;
    }

    public static Created withLocation(String location) {
        touchPayload().message(location);
        return _INSTANCE;
    }

    public static Created withEtag(String etag) {
        touchPayload().etag(etag);
        return _INSTANCE;
    }
}
