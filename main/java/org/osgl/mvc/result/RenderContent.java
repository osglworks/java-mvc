package org.osgl.mvc.result;

import org.osgl.http.H;
import org.osgl.util.E;
import org.osgl.util.S;

/**
 * Created by luog on 23/03/14.
 */
public abstract class RenderContent extends Result {

    private String content;
    private H.Format format;
    private boolean outputEncoding;

    /**
     * Create a RenderContent object with content, format and
     * the outputEncoding set to {@code true}
     *
     * @param content the content to output
     * @param format the format of the content type
     */
    protected RenderContent(String content, H.Format format) {
        this(content, format, true);
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
     * @param content
     * @param format
     * @param outputEncoding
     */
    protected RenderContent(String content, H.Format format, boolean outputEncoding) {
        super(H.Status.OK);
        E.NPE(format);
        this.content = content;
        this.format = format;
        this.outputEncoding = outputEncoding;
    }

    protected void setContentType(H.Response resp) {
        String s = format.toContentType();
        if (outputEncoding) {
            String encoding = resp.characterEncoding();
            if (S.notEmpty(encoding)) {
                s = S.builder(s).append("; charset=").append(encoding).toString();
            }
        }
        resp.initContentType(s);
    }

    @Override
    public void apply(H.Request req, H.Response resp) {
        applyStatus(resp);
        setContentType(resp);
        resp.writeContent(content);
    }
}
