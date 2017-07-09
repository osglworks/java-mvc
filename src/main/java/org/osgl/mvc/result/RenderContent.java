package org.osgl.mvc.result;

import org.osgl.http.H;
import org.osgl.http.Http;

public class RenderContent extends Result {

    private String content;

    public RenderContent() {
    }

    public RenderContent(Http.Status status) {
        super(status);
    }

    public RenderContent(H.Context context) {
        super(context);
    }

    public RenderContent(H.Context context, Http.Status status) {
        super(context, status);
    }

    public String content() {
        return content;
    }

    public RenderContent(String content) {
        this.content = content;
    }

    public RenderContent(String content, Http.Status status) {
        super(status);
        this.content = content;
    }

    public RenderContent(String content, H.Context context) {
        super(context);
        this.content = content;
    }

    public RenderContent(String content, H.Context context, Http.Status status) {
        super(context, status);
        this.content = content;
    }

    public RenderContent content(String content) {
        this.content = content;
        return this;
    }


    /**
     * {@inheritDoc}
     * @param cause the cause
     * @return this error result
     */
    @Override
    public RenderContent initCause(Throwable cause) {
        super.initCause(cause);
        return this;
    }

    /**
     * {@inheritDoc}
     *
     * @param status the new status
     * @return this error result
     */
    @Override
    public RenderContent overwriteStatus(H.Status status) {
        super.overwriteStatus(status);
        return this;
    }

    /**
     * {@inheritDoc}
     * @param name the header name
     * @param value the header value
     * @return this error result
     */
    @Override
    public RenderContent header(String name, String value) {
        super.header(name, value);
        return this;
    }

    /**
     * {@inheritDoc}
     *
     * @param header the header
     * @return this error result
     */
    @Override
    public RenderContent header(H.Header header) {
        super.header(header);
        return this;
    }

    /**
     * {@inheritDoc}
     *
     * @param name the header name
     * @param values the values to be added
     * @return this error result
     */
    @Override
    public RenderContent addHeader(String name, String... values) {
        super.addHeader(name, values);
        return this;
    }

    /**
     * {@inheritDoc}
     * @param cookie the cookie to be set
     * @return this error result
     */
    @Override
    public RenderContent cookie(H.Cookie cookie) {
        super.cookie(cookie);
        return this;
    }

    /**
     * {@inheritDoc}
     * @param name the session variable name
     * @param val the session variable value
     * @return this error result
     */
    @Override
    public RenderContent session(String name, Object val) {
        super.session(name, val);
        return this;
    }

    /**
     * {@inheritDoc}
     * @param name the flash variable name
     * @param val the flash variable value
     * @return this error result
     */
    @Override
    public RenderContent flash(String name, Object val) {
        super.flash(name, val);
        return this;
    }

    /**
     * {@inheritDoc}
     * @param location the location URL
     * @return this result
     */
    @Override
    public RenderContent location(String location) {
        super.location(location);
        return this;
    }

    /**
     * {@inheritDoc}
     * @param contentType the content type string
     * @return this result
     */
    @Override
    public RenderContent contentType(String contentType) {
        super.contentType(contentType);
        return this;
    }

    @Override
    public RenderContent contentType(H.Format contentType) {
        super.contentType(contentType);
        return this;
    }

    /**
     * {@inheritDoc}
     * @param etag the etag value
     * @return this result
     */
    @Override
    public RenderContent etag(String etag) {
        super.etag(etag);
        return this;
    }


    @Override
    protected void applyBody(H.Request request, H.Response response) {
        response.writeContent(content());
    }

}
