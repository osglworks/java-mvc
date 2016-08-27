package org.osgl.mvc.result;

import org.osgl.$;
import org.osgl.http.H;
import org.osgl.http.Http;

public final class Accepted extends Result {

    private final String location;

    public Accepted(String statusCheckUrl) {
        super(Http.Status.ACCEPTED, "202 Accepted");
        this.location = $.notNull(statusCheckUrl);
    }

    @Override
    public boolean equals(Object obj) {
        return obj == this || (obj instanceof Accepted && ((Accepted) obj).location.equals(location));
    }

    @Override
    public String toString() {
        return "HTTP/1.1 201 Created";
    }

    @Override
    public void apply(H.Request req, H.Response resp) {
        resp.header(H.Header.Names.LOCATION, location);
        super.apply(req, resp);
    }
}
