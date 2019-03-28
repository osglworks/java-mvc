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

import static org.osgl.http.H.Status.UNAUTHORIZED;

import org.osgl.$;
import org.osgl.http.H;
import org.osgl.util.*;

/**
 * HTTP 401 Unauthorized,
 *
 * Note digest type is not supported yet
 */
public class Unauthorized extends ErrorResult {

    private static final String PAYLOAD_KEY = "401";

    public static final Unauthorized INSTANCE = new Unauthorized();

    private static final Unauthorized _INSTANCE = new Unauthorized() {
        @Override
        protected String realm() {
            $.T2<String, Type> data = payload().getValue(PAYLOAD_KEY);
            return null == data ? null : data._1;
        }

        public String getMessage() {
            return payload().message;
        }

        @Override
        public Integer errorCode() {
            return payload().errorCode;
        }

        @Override
        protected Type type() {
            $.T2<String, Type> data = payload().getValue(PAYLOAD_KEY);
            return null == data ? null : data._2;
        }

        @Override
        public long timestamp() {
            return payload().timestamp;
        }
    };

    enum Type {
        BASIC () {
            @Override
            String header(Unauthorized data) {
                StringBuilder sb = S.builder("Basic realm=\"")
                        .append(Codec.encodeBase64(data.realm())).append("\"");
                return sb.toString();
            }
        },
        DIGEST () {
            @Override
            String header(Unauthorized data) {
                StringBuilder sb = S.builder("Digest realm=\"")
                        .append(data.realm()).append("\",\nqop=\"auth,auth-int\",\nnonce=\"")
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

    public Unauthorized(int errorCode) {
        super(UNAUTHORIZED, errorCode);
        this.type = Type.FORM;
    }

    public Unauthorized(int errorCode, String message) {
        super(UNAUTHORIZED, errorCode, message);
        this.type = Type.FORM;
    }

    public Unauthorized(String realm) {
        super(UNAUTHORIZED);
        this.realm = realm;
        this.type = S.blank(realm) ? Type.FORM : Type.BASIC;
    }

    public Unauthorized(String realm, boolean digest) {
        super(UNAUTHORIZED);
        this.realm = realm;
        this.type = digest ? Type.DIGEST : Type.BASIC;
        if (digest) {
            throw E.unsupport("Digest access authentication is currently not supported");
        }
    }

    @Override
    public void apply(H.Request req, H.Response resp) {
        applyBeforeCommitHandler(req, resp);
        try {
            applyStatus(resp);
            applyCookies(resp);
            applyHeaders(resp);
            resp.header(H.Header.Names.WWW_AUTHENTICATE, type().header(this));
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

    protected Type type() {
        return type;
    }

    protected String realm() {
        return realm;
    }


    /**
     * Returns the {@link #INSTANCE static Unauthorized instance}
     * @return the static Unauthorized instance
     */
    public static Unauthorized get() {
        return INSTANCE;
    }

    /**
     * Returns a static Unauthorized instance and set the {@link #payload} thread local
     * with realm and type
     *
     * When calling the instance on {@link #realm()} and {@link #type()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param realm the authentication realm
     * @return a static Unauthorized instance as described above
     */
    public static Unauthorized of(String realm) {
        touchPayload().putValue(PAYLOAD_KEY, $.T2(realm, S.blank(realm) ? Type.FORM : Type.BASIC));
        return _INSTANCE;
    }

    /**
     * Returns a static Unauthorized instance and set the {@link #payload} thread local with
     * error code.
     *
     * @param errorCode the error code
     * @return a static Unauthorized instance as described above
     */
    public static Unauthorized of(int errorCode) {
        touchPayload().errorCode = errorCode;
        return _INSTANCE;
    }

    /**
     * Returns a static Unauthorized instance and set the {@link #payload} thread local with
     * error code and message.
     *
     * @param errorCode the error code
     * @param message the error message
     * @return a static Unauthorized instance as described above
     */
    public static Unauthorized of(int errorCode, String message) {
        touchPayload().errorCode(errorCode).message(message);
        return _INSTANCE;
    }

}
