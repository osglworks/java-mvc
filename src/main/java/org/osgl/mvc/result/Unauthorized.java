package org.osgl.mvc.result;

import org.osgl.$;
import org.osgl.http.H;
import org.osgl.util.Codec;
import org.osgl.util.E;
import org.osgl.util.S;

/**
 * HTTP 401 Unauthorized,
 *
 * Note digest type is not supported yet
 *
 * TODO: support more authenticate schemes:
 * see http://www.iana.org/assignments/http-authschemes/http-authschemes.xhtml
 */
public class Unauthorized extends ErrorResult {

    /**
     * Defines three types of `Unauthorized` result:
     *
     * * BASIC
     * * DIGEST
     * * FORM
     */
    enum Type {
        BASIC () {
            @Override
            String header(Unauthorized data) {
                StringBuilder sb = S.builder("Basic realm=\"")
                        .append(Codec.encodeBase64(data.realm)).append("\"");
                return sb.toString();
            }
        },
        DIGEST () {
            @Override
            String header(Unauthorized data) {
                StringBuilder sb = S.builder("Digest realm=\"")
                        .append(data.realm).append("\",\nqop=\"auth,auth-int\",\nnonce=\"")
                        .append(S.random(34)).append("\",\nopaque=\"")
                        .append(S.random(32)).append("\"");
                return sb.toString();
            }
        },
        FORM () { /* non-standard */
            @Override
            String header(Unauthorized data) {
                return "Form";
            }
        }
        ;
        abstract String header(Unauthorized data);
    }

    private String realm;
    private Type type;

    public Unauthorized() {
        super(H.Status.UNAUTHORIZED);
        this.type = Type.FORM;
    }

    /**
     * {@inheritDoc}
     * @param errorCode the error code to be set
     * @return this result
     * @throws IllegalStateException
     */
    @Override
    public Unauthorized initErrorCode(int errorCode) throws IllegalStateException {
        super.initErrorCode(errorCode);
        return this;
    }

    /**
     * {@inheritDoc}
     * @param attachment the object to be attached
     * @return this result
     */
    @Override
    public Unauthorized attach(Object attachment) {
        super.attach(attachment);
        return this;
    }

    /**
     * {@inheritDoc}
     * @param cause the cause
     * @return this error result
     */
    @Override
    public Unauthorized initCause(Throwable cause) {
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
    public Unauthorized overwriteStatus(H.Status status) {
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
    public Unauthorized header(String name, String value) {
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
    public Unauthorized header(H.Header header) {
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
    public Unauthorized addHeader(String name, String... values) {
        super.addHeader(name, values);
        return this;
    }

    /**
     * {@inheritDoc}
     * @param cookie the cookie to be set
     * @return this error result
     */
    @Override
    public Unauthorized cookie(H.Cookie cookie) {
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
    public Unauthorized session(String name, Object val) {
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
    public Unauthorized flash(String name, Object val) {
        super.flash(name, val);
        return this;
    }

    /**
     * {@inheritDoc}
     * @param location the location URL
     * @return this result
     */
    @Override
    public Unauthorized location(String location) {
        super.location(location);
        return this;
    }

    /**
     * {@inheritDoc}
     * @param contentType the content type string
     * @return this result
     */
    @Override
    public Unauthorized contentType(String contentType) {
        super.contentType(contentType);
        return this;
    }

    /**
     * {@inheritDoc}
     * @param contentType the media format
     * @return this result
     */
    @Override
    public Unauthorized contentType(H.Format contentType) {
        super.contentType(contentType);
        return this;
    }

    /**
     * {@inheritDoc}
     * @param etag the etag value
     * @return this result
     */
    @Override
    public Unauthorized etag(String etag) {
        super.etag(etag);
        return this;
    }
    

    public Unauthorized type(Type type) {
        E.unsupportedIf($.notNull(type) == Type.DIGEST, "Digest authentication is currently not supported");
        this.type = type;
        return this;
    }

    public Unauthorized realm(String realm) {
        this.realm = realm;
        this.type = S.blank(realm) ? Type.FORM : Type.BASIC;
        return this;
    }

    @Override
    protected void prepareHeaderAndCookies(H.Context context) {
        this.header(H.Header.Names.WWW_AUTHENTICATE, this.type.header(this));
    }
}
