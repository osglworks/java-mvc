package org.osgl.mvc.result;

import org.osgl.http.Http;
import org.osgl.util.S;

/**
 * Created by luog on 20/03/2014.
 */
public class BadRequest extends Result {
    public BadRequest() {
        super(Http.Status.BAD_REQUEST);
    }
    public BadRequest(String message) {
        super(Http.Status.BAD_REQUEST, message);
    }
    public BadRequest(String message, Object ... args) {
        this(S.fmt(message, args));
    }
}
