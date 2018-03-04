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

/**
 * Render a text message
 */
public class RenderText extends RenderContent {

    private static RenderText _INSTANCE = new RenderText() {
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

    private static RenderText _INSTANCE2 = new RenderText() {
        @Override
        public String content() {
            return payload().message;
        }

        @Override
        public H.Format format() {
            return payload().format;
        }
    };

    private RenderText() {
        super(H.Format.TXT);
        setOutputEncoding(false);
    }

    public RenderText(String text, Object... args) {
        super(S.fmt(text, args), H.Format.TXT, false);
    }

    public RenderText(H.Format fmt, String text, Object... args) {
        super(S.fmt(text, args), fmt, false);
    }


    public RenderText(H.Status status, String text, Object... args) {
        super(status, S.fmt(text, args), H.Format.TXT, false);
    }

    public RenderText(H.Status status, H.Format fmt, String text, Object... args) {
        super(status, S.fmt(text, args), fmt, false);
    }

    public static RenderText of($.Visitor<Output> contentWriter) {
        touchPayload().contentWriter(contentWriter);
        return _INSTANCE;
    }

    public static RenderText of(String text) {
        touchPayload().message(text);
        return _INSTANCE;
    }

    public static RenderText of(String text, Object... args) {
        touchPayload().message(text, args);
        return _INSTANCE;
    }

    public static RenderText of(H.Format fmt, String text, Object... args) {
        touchPayload().message(text, args).format(fmt);
        return _INSTANCE2;
    }

    public static RenderText of(H.Status status, String text) {
        touchPayload().message(text).status(status);
        return _INSTANCE;
    }

    public static RenderText of(H.Status status, String text, Object... args) {
        touchPayload().message(text, args).status(status);
        return _INSTANCE;
    }

    public static RenderText of(H.Status status, H.Format fmt, String text, Object... args) {
        touchPayload().message(text, args).format(fmt).status(status);
        return _INSTANCE2;
    }
}
