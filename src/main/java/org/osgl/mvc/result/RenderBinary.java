package org.osgl.mvc.result;

import org.osgl.$;
import org.osgl.http.H;
import org.osgl.http.Http;
import org.osgl.storage.ISObject;
import org.osgl.storage.impl.SObject;
import org.osgl.util.S;

import java.io.File;
import java.io.OutputStream;

import static org.osgl.http.H.Header.Names.CONTENT_LENGTH;

public class RenderBinary extends RichResult {
    private enum Disposition {
        INLINE, ATTACHMENT;

        public boolean isInline() {
            return INLINE == this;
        }

        public boolean isAttachment() {
            return ATTACHMENT == this;
        }
    }

    private Disposition disposition = Disposition.INLINE;
    private long length;
    private String name;
    private ISObject binary;
    private $.Function<OutputStream, ?> outputStreamVisitor;

    /**
     * Construct a RenderBinary result with status default to {@link H.Status#OK}
     */
    public RenderBinary() {
    }

    /**
     * Construct a RenderBinary result with status specified
     *
     * @param status the status
     */
    public RenderBinary(Http.Status status) {
        super(status);
    }

    /**
     * construct a RenderBinary result with HTTP context and status
     * default to {@link H.Status#OK}
     *
     * @param context the HTTP context
     */
    public RenderBinary(H.Context context) {
        super(context);
    }

    /**
     * construct a RenderBinary result with HTTP context and status specified
     *
     * @param context the HTTP context
     * @param status  the status
     */
    public RenderBinary(H.Context context, Http.Status status) {
        super(context, status);
    }

    /**
     * Set the file to be output
     * <p>
     * If the binary source is already set then it will be overwritten by
     * this file
     *
     * @param file the file as the binary source
     * @return this result
     */
    public RenderBinary binary(File file) {
        return this.binary(SObject.of(file));
    }

    /**
     * Set the {@link ISObject storage object} to be output
     * <p>
     * If the binary source is already set then it will be overwritten by
     * this storage object
     *
     * @param blob the sobject as the binary source
     * @return this result
     */
    public RenderBinary binary(ISObject blob) {
        this.binary = blob;
        this.contentType(blob.getAttribute(ISObject.ATTR_CONTENT_TYPE));
        String s = blob.getAttribute(ISObject.ATTR_CONTENT_LENGTH);
        if (S.notBlank(s)) {
            this.length = Integer.parseInt(s);
        }
        this.name = blob.getAttribute(ISObject.ATTR_FILE_NAME);
        this.outputStreamVisitor = null;
        return this;
    }

    /**
     * Set a byte array to be output
     * <p>
     * If the binary source is already set then it will be overwritten by
     * this byte array
     *
     * @param blob the byte array as the binary source
     * @return this result
     */
    public RenderBinary binary(byte[] blob) {
        this.binary = SObject.of(blob);
        this.outputStreamVisitor = null;
        return this;
    }

    /**
     * Set output stream visitor which can be used to directly apply a binary content into
     * the output stream without cache the blob data inside this result.
     * <p>
     * If the binary source is already set then it will be overwritten by this
     * output stream visitor function
     *
     * @param outputStreamVisitor the output stream visitor
     * @return this result
     */
    public RenderBinary binary($.Function<OutputStream, ?> outputStreamVisitor) {
        this.outputStreamVisitor = $.notNull(outputStreamVisitor);
        this.binary = null;
        return this;
    }

    /**
     * Make this binary result be INLINE disposition
     *
     * @return this binary result
     */
    public RenderBinary asInline() {
        this.disposition = Disposition.INLINE;
        return this;
    }

    /**
     * Make this binary result be ATTACHMENT disposition
     *
     * @return this binary result
     */
    public RenderBinary asAttachment() {
        this.disposition = Disposition.ATTACHMENT;
        return this;
    }

    /**
     * Set the name of download file. This method has no effect if the disposition type
     * is {@link Disposition#INLINE}
     *
     * @param name the download file name
     * @return this result
     */
    public RenderBinary name(String name) {
        this.name = name;
        return this;
    }

    /**
     * {@inheritDoc}
     *
     * @param cause the cause
     * @return this error result
     */
    @Override
    public RenderBinary initCause(Throwable cause) {
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
    public RenderBinary overwriteStatus(H.Status status) {
        super.overwriteStatus(status);
        return this;
    }

    /**
     * {@inheritDoc}
     *
     * @param name  the header name
     * @param value the header value
     * @return this error result
     */
    @Override
    public RenderBinary header(String name, String value) {
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
    public RenderBinary header(H.Header header) {
        super.header(header);
        return this;
    }

    /**
     * {@inheritDoc}
     *
     * @param name   the header name
     * @param values the values to be added
     * @return this error result
     */
    @Override
    public RenderBinary addHeader(String name, String... values) {
        super.addHeader(name, values);
        return this;
    }

    /**
     * {@inheritDoc}
     *
     * @param cookie the cookie to be set
     * @return this error result
     */
    @Override
    public RenderBinary cookie(H.Cookie cookie) {
        super.cookie(cookie);
        return this;
    }

    /**
     * {@inheritDoc}
     *
     * @param name the session variable name
     * @param val  the session variable value
     * @return this error result
     */
    @Override
    public RenderBinary session(String name, Object val) {
        super.session(name, val);
        return this;
    }

    /**
     * {@inheritDoc}
     *
     * @param name the flash variable name
     * @param val  the flash variable value
     * @return this error result
     */
    @Override
    public RenderBinary flash(String name, Object val) {
        super.flash(name, val);
        return this;
    }

    /**
     * {@inheritDoc}
     *
     * @param location the location URL
     * @return this result
     */
    @Override
    public RenderBinary location(String location) {
        super.location(location);
        return this;
    }

    /**
     * {@inheritDoc}
     *
     * @param contentType the content type string
     * @return this result
     */
    @Override
    public RenderBinary contentType(String contentType) {
        super.contentType(contentType);
        return this;
    }

    @Override
    public RenderBinary contentType(H.Format contentType) {
        super.contentType(contentType);
        return this;
    }

    /**
     * {@inheritDoc}
     *
     * @param etag the etag value
     * @return this result
     */
    @Override
    public RenderBinary etag(String etag) {
        super.etag(etag);
        return this;
    }

    @Override
    protected void prepareHeaderAndCookies(H.Context context) {
        H.Response resp = context.resp();
        ensureContentType(resp);
        resp.contentDisposition(name, disposition.isInline());
        if (0 < length) {
            resp.header(CONTENT_LENGTH, S.string(length));
        }
    }

    @Override
    protected void applyBody(H.Request request, H.Response response) {
        if (null != binary) {
            response.writeBinary(binary);
        } else {
            outputStreamVisitor.apply(response.outputStream());
        }
    }

    private void ensureContentType(H.Response response) {
        String contentType = "application/octet-stream";
        if (S.notBlank(name)) {
            String ext = S.afterLast(name, ".");
            if (S.notBlank(ext)) {
                H.Format format = H.Format.of(ext);
                if (null != format) {
                    contentType = format.contentType();
                }
            }
        }
        response.initContentType(contentType);
    }

}
