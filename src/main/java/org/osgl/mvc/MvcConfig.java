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

import com.alibaba.fastjson.JSON;
import org.osgl.$;
import org.osgl.Lang;
import org.osgl.exception.NotAppliedException;
import org.osgl.http.H;
import org.osgl.http.HttpConfig;
import org.osgl.mvc.result.Result;
import org.osgl.util.*;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.Writer;
import java.lang.reflect.Modifier;
import java.util.*;

import static org.osgl.http.H.Status.*;

public class MvcConfig extends HttpConfig {

    public static final String ALARM_BIG_CONTENT_ENCOUNTERED = "ALARM_BIG_CONTENT_ENCOUNTERED";

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
    public static final String MSG_ID_INTERNAL_SERVER_ERROR = "osgl.result.internal_server_error";
    public static final String MSG_ID_SERVICE_UNAVAILABLE = "osgl.result.service_unavailable";
    public static final String MSG_ID_NOT_IMPLEMENTED = "osgl.result.not_implemented";

    private static Map<String, String> messageMap;

    public static final String DEF_COOKIE_PREFIX = "OSGL";

    private static final ResourceBundle mvcEnResourceBound = ResourceBundle.getBundle("org.osgl.mvc.MvcConfig", Locale.ENGLISH);

    private static final $.Func3<Result, H.Request<?>, H.Response<?>, Void> DUMB_COMMIT_RESULT_LISTENER =
            new $.F3<Result, H.Request<?>, H.Response<?>, Void>() {
                @Override
                public Void apply(Result result, H.Request request, H.Response response) throws NotAppliedException, Lang.Break {
                    return null;
                }
            };

    static String sessionCookieName = DEF_COOKIE_PREFIX + "_SESSION";
    static String flashCookieName = DEF_COOKIE_PREFIX + "_FLASH";
    static int sessionExpire = -1;
    static String secret;
    static $.Func2<Writer, Object, ?> jsonSerializer = new $.Func2<Writer, Object, Void>() {
        @Override
        public Void apply(Writer sink, Object o) throws NotAppliedException, $.Break {
            if (o instanceof CharSequence) {
                IO.write(((CharSequence) o).toString(), sink);
            } else {
                JSON.writeJSONString(sink, o);
            }
            return null;
        }
    };
    private static final DumperOptions yamlDumpOptions = new DumperOptions();

    static {
        yamlDumpOptions.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        yamlDumpOptions.setPrettyFlow(true);
    }

    private static final ThreadLocal<Yaml> yamlHolder = new ThreadLocal<Yaml>() {
        @Override
        protected Yaml initialValue() {
            return new Yaml(yamlDumpOptions);
        }
    };
    static $.Func2<Writer, Object, ?> yamlSerializer = new $.Func2<Writer, Object, Void>() {
        @Override
        public Void apply(Writer sink, Object o) throws NotAppliedException, $.Break {
            if (null == o) {
                return null;
            }
            if (o instanceof CharSequence) {
                IO.write(((CharSequence) o).toString(), sink);
            } else {
                Yaml yaml = yamlHolder.get();
                if (o instanceof Map) {
                    IO.write(yaml.dumpAsMap(o), sink);
                } else if (o.getClass().isArray()) {
                    yaml.dump(new ArrayObjectIterator(o), sink);
                } else {
                    yaml.dump(o, sink);
                }
            }
            return null;
        }
    };
    static $.Func3<Result, H.Request<?>, H.Response<?>, ?> beforeCommitResultHandler = DUMB_COMMIT_RESULT_LISTENER;
    static $.Func3<Result, H.Request<?>, H.Response<?>, ?> afterCommitResultHandler = DUMB_COMMIT_RESULT_LISTENER;
    static $.Function<String, String> messageTranlater = new $.Transformer<String, String>() {
        @Override
        public String transform(String s) {
            ResourceBundle bundle = ResourceBundle.getBundle(MvcConfig.class.getName(), Locale.getDefault());
            if (bundle.containsKey(s)) {
                return bundle.getString(s);
            }
            return s;
        }
    };
    // We need this indirect to handle IE's nasty issue with application/json
    static $.Func0<H.Format> jsonMediaTypeProvider = new $.Func0<H.Format>() {
        @Override
        public H.Format apply() throws NotAppliedException, Lang.Break {
            return H.Format.JSON;
        }
    };
    static Map<String, $.Func0> alarmListeners = new IdentityHashMap<>();
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
        jsonMediaTypeProvider = $.requireNotNull(provider);
    }

    public static $.Func0<H.Format> jsonMediaTypeProvider() {
        return jsonMediaTypeProvider;
    }

    public static void jsonSerializer($.Func2<Writer, Object, ?> serializer) {
        MvcConfig.jsonSerializer = $.requireNotNull(serializer);
    }

    public static void yamlSerializer($.Func2<Writer, Object, ?> serializer) {
        MvcConfig.yamlSerializer = $.requireNotNull(serializer);
    }

    public static void beforeCommitResultHandler($.Func3<Result, H.Request<?>, H.Response<?>, ?> beforeCommitResultHandler) {
        MvcConfig.beforeCommitResultHandler = $.requireNotNull(beforeCommitResultHandler);
    }

    public static void applyBeforeCommitResultHandler(Result result, H.Request req, H.Response resp) {
        beforeCommitResultHandler.apply(result, req, resp);
    }

    public static void afterCommitResultHandler($.Func3<Result, H.Request<?>, H.Response<?>, ?> afterCommitResultHandler) {
        MvcConfig.afterCommitResultHandler = $.requireNotNull(afterCommitResultHandler);
    }

    public static void registerAlarmListener(String alarmType, $.Func0 listener) {
        alarmListeners.put(alarmType, listener);
    }

    public static void triggerAlarm(String alarmType) {
        $.Func0 listener = alarmListeners.get(alarmType);
        if (null != listener) {
            listener.apply();
        }
    }

    public static void applyAfterCommitResultHandler(Result result, H.Request<?> req, H.Response<?> resp) {
        afterCommitResultHandler.apply(result, req, resp);
    }

    public static void messageTranslater($.Function<String, String> translater) {
        MvcConfig.messageTranlater = $.requireNotNull(translater);
    }

    public static $.Function<String, String> messageTranslater() {
        return messageTranlater;
    }

    public static $.Visitor<Writer> jsonSerializer(final Object v) {
        return new $.Visitor<Writer>() {
            @Override
            public void visit(Writer sink) throws $.Break {
                jsonSerializer.apply(sink, v);
            }
        };
    }

    public static $.Visitor<Writer> yamlSerializer(final Object v) {
        return new $.Visitor<Writer>() {
            @Override
            public void visit(Writer sink) throws Lang.Break {
                yamlSerializer.apply(sink, v);
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

    private static String defMsg(int code) {
        if (code < 400) {
            return "";
        } else if (code < 500) {
            return "Client Error";
        } else {
            return "Server Error";
        }
    }

    /**
     * Returns default error message id from http status
     *
     * @param status the http status
     * @return the corresponding error message id
     */
    public static String errorMessage(H.Status status) {
        return errorMessage(status.code());
    }

    /**
     * Returns default error message id from http status code
     *
     * @param code the http status code
     * @return the corresponding error message id
     */
    public static String errorMessage(int code) {
        String key = "code." + code;
        String messageKey = messageMap().get(key);
        if (null == messageKey) {
            return defMsg(code);
        }
        boolean i18n = MvcConfig.localizedErrorMsg();
        if (i18n) {
            String ret = messageTranslater().apply(messageKey);
            if (S.blank(ret) || S.eq(ret, messageKey)) {
                return defMsg(code);
            }
            return ret;
        } else {
            return mvcEnResourceBound.containsKey(messageKey) ? mvcEnResourceBound.getString(messageKey) : defMsg(code);
        }
    }

    private static Map<String, String> messageMap() {
        if (null == messageMap) {
            messageMap = new HashMap<>();
            Properties p = IO.loadProperties(MvcConfig.class.getClassLoader().getResource("status_code_lookup.properties"));
            messageMap.putAll((Map) p);
        }
        return messageMap;
    }

    private static void dumpStatusMessageLookup() throws Exception {
        File file = new File("target/classes/org/osgl/mvc/result");
        String[] sa = file.list();
        for (String s : sa) {
            if (s.contains("$")) {
                continue;
            }
            String cn = s.substring(0, s.length() - 6);
            String key = "osgl.result." + Keyword.of(cn).snakeCase();
            cn = "org.osgl.mvc.result." + cn;
            try {
                Class<?> c = $.classForName(cn);
                if (Modifier.isAbstract(c.getModifiers())) {
                    continue;
                }
                Result r = (Result) $.newInstance(c);
                int statusCode = r.statusCode();
                System.out.println(S.fmt("code." + statusCode + "=" + key));
            } catch (Exception e) {
                continue;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println(errorMessage(401));
        //dumpStatusMessageLookup();
    }
}
