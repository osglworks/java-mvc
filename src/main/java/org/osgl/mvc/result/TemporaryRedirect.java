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

import org.osgl.http.Http;
import org.osgl.util.S;

public class TemporaryRedirect extends RedirectBase {

    private static TemporaryRedirect _INSTANCE = new TemporaryRedirect() {
        @Override
        protected String url() {
            return payload().message;
        }
    };

    private TemporaryRedirect() {
        super(Http.Status.TEMPORARY_REDIRECT);
    }

    public TemporaryRedirect(String url) {
        super(Http.Status.TEMPORARY_REDIRECT, url);
    }

    public TemporaryRedirect(String url, Object... args) {
        this(S.fmt(url, args));
    }

    public static TemporaryRedirect of(String url) {
        payload().message(url);
        return _INSTANCE;
    }

    public static TemporaryRedirect of(String url, Object... args) {
        payload().message(url, args);
        return _INSTANCE;
    }

}
