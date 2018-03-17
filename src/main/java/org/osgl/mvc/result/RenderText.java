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
            Payload payload = payload();
            return null == payload.stringContentProducer ? payload.message : payload.stringContentProducer.apply();
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

        @Override
        public boolean isOutputEncoding() {
            return payload().outputEncoding();
        }

        @Override
        public RenderContent setOutputEncoding(boolean outputEncoding) {
            payload().outputEncoding(outputEncoding);
            return this;
        }

        @Override
        public H.Format format() {
            return H.Format.TXT;
        }
    };

    private static RenderText _INSTANCE2 = new RenderText() {
        @Override
        public String content() {
            return payload().message;
        }

        @Override
        public H.Format format() {
            return H.Format.TXT;
        }
    };

    private RenderText() {
        super(H.Format.TXT);
        setOutputEncoding(true);
    }

    public RenderText(String text, Object... args) {
        super(S.fmt(text, args), H.Format.TXT, true);
    }

    public RenderText(H.Format fmt, String text, Object... args) {
        super(S.fmt(text, args), fmt, true);
    }


    public RenderText(H.Status status, String text, Object... args) {
        super(status, S.fmt(text, args), H.Format.TXT, true);
    }

    public RenderText(H.Status status, H.Format fmt, String text, Object... args) {
        super(status, S.fmt(text, args), fmt, true);
    }

    public static RenderText of($.Visitor<Output> contentWriter) {
        touchPayload().contentWriter(contentWriter).outputEncoding(true);
        return _INSTANCE;
    }

    public static RenderText of($.Func0<String> contentProducer) {
        touchPayload().stringContentProducer(contentProducer);
        return _INSTANCE;
    }

    public static RenderText of(String text) {
        touchPayload().message(text).outputEncoding(true);
        return _INSTANCE;
    }

    public static RenderText of(String text, Object... args) {
        touchPayload().message(text, args).outputEncoding(true);
        return _INSTANCE;
    }

    public static RenderText of(H.Format fmt, String text, Object... args) {
        touchPayload().message(text, args).format(fmt).outputEncoding(true);
        return _INSTANCE2;
    }

    public static RenderText of(H.Status status, String text) {
        touchPayload().message(text).status(status).outputEncoding(true);
        return _INSTANCE;
    }

    public static RenderText of(H.Status status, String text, Object... args) {
        touchPayload().message(text, args).status(status).outputEncoding(true);
        return _INSTANCE;
    }

    public static RenderText of(H.Status status, H.Format fmt, String text, Object... args) {
        touchPayload().message(text, args).format(fmt).status(status).outputEncoding(true);
        return _INSTANCE2;
    }

    public static RenderText of(H.Status status, $.Visitor<Output> contentWriter) {
        touchPayload().contentWriter(contentWriter).status(status);
        return _INSTANCE;
    }

    public static RenderText of(H.Status status, $.Func0<String> contentProducer) {
        touchPayload().stringContentProducer(contentProducer).status(status);
        return _INSTANCE;
    }
}
