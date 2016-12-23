package org.osgl.mvc.result;

import org.osgl.http.H;
import org.osgl.http.Http;
import org.osgl.util.Codec;
import org.osgl.util.E;
import org.osgl.util.S;

/**
 * HTTP 401 Unauthorized
 */
public class Unauthorized extends ErrorResult {

    public static final Unauthorized INSTANCE = new Unauthorized();

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
        this(null);
    }

    public Unauthorized(String realm) {
        super(Http.Status.UNAUTHORIZED, "Unauthorized");
        this.realm = realm;
        this.type = S.blank(realm) ? Type.FORM : Type.BASIC;
    }

    public Unauthorized(String realm, boolean digest) {
        super(Http.Status.UNAUTHORIZED);
        this.realm = realm;
        this.type = digest ? Type.DIGEST : Type.BASIC;
        if (digest) {
            throw E.unsupport("Digest access authentication is currently not supported");
        }
    }

    @Override
    public void apply(H.Request req, H.Response resp) {
        applyStatus(resp);
        resp.header(H.Header.Names.WWW_AUTHENTICATE, type.header(this));
        applyMessage(req, resp);
    }
}
