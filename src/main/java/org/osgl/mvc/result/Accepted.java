package org.osgl.mvc.result;

import org.osgl.$;
import org.osgl.http.H;
import org.osgl.http.Http;

public class Accepted extends Result {

    private static final Accepted _INSTANCE = new Accepted() {
        @Override
        public String location() {
            return payload().message;
        }
    };

    private final String location;

    private Accepted() {
        location = null;
    }

    public Accepted(String statusCheckUrl) {
        super(Http.Status.ACCEPTED);
        this.location = $.notNull(statusCheckUrl);
    }

    @Override
    public boolean equals(Object obj) {
        return obj == this || (obj instanceof Accepted && ((Accepted) obj).location().equals(location()));
    }

    public String location() {
        return location;
    }

    @Override
    public String toString() {
        return "201 Created";
    }

    @Override
    public void apply(H.Request req, H.Response resp) {
        resp.header(H.Header.Names.LOCATION, location());
        super.apply(req, resp);
    }

    public static Accepted get(String statusCheckUrl) {
        payload.get().message(statusCheckUrl);
        return _INSTANCE;
    }
}
