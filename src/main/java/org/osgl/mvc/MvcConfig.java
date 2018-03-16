package org.osgl.mvc;

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

import static org.osgl.http.H.Status.*;

import com.alibaba.fastjson.JSON;
import org.osgl.$;
import org.osgl.Osgl;
import org.osgl.exception.NotAppliedException;
import org.osgl.http.H;
import org.osgl.http.HttpConfig;
import org.osgl.mvc.result.Result;
import org.osgl.util.C;
import org.osgl.util.E;
import org.osgl.util.Output;
import org.osgl.util.S;

import java.io.IOException;
import java.util.Map;

public class MvcConfig extends HttpConfig {

    public static final String MSG_ID_CREATED = "osgl.result.created";
    public static final String MSG_ID_ACCEPTED = "osgl.result.accepted";

    public static final String MSG_ID_CLIENT_ERROR = "osgl.result.client_error";
    public static final String MSG_ID_BAD_REQUEST = "osgl.result.bad_request";
    public static final String MSG_ID_UNAUTHORIZED = "osgl.result.unauthorized";
    public static final String MSG_ID_PAYMENT_REQUIRED = "osgl.result.payment_required";
    public static final String MSG_ID_FORBIDDEN = "osgl.result.forbidden";
    public static final String MSG_ID_NOT_FOUND = "osgl.result.not_found";
    public static final String MSG_ID_METHOD_NOT_ALLOWED = "osgl.result.method_not_allowed";
    public static final String MSG_ID_NOT_ACCEPTABLE = "osgl.result.not_acceptable";
    public static final String MSG_ID_PROXY_AUTHENTICATION_REQUIRED = "osgl.result.proxy_authentication_required";
    public static final String MSG_ID_REQUREST_TIMEOUT = "osgl.result.request_timeout";
    public static final String MSG_ID_CONFLICT = "osgl.result.conflict";
    public static final String MSG_ID_GONE = "osgl.result.gone";
    public static final String MSG_ID_TOO_MANY_REQUESTS = "osgl.result.too_many_requests";
    public static final String MSG_ID_UNKNOWN_STATUS = "osgl.result.unknown_status";

    public static final String MSG_ID_SERVER_ERROR = "osgl.result.server_error";
    public static final String MSG_ID_NOT_IMPLEMENTED = "osgl.result.not_implemented";

    private static final Map<H.Status, String> messageMap = C.map(
            ACCEPTED, MSG_ID_ACCEPTED,
            CREATED, MSG_ID_CREATED,
            BAD_REQUEST, MSG_ID_BAD_REQUEST,
            UNAUTHORIZED, MSG_ID_UNAUTHORIZED,
            PAYMENT_REQUIRED, MSG_ID_PAYMENT_REQUIRED,
            FORBIDDEN, MSG_ID_FORBIDDEN,
            NOT_FOUND, MSG_ID_NOT_FOUND,
            METHOD_NOT_ALLOWED, MSG_ID_METHOD_NOT_ALLOWED,
            NOT_ACCEPTABLE, MSG_ID_NOT_ACCEPTABLE,
            PROXY_AUTHENTICATION_REQUIRED, MSG_ID_PROXY_AUTHENTICATION_REQUIRED,
            REQUEST_TIMEOUT, MSG_ID_REQUREST_TIMEOUT,
            CONFLICT, MSG_ID_CONFLICT,
            GONE, MSG_ID_GONE,
            TOO_MANY_REQUESTS, MSG_ID_TOO_MANY_REQUESTS,
            NOT_IMPLEMENTED, MSG_ID_NOT_IMPLEMENTED
    );

    // Stores English error message that are not defined in separate ErrorResult implementation, e.g. NotFound
    private static final Map<H.Status, String> enMessageMap = C.map(
            ACCEPTED, "Accepted",
            CREATED, "Created",
            BAD_REQUEST, "Bad Request",
            UNAUTHORIZED, "Unauthorized",
            PAYMENT_REQUIRED, "Payment Required",
            FORBIDDEN, "Forbidden",
            NOT_FOUND, "Not Found",
            METHOD_NOT_ALLOWED, "Method Not Allowed",
            NOT_ACCEPTABLE, "Not Acceptable",
            PROXY_AUTHENTICATION_REQUIRED, "Proxy Authentication Required",
            REQUEST_TIMEOUT, "Request Timeout",
            CONFLICT, "Conflict",
            GONE, "Gone",
            TOO_MANY_REQUESTS, "Too Many Requests",
            NOT_IMPLEMENTED, "Not Implemented"
    );

    public static final String DEF_COOKIE_PREFIX = "OSGL";

    private static final $.Func3<Result, H.Request<?>, H.Response<?>, Void> DUMB_COMMIT_RESULT_LISTENER =
            new $.F3<Result, H.Request<?>, H.Response<?>, Void>() {
                @Override
                public Void apply(Result result, H.Request request, H.Response response) throws NotAppliedException, Osgl.Break {
                    return null;
                }
            };

    static String sessionCookieName = DEF_COOKIE_PREFIX + "_SESSION";
    static String flashCookieName = DEF_COOKIE_PREFIX + "_FLASH";
    static int sessionExpire = -1;
    static String secret;
    static $.Func2<Output, Object, ?> jsonSerializer = new $.Func2<Output, Object, Void> () {
        @Override
        public Void apply(Output sink, Object o) throws NotAppliedException, Osgl.Break {
                if (o instanceof CharSequence) {
                    sink.append((CharSequence) o);
                } else {
                    try {
                        JSON.writeJSONString(sink.asOutputStream(), o);
                    } catch (IOException e) {
                        throw E.ioException(e);
                    }
                }
            return null;
        }
    };
    static $.Func3<Result, H.Request<?>, H.Response<?>, ?> beforeCommitResultHandler = DUMB_COMMIT_RESULT_LISTENER;
    static $.Func3<Result, H.Request<?>, H.Response<?>, ?> afterCommitResultHandler = DUMB_COMMIT_RESULT_LISTENER;
    static $.Function<String, String> messageTranlater = $.F.identity();
    // We need this indirect to handle IE's nasty issue with application/json
    static $.Func0<H.Format> jsonMediaTypeProvider = new $.Func0<H.Format>() {
        @Override
        public H.Format apply() throws NotAppliedException, Osgl.Break {
            return H.Format.JSON;
        }
    };
    /*
     * By default we don't output encoding. See http://www.ietf.org/rfc/rfc7159.txt
     */
    static boolean renderJsonOutputCharset = false;

    /**
     * Set the cookie prefix for session and flash cookie
     *
     * @param prefix the prefix to prepend to session/flash cookie name
     */
    public static void cookiePrefix(String prefix) {
        E.illegalArgumentIf(S.blank(prefix));
        sessionCookieName = prefix + "_SESSION";
        flashCookieName = prefix = "_FLASH";
    }

    /**
     * Set session expire in seconds
     *
     * @param ttl the session expire in seconds
     */
    public static void sessionExpire(int ttl) {
        E.illegalArgumentIf(ttl < 0);
        sessionExpire = ttl;
    }

    public static void secret(String secret) {
        E.illegalArgumentIf(S.blank(secret));
        MvcConfig.secret = secret;
    }

    public static void renderJsonOutputCharset(boolean b) {
        renderJsonOutputCharset = b;
    }

    public static boolean renderJsonOutputCharset() {
        return renderJsonOutputCharset;
    }

    public static void jsonMediaTypeProvider($.Func0<H.Format> provider) {
        jsonMediaTypeProvider = $.notNull(provider);
    }

    public static $.Func0<H.Format> jsonMediaTypeProvider() {
        return jsonMediaTypeProvider;
    }

    public static void jsonSerializer($.Func2<Output, Object, ?> serializer) {
        MvcConfig.jsonSerializer = $.notNull(serializer);
    }

    public static void beforeCommitResultHandler($.Func3<Result, H.Request<?>, H.Response<?>, ?> beforeCommitResultHandler) {
        MvcConfig.beforeCommitResultHandler = $.notNull(beforeCommitResultHandler);
    }

    public static void applyBeforeCommitResultHandler(Result result, H.Request req, H.Response resp) {
        beforeCommitResultHandler.apply(result, req, resp);
    }

    public static void afterCommitResultHandler($.Func3<Result, H.Request<?>, H.Response<?>, ?> afterCommitResultHandler) {
        MvcConfig.afterCommitResultHandler = $.notNull(afterCommitResultHandler);
    }

    public static void applyAfterCommitResultHandler(Result result, H.Request<?> req, H.Response<?> resp) {
        afterCommitResultHandler.apply(result, req, resp);
    }

    public static void messageTranslater($.Function<String, String> translater) {
        MvcConfig.messageTranlater = $.notNull(translater);
    }

    public static $.Function<String, String> messageTranslater() {
        return messageTranlater;
    }

    public static $.Visitor<Output> jsonSerializer(final Object v) {
        return new $.Visitor<Output>() {
            @Override
            public void visit(Output sink) throws Osgl.Break {
                jsonSerializer.apply(sink, v);
            }
        };
    }

    private static ErrorPageRenderer errorPageRenderer = new ErrorPageRenderer();

    public static void errorPageRenderer(ErrorPageRenderer renderer) {
        E.NPE(renderer);
        errorPageRenderer = renderer;
    }

    public static ErrorPageRenderer errorPageRenderer() {
        return errorPageRenderer;
    }

    private static boolean localizedErrorMsg = false;

    public static void enableLocalizedErrorMsg() {
        localizedErrorMsg = true;
    }

    public static boolean localizedErrorMsg() {
        return localizedErrorMsg;
    }

    /**
     * Returns default error message id from http status
     *
     * @param status the http status
     * @return the corresponding error message id
     */
    public static String errorMessage(H.Status status) {
        boolean i18n = MvcConfig.localizedErrorMsg();
        String msg = i18n ? messageMap.get(status) : enMessageMap.get(status);

        if (null == msg) {
            if (status.isClientError()) {
                msg = i18n ? MSG_ID_CLIENT_ERROR : "Client Error";
            } else if (status.isServerError()) {
                msg = i18n ? MSG_ID_SERVER_ERROR : "Server Error";
            } else {
                msg = "Unknown status";
            }
        }

        if (i18n) {
            msg = messageTranlater.apply(msg);
        }

        return msg;
    }

    /**
     * Returns default error message id from http status code
     *
     * @param code the http status code
     * @return the corresponding error message id
     */
    public static String errorMessage(int code) {
        return errorMessage(H.Status.of(code));
    }
}
