package org.osgl.mvc.result;

import org.osgl.http.H;
import org.osgl.http.Http;
import org.osgl.util.S;

/**
 * Created by luog on 20/03/2014.
 */
public class Unauthorized extends Result {
    private String realm;
    public Unauthorized(String realm) {
        super(Http.Status.BAD_REQUEST);
        this.realm = realm;
    }

    @Override
    public void apply(H.Request req, H.Response resp) {
        applyStatus(resp);
        StringBuilder sb = S.builder("Basic realm=\"")
                            .append(realm).append("\"");
        resp.header(H.Header.Names.WWW_AUTHENTICATE, sb.toString());
    }
}
