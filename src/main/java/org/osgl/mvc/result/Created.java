package org.osgl.mvc.result;

import org.osgl.$;
import org.osgl.http.H;
import org.osgl.http.Http;

public final class Created extends Result {

    private final String location;

    public Created(String resourceGetUrl) {
        super(Http.Status.CREATED, "201 Created");
        this.location = $.notNull(resourceGetUrl);
    }

    @Override
    public boolean equals(Object obj) {
        return obj == this || (obj instanceof Created && ((Created) obj).location.equals(location));
    }

    @Override
    public String toString() {
        return "HTTP/1.1 201 Created";
    }

    @Override
    public void apply(H.Request req, H.Response resp) {
        super.apply(req, resp);
        resp.header(H.Header.Names.LOCATION, location);
    }
}
