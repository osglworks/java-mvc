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
import org.osgl.exception.FastRuntimeException;
import org.osgl.http.H;
import org.osgl.http.Http;
import org.osgl.mvc.MvcConfig;
import org.osgl.util.C;
import org.osgl.util.KVStore;
import org.osgl.util.S;

import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class Result extends FastRuntimeException {

    protected static class Payload extends KVStore {
        public String message;
        public $.Visitor<Writer> contentWriter;
        public $.Func0<String> stringContentProducer;
        public Integer errorCode;
        public Throwable cause;
        public H.Format format;
        public H.Status status;
        public Object attachment;
        public String etag;
        public Boolean outputEncoding;
        public long timestamp;

        public Payload message(String message) {
            this.message = message;
            return this;
        }

        public Payload message(String message, Object... args) {
            this.message = S.fmt(message, args);
            return this;
        }

        public Payload contentWriter($.Visitor<Writer> contentWriter) {
            this.contentWriter = contentWriter;
            return this;
        }

        public Payload stringContentProducer($.Func0<String> producer) {
            this.stringContentProducer = producer;
            return this;
        }

        public Payload touch() {
            this.timestamp = $.ms();
            this.outputEncoding(MvcConfig.renderJsonOutputCharset());
            return this;
        }

        public Payload errorCode(int errorCode) {
            this.errorCode = errorCode;
            return this;
        }

        public Payload cause(Throwable cause) {
            this.cause = cause;
            return this;
        }

        public Payload format(H.Format format) {
            this.format = format;
            return this;
        }

        public Payload status(H.Status status) {
            this.status = status;
            return this;
        }

        public Payload attach(Object attachment) {
            this.attachment = attachment;
            return this;
        }

        public Payload etag(String etag) {
            this.etag = etag;
            return this;
        }

        public String etag() {
            return this.etag;
        }

        public Payload outputEncoding(boolean outputEncoding) {
            this.outputEncoding = outputEncoding;
            return this;
        }

        public boolean outputEncoding() {
            return null != outputEncoding && outputEncoding;
        }
    }

    private static final ThreadLocal<Payload> payload = new ThreadLocal<Payload>() {
        @Override
        protected Payload initialValue() {
            return new Payload();
        }
    };

    protected static Payload payload() {
        return payload.get();
    }

    protected static Payload touchPayload() {
        return payload().touch();
    }

    private Http.Status status;

    protected String message;

    private long timestamp = $.ms();

    private Map<String, H.Header> headers = new HashMap<>();
    private Map<String, H.Cookie> cookies = new HashMap<>();

    protected Result() {status = Http.Status.OK;}

    protected Result(Http.Status status) {
        this.status = status;
    }

    protected Result(Http.Status status, String message) {
        this.status = status;
        this.message = message;
    }

    protected Result(Http.Status status, String message, Object... args) {
        this.status = status;
        this.message = S.fmt(message, args);
    }

    protected Result(Http.Status status, Throwable cause) {
        super(cause);
        this.status = status;
    }

    protected Result(Http.Status status, Throwable cause, String message, Object... args) {
        super(cause);
        this.status = status;
        this.message = S.fmt(message, args);
    }

    protected final void setMessage(String message, Object... args) {
        this.message = S.fmt(message, args);
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Http.Status status() {
        return status;
    }

    public int statusCode() {
        return status().code();
    }

    public Result status(H.Status status) {
        this.status = status;
        return this;
    }

    public long timestamp() {
        return timestamp;
    }

    public Result header(H.Header header) {
        this.headers.put(header.name(), header);
        return this;
    }

    public Result addHeader(String name, String ... values) {
        H.Header header = this.headers.get(name);
        if (null == header) {
            header = new H.Header(name, values);
        } else {
            header = new H.Header(name, C.list(header.values()).append(C.listOf(values)));
        }
        this.headers.put(name, header);
        return this;
    }

    public Result addCookie(H.Cookie cookie) {
        this.cookies.put(cookie.name(), cookie);
        return this;
    }

    protected final void applyHeaders(H.Response response) {
        if (headers.isEmpty()) {
            return;
        }
        for (H.Header header : headers.values()) {
            String name = header.name();
            for (String value : header.values()) {
                response.addHeader(name, value);
            }
        }
    }

    protected final void applyCookies(H.Response response) {
        if (cookies.isEmpty()) {
            return;
        }
        for (H.Cookie cookie: cookies.values()) {
            response.addCookie(cookie);
        }
    }

    protected final void applyStatus(H.Response response) {
        response.status(statusCode());
    }

    protected final void applyBeforeCommitHandler(H.Request req, H.Response resp) {
        MvcConfig.applyBeforeCommitResultHandler(this, req, resp);
    }

    protected final void applyAfterCommitHandler(H.Request req, H.Response resp) {
        MvcConfig.applyAfterCommitResultHandler(this, req, resp);
    }

    protected void applyMessage(H.Request request, H.Response response) {
        String msg = getMessage();
        if (S.notBlank(msg)) {
            response.writeContent(msg);
        }
    }

    public void apply(H.Request req, H.Response resp) {
        applyBeforeCommitHandler(req, resp);
        try {
            applyStatus(resp);
            applyCookies(resp);
            applyHeaders(resp);
            applyMessage(req, resp);
        } finally {
            try {
                resp.commit();
                applyAfterCommitHandler(req, resp);
            } finally {
                clearThreadLocals();
            }
        }
    }

    public static void clearThreadLocals() {
        payload.remove();
    }

}
