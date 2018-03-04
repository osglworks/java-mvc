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
import org.osgl.util.Output;
import org.osgl.util.S;

public class RenderHtml extends RenderContent {

    private static RenderHtml _INSTANCE = new RenderHtml() {
        @Override
        public String content() {
            return payload().message;
        }

        @Override
        public $.Visitor<Output> contentWriter() {
            return payload().contentWriter;
        }

        @Override
        public Http.Status status() {
            Http.Status status = payload().status;
            return null == status ? super.status() : status;
        }

        @Override
        public long timestamp() {
            return payload().timestamp;
        }
    };

    private RenderHtml() {
        super(H.Format.HTML);
    }

    public RenderHtml(String html) {
        super(html, H.Format.HTML);
    }

    public RenderHtml(String html, Object ... args) {
        super(S.fmt(html, args), H.Format.HTML);
    }

    public RenderHtml(H.Status status, String html) {
        super(status, html, H.Format.HTML);
    }

    public RenderHtml(H.Status status, String html, Object ... args) {
        super(status, S.fmt(html, args), H.Format.HTML);
    }

    public static RenderHtml of($.Visitor<Output> contentWriter) {
        touchPayload().contentWriter(contentWriter);
        return _INSTANCE;
    }

    public static RenderHtml of(String html) {
        touchPayload().message(html);
        return _INSTANCE;
    }

    public static RenderHtml of(String html, Object... args) {
        touchPayload().message(html, args);
        return _INSTANCE;
    }

    public static RenderHtml of(H.Status status, String html) {
        touchPayload().message(html).status(status);
        return _INSTANCE;
    }

    public static RenderHtml of(H.Status status, String html, Object... args) {
        touchPayload().message(html, args).status(status);
        return _INSTANCE;
    }

}
