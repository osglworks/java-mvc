package org.osgl.mvc.result;

import org.osgl.http.Http;

public final class Ok extends Result {

    public static Ok INSTANCE = new Ok();

    public Ok() {
        super(Http.Status.OK, "200 Ok");
    }

    @Override
    public boolean equals(Object obj) {
        return obj == this || obj instanceof Ok;
    }

    @Override
    public String toString() {
        return "HTTP/1.1 200 OK";
    }
}
