package org.osgl.mvc.result;

/*-
 * #%L
 * OSGL MVC
 * %%
 * Copyright (C) 2014 - 2017 OSGL (Open Source General Library)
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import org.osgl.$;
import org.osgl.http.H;
import org.osgl.http.Http;

public class Accepted extends Result {

    private static final Accepted _INSTANCE = new Accepted() {
        @Override
        public String location() {
            return payload().message;
        }

        @Override
        public long timestamp() {
            return payload().timestamp;
        }
    };

    private final String location;

    private Accepted() {
        location = null;
    }

    public Accepted(String statusCheckUrl) {
        super(Http.Status.ACCEPTED);
        this.location = $.requireNotNull(statusCheckUrl);
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

    public static Accepted of(String statusCheckUrl) {
        touchPayload().message(statusCheckUrl);
        return _INSTANCE;
    }
}
