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
import org.osgl.http.util.Path;
import org.osgl.util.S;

/**
 * Base class for different Redirect result
 */
public abstract class RedirectBase extends Result {
    protected String url;

    protected RedirectBase(H.Status status) {
        super(status);
    }

    public RedirectBase(H.Status status, String url) {
        super(status);
        this.url = url.trim();
    }

    public RedirectBase(H.Status status, String url, Object ... args) {
        super(status);
        this.url = S.fmt(url, args);
    }

    @Override
    public final void apply(H.Request req, H.Response resp) {
        try {
            _applyStatus(req, resp);
            applyCookies(resp);
            applyHeaders(resp);
            String url = fullUrl(req);
            resp.header("Location", url);
            applyBeforeCommitHandler(req, resp);
            resp.commit();
            applyAfterCommitHandler(req, resp);
        } finally {
            clearThreadLocals();
        }
    }

    protected String url() {
        return url;
    }

    protected void _applyStatus(H.Request request, H.Response response) {
        applyStatus(response);
    }

    protected String fullUrl(H.Request request) {
        return Path.fullUrl(this.url(), request);
    }


}
