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
import org.osgl.util.S;

import java.io.StringWriter;
import java.io.Writer;
import java.util.List;

public class RenderYAML extends RenderContent {

    private static RenderYAML _INSTANCE = new RenderYAML() {
        @Override
        public String content() {
            Payload payload = payload();
            if (null != payload.stringContentProducer) {
                Object result = payload.stringContentProducer.apply();
                if (result instanceof String) {
                    return (String) result;
                }
                StringWriter sw = new StringWriter();
                MvcConfig.yamlSerializer(result).visit(sw);
                return sw.toString();
            }
            return payload.message;
        }

        @Override
        public $.Visitor<Writer> contentWriter() {
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
            return H.Format.YAML;
        }

    };

    private RenderYAML() {
        super(H.Format.YAML);
        setOutputEncoding(MvcConfig.renderJsonOutputCharset());
    }

    public RenderYAML(String yamlStr) {
        super(yamlStr, H.Format.YAML, MvcConfig.renderJsonOutputCharset());
    }
    public RenderYAML(String yamlFormat, Object ... args) {
        super(S.fmt(yamlFormat, args), H.Format.YAML, MvcConfig.renderJsonOutputCharset());
    }

    public RenderYAML($.Visitor<Writer> contentWriter) {
        super(contentWriter, H.Format.YAML);
    }

    public RenderYAML(Object v) {
        this(MvcConfig.yamlSerializer(v));
    }

    public RenderYAML(H.Status status, String yamlStr) {

        super(status, yamlStr, H.Format.YAML, MvcConfig.renderJsonOutputCharset());
    }

    public RenderYAML(H.Status status, String yamlFormat, Object ... args) {
        super(status, S.fmt(yamlFormat, args), H.Format.YAML, MvcConfig.renderJsonOutputCharset());
    }

    public RenderYAML(H.Status status, $.Visitor<Writer> contentWriter) {
        super(status, contentWriter, H.Format.YAML);
    }

    public RenderYAML(H.Status status, Object v) {
        this(status, MvcConfig.yamlSerializer(v));
    }

    public static RenderYAML of(String yamlStr) {
        touchPayload().message(yamlStr);
        return _INSTANCE;
    }

    public static RenderYAML of(String yamlFormt, Object... args) {
        touchPayload().message(yamlFormt, args);
        return _INSTANCE;
    }

    public static RenderYAML of($.Visitor<Writer> contentWriter) {
        touchPayload().contentWriter(contentWriter);
        return _INSTANCE;
    }

    public static RenderYAML of($.Func0<String> producer) {
        touchPayload().stringContentProducer(producer);
        return _INSTANCE;
    }

    public static RenderYAML of(Object v) {
        if (v instanceof String) {
            touchPayload().message((String) v);
        } else if (v instanceof $.Visitor) {
            touchPayload().contentWriter(($.Visitor) v);
        } else if (v instanceof List) {
            touchPayload().contentWriter(MvcConfig.yamlSerializer(v));
        } else if (v instanceof $.Func0) {
            touchPayload().stringContentProducer(($.Func0<String>) v);
        } else {
            touchPayload().contentWriter(MvcConfig.yamlSerializer(v));
        }
        return _INSTANCE;
    }

    public static RenderYAML of(H.Status status, String yamlStr) {
        touchPayload().message(yamlStr).status(status);
        return _INSTANCE;
    }

    public static RenderYAML of(H.Status status, String yamlFormt, Object... args) {
        touchPayload().message(yamlFormt, args).status(status);
        return _INSTANCE;
    }

    public static RenderYAML of(H.Status status, Object v) {
        touchPayload().status(status);
        return of(v);
    }

    public static RenderYAML of(H.Status status, $.Visitor<Writer> contentWriter) {
        touchPayload().contentWriter(contentWriter).status(status);
        return _INSTANCE;
    }

    public static RenderYAML of(H.Status status, $.Func0<String> contentProducer) {
        touchPayload().stringContentProducer(contentProducer).status(status);
        return _INSTANCE;
    }

}

