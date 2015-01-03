package org.osgl.mvc.result;

import org.osgl.http.Http;

/**
 * Created by luog on 20/03/2014.
 */
public class Ok extends Result {
    public Ok() {
        super(Http.Status.OK);
    }
}
