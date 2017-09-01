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

/**
 * Render NO_CONTENT response
 */
public class NoContent extends Result {

    public static NoContent INSTANCE = new NoContent();

    protected NoContent() {
        super(H.Status.NO_CONTENT);
    }

    @Override
    public void apply(H.Request req, H.Response resp) {
        try {
            applyStatus(resp);
            applyCookies(resp);
            applyHeaders(resp);
            applyBeforeCommitHandler(req, resp);
            resp.commit();
            applyAfterCommitHandler(req, resp);
        } finally {
            clearThreadLocals();
        }
    }

    public static NoContent get() {
        return INSTANCE;
    }
}
