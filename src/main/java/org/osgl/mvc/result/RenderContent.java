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
import org.osgl.util.E;
import org.osgl.util.Output;
import org.osgl.util.S;

public abstract class RenderContent extends Result {

    private String content;
    private $.Visitor<Output> contentWriter;
    private $.Func0<String> stringContentProducer;
    private H.Format format;
    private boolean outputEncoding;

    /**
     * Create a RenderContent object with blank content, format and
     * the outputEncoding set to {@code true}
     *
     * @param format the content type
     */
    protected RenderContent(H.Format format) {
        this("", format, true);
    }

    /**
     * Create a RenderContent object with content, format and
     * the outputEncoding set to {@code true}
     *
     * @param content the content string
     * @param format the content type
     */
    protected RenderContent(String content, H.Format format) {
        this(content, format, true);
    }

    /**
     * Create a RenderContent object with content writer, format and
     * the outputEncoding set to {@code true}
     *
     * @param contentWriter the content writer
     * @param format the content type
     */
    protected RenderContent($.Visitor<Output> contentWriter, H.Format format) {
        this(contentWriter, format, true);
    }

    /**
     * Create a RenderContent object with content, format and
     * the outputEncoding set to {@code true}
     *
     * @param status the response status
     * @param content the content string
     * @param format the content type
     */
    protected RenderContent(H.Status status, String content, H.Format format) {
        this(status, content, format, true);
    }

    /**
     * Create a RenderContent object with content writer, format and
     * the outputEncoding set to {@code true}
     *
     * @param status the response status
     * @param contentWriter the content writer
     * @param format the content type
     */
    protected RenderContent(H.Status status, $.Visitor<Output> contentWriter, H.Format format) {
        this(status, contentWriter, format, true);
    }

    /**
     * Create a RenderContent object with content writer, format and
     * the outputEncoding set to {@code true}
     *
     * @param status the response status
     * @param contentProducer the content message producer
     * @param format the content type
     */
    protected RenderContent(H.Status status, $.Func0<String> contentProducer, H.Format format) {
        this(status, contentProducer, format, true);
    }

    /**
     * Create a RenderContent object with content, format and outputEncoding
     * specified.
     *
     * <p>
     * If outputEncoding is set to {@code true} then when applying the
     * Result, it will set content type to "content-type; charset=encoding"
     * style
     * </p>
     *
     * @param content the content string
     * @param format the content type
     * @param outputEncoding output encoding
     */
    protected RenderContent(String content, H.Format format, boolean outputEncoding) {
        this(H.Status.OK, content, format, outputEncoding);
    }

    /**
     * Create a RenderContent object with content writer, format and outputEncoding
     * specified.
     *
     * <p>
     * If outputEncoding is set to {@code true} then when applying the
     * Result, it will set content type to "content-type; charset=encoding"
     * style
     * </p>
     *
     * @param contentWriter the content writer
     * @param format the content type
     * @param outputEncoding output encoding
     */
    protected RenderContent($.Visitor<Output> contentWriter, H.Format format, boolean outputEncoding) {
        this(H.Status.OK, contentWriter, format, outputEncoding);
    }

    /**
     * Create a RenderContent object with content, format and outputEncoding
     * specified.
     *
     * <p>
     * If outputEncoding is set to {@code true} then when applying the
     * Result, it will set content type to "content-type; charset=encoding"
     * style
     * </p>
     *
     * @param status HTTP response status
     * @param content the content string
     * @param format the content type
     * @param outputEncoding output encoding
     */
    protected RenderContent(H.Status status, String content, H.Format format, boolean outputEncoding) {
        super(status);
        E.NPE(format);

        this.content = content;
        this.format = format;
        this.outputEncoding = outputEncoding;
    }

    /**
     * Create a RenderContent object with content writer, format and outputEncoding
     * specified.
     *
     * <p>
     * If outputEncoding is set to {@code true} then when applying the
     * Result, it will set content type to "content-type; charset=encoding"
     * style
     * </p>
     *
     * @param status HTTP response status
     * @param contentWriter the content writer
     * @param format the content type
     * @param outputEncoding output encoding
     */
    protected RenderContent(H.Status status, $.Visitor<Output> contentWriter, H.Format format, boolean outputEncoding) {
        super(status);
        E.NPE(format);

        this.contentWriter = $.notNull(contentWriter);
        this.format = format;
        this.outputEncoding = outputEncoding;
    }

    /**
     * Create a RenderContent object with content writer, format and outputEncoding
     * specified.
     *
     * <p>
     * If outputEncoding is set to {@code true} then when applying the
     * Result, it will set content type to "content-type; charset=encoding"
     * style
     * </p>
     *
     * @param status HTTP response status
     * @param producer the content producer
     * @param format the content type
     * @param outputEncoding output encoding
     */
    protected RenderContent(H.Status status, $.Func0<String> producer, H.Format format, boolean outputEncoding) {
        super(status);
        E.NPE(format);

        this.stringContentProducer = $.notNull(producer);
        this.format = format;
        this.outputEncoding = outputEncoding;
    }

    protected void setContentType(H.Response resp) {
        String s = format().contentType();
        if (isOutputEncoding()) {
            String encoding = resp.characterEncoding();
            if (S.notBlank(encoding)) {
                s = S.builder(s).append("; charset=").append(encoding).toString();
            }
        }
        resp.initContentType(s);
    }

    public boolean isOutputEncoding() {
        return outputEncoding;
    }

    public RenderContent setOutputEncoding(boolean outputEncoding) {
        this.outputEncoding = outputEncoding;
        return this;
    }

    public H.Format format() {
        return format;
    }

    public String content() {
        return null == stringContentProducer ? content : stringContentProducer.apply();
    }

    public $.Visitor<Output> contentWriter() {
        return contentWriter;
    }

    public void apply(H.Request req, H.Response resp) {
        try {
            applyStatus(resp);
            setContentType(resp);
            applyCookies(resp);
            applyHeaders(resp);
            applyBeforeCommitHandler(req, resp);
            $.Visitor<Output> contentWriter = contentWriter();
            if (null != contentWriter) {
                contentWriter.visit(resp.output());
            } else {
                resp.writeContent(content());
            }
        } finally {
            try {
                resp.commit();
                applyAfterCommitHandler(req, resp);
            } finally {
                clearThreadLocals();
            }
        }
    }
}
