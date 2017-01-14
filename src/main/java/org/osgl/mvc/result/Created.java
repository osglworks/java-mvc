package org.osgl.mvc.result;

import org.osgl.$;
import org.osgl.http.H;
import org.osgl.http.Http;

public class Created extends Result {

    public static final Created INSTANCE = new Created("");

    private static final Created _INSTANCE = new Created("") {
        @Override
        protected String location() {
            return payload().message;
        }
    };

    private final String location;

    public Created(String resourceGetUrl) {
        super(Http.Status.CREATED, "201 Created");
        this.location = $.notNull(resourceGetUrl);
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
        resp.header(H.Header.Names.LOCATION, location());
        super.apply(req, resp);
    }

    public static Created get(String location) {
        payload.get().message(location);
        return _INSTANCE;
    }
}
