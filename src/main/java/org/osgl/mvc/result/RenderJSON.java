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
import org.osgl.mvc.MvcConfig;
import org.osgl.util.Output;
import org.osgl.util.S;

public class RenderJSON extends RenderContent {

    private static RenderJSON _INSTANCE = new RenderJSON() {
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
            return payload().format;
        }

    };

    private RenderJSON() {
        super(MvcConfig.jsonMediaTypeProvider().apply());
        setOutputEncoding(MvcConfig.renderJsonOutputCharset());
    }

    public RenderJSON(String jsonStr) {
        super(jsonStr, MvcConfig.jsonMediaTypeProvider().apply(), MvcConfig.renderJsonOutputCharset());
    }
    public RenderJSON(String jsonFormat, Object ... args) {
        super(S.fmt(jsonFormat, args), MvcConfig.jsonMediaTypeProvider().apply(), MvcConfig.renderJsonOutputCharset());
    }

    public RenderJSON($.Visitor<Output> contentWriter) {
        super(contentWriter, H.Format.JSON);
    }

    public RenderJSON(Object v) {
        this(MvcConfig.jsonSerializer(v));
    }

    public RenderJSON(H.Status status, String jsonStr) {
        super(status, jsonStr, MvcConfig.jsonMediaTypeProvider().apply(), MvcConfig.renderJsonOutputCharset());
    }

    public RenderJSON(H.Status status, String jsonFormat, Object ... args) {
        super(status, S.fmt(jsonFormat, args), MvcConfig.jsonMediaTypeProvider().apply(), MvcConfig.renderJsonOutputCharset());
    }

    public RenderJSON(H.Status status, $.Visitor<Output> contentWriter) {
        super(status, contentWriter, H.Format.JSON);
    }

    public RenderJSON(H.Status status, Object v) {
        this(status, MvcConfig.jsonSerializer(v));
    }

    public static RenderJSON of(String jsonStr) {
        touchPayload().message(jsonStr);
        return _INSTANCE;
    }

    public static RenderJSON of(String jsonFormt, Object... args) {
        touchPayload().message(jsonFormt, args);
        return _INSTANCE;
    }

    public static RenderJSON of($.Visitor<Output> contentWriter) {
        touchPayload().contentWriter(contentWriter);
        return _INSTANCE;
    }

    public static RenderJSON of(Object v) {
        if (v instanceof String) {
            touchPayload().message((String) v);
        } else if (v instanceof $.Visitor) {
            touchPayload().contentWriter(($.Visitor) v);
        } else {
            touchPayload().contentWriter(MvcConfig.jsonSerializer(v));
        }
        return _INSTANCE;
    }

    public static RenderJSON of(H.Status status, String jsonStr) {
        touchPayload().message(jsonStr).status(status);
        return _INSTANCE;
    }

    public static RenderJSON of(H.Status status, String jsonFormt, Object... args) {
        touchPayload().message(jsonFormt, args).status(status);
        return _INSTANCE;
    }

    public static RenderJSON of(H.Status status, Object v) {
        if (v instanceof String) {
            touchPayload().message((String) v).status(status);
            return _INSTANCE;
        } else if (v instanceof $.Visitor) {
            return of(status, ($.Visitor) v);
        } else {
            return of(status, MvcConfig.jsonSerializer(v));
        }
    }

    public static RenderJSON of(H.Status status, $.Visitor<Output> contentWriter) {
        touchPayload().contentWriter(contentWriter).status(status);
        return _INSTANCE;
    }
}

