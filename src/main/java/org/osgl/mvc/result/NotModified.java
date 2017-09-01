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

import org.osgl.http.H;
import org.osgl.http.Http;
import org.osgl.util.S;

public class NotModified extends Result {

    /**
     * The static NotModified result
     */
    public static final NotModified INSTANCE = new NotModified();

    private static final NotModified _INSTANCE = new NotModified() {
        @Override
        protected String etag() {
            return payload().message;
        }

        @Override
        public long timestamp() {
            return payload().timestamp;
        }
    };

    private String etag;

    public NotModified() {
        super(Http.Status.NOT_MODIFIED);
    }

    public NotModified(String etag) {
        super(Http.Status.NOT_MODIFIED);
        this.etag = etag;
    }

    public NotModified(String etag, Object... args) {
        this(S.fmt(etag, args));
    }

    protected String etag() {
        return etag;
    }

    @Override
    public void apply(H.Request req, H.Response resp) {
        String etag = etag();
        if (null != etag) {
            resp.header(H.Header.Names.ETAG, etag);
        }
        super.apply(req, resp);
    }

    /**
     * Returns the {@link #INSTANCE static NotModified instance}
     *
     * @return the static instance
     */
    public static NotModified get() {
        return INSTANCE;
    }

    /**
     * Returns a static NotModified instance which when calling on
     * {@link #etag()} method, will return whatever stored in the
     * {@link #payload} thread local.
     * <p>
     * Before return the static instance, the specified etag will
     * be stored into the {@link #payload} thread local
     *
     * @param etag the etag
     * @return a static instance
     */
    public static NotModified of(String etag) {
        touchPayload().message(etag);
        return _INSTANCE;
    }

    /**
     * Returns a static NotModified instance which when calling on
     * {@link #etag()} method, will return whatever stored in the
     * {@link #payload} thread local.
     * <p>
     * Before return the static instance, the specified etag will
     * be stored into the {@link #payload} thread local
     *
     * @param etag the etag
     * @param args the args used to populate the etag
     * @return a static instance
     */
    public static NotModified of(String etag, Object... args) {
        touchPayload().message(etag, args);
        return _INSTANCE;
    }
}
