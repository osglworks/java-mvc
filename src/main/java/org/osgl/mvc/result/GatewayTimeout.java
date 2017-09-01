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

import static org.osgl.http.H.Status.GATEWAY_TIMEOUT;

/**
 * The server was acting as a gateway or proxy and did not receive a timely response
 * from the upstream server.
 */
public class GatewayTimeout extends ErrorResult {

    private static final GatewayTimeout _INSTANCE = new GatewayTimeout() {
        @Override
        public String getMessage() {
            return payload().message;
        }

        @Override
        public synchronized Throwable getCause() {
            return payload().cause;
        }

        @Override
        public Integer errorCode() {
            return payload().errorCode;
        }

        @Override
        public long timestamp() {
            return payload().timestamp;
        }
    };

    public GatewayTimeout() {
        super(GATEWAY_TIMEOUT);
    }
    public GatewayTimeout(String message, Object... args) {
        super(GATEWAY_TIMEOUT, message, args);
    }
    public GatewayTimeout(Throwable t) {
        super(GATEWAY_TIMEOUT, t);
    }
    public GatewayTimeout(Throwable t, String message, Object... args) {
        super(GATEWAY_TIMEOUT, t, message, args);
    }

    public GatewayTimeout(int errorCode) {
        super(GATEWAY_TIMEOUT, errorCode);
    }
    public GatewayTimeout(int errorCode, String message, Object... args) {
        super(GATEWAY_TIMEOUT, errorCode, message, args);
    }
    public GatewayTimeout(int errorCode, Throwable cause) {
        super(GATEWAY_TIMEOUT, errorCode, cause);
    }
    public GatewayTimeout(int errorCode, Throwable cause, String message, Object... args) {
        super(GATEWAY_TIMEOUT, errorCode, cause, message, args);
    }

    /**
     * Returns a static GatewayTimeout instance and set the {@link #payload} thread local
     * with default message.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @return a static GatewayTimeout instance as described above
     */
    public static GatewayTimeout get() {
        if (_localizedErrorMsg()) {
            return of(defaultMessage(GATEWAY_TIMEOUT));
        } else {
            touchPayload();
            return _INSTANCE;
        }
    }

    /**
     * Returns a static GatewayTimeout instance and set the {@link #payload} thread local
     * with message specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param message the message
     * @param args the message arguments
     * @return a static GatewayTimeout instance as described above
     */
    public static GatewayTimeout of(String message, Object... args) {
        touchPayload().message(message, args);
        return _INSTANCE;
    }

    /**
     * Returns a static GatewayTimeout instance and set the {@link #payload} thread local
     * with cause specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param cause the cause
     * @return a static GatewayTimeout instance as described above
     */
    public static GatewayTimeout of(Throwable cause) {
        if (_localizedErrorMsg()) {
            return of(cause, defaultMessage(GATEWAY_TIMEOUT));
        } else {
            touchPayload().cause(cause);
            return _INSTANCE;
        }
    }

    /**
     * Returns a static GatewayTimeout instance and set the {@link #payload} thread local
     * with cause and message specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param cause  the cause
     * @param message the message
     * @param args the message arguments
     * @return a static GatewayTimeout instance as described above
     */
    public static GatewayTimeout of(Throwable cause, String message, Object... args) {
        touchPayload().message(message, args).cause(cause);
        return _INSTANCE;
    }

    /**
     * Returns a static GatewayTimeout instance and set the {@link #payload} thread local
     * with error code and default message.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param errorCode the app defined error code
     * @return a static GatewayTimeout instance as described above
     */
    public static GatewayTimeout of(int errorCode) {
        if (_localizedErrorMsg()) {
            return of(errorCode, defaultMessage(GATEWAY_TIMEOUT));
        } else {
            touchPayload().errorCode(errorCode);
            return _INSTANCE;
        }
    }

    /**
     * Returns a static GatewayTimeout instance and set the {@link #payload} thread local
     * with error code and message specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param errorCode app defined error code
     * @param message the message
     * @param args the message arguments
     * @return a static GatewayTimeout instance as described above
     */
    public static GatewayTimeout of(int errorCode, String message, Object... args) {
        touchPayload().message(message, args).errorCode(errorCode);
        return _INSTANCE;
    }

    /**
     * Returns a static GatewayTimeout instance and set the {@link #payload} thread local
     * with error code and cause specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param cause  the cause
     * @param errorCode app defined error code
     * @return a static GatewayTimeout instance as described above
     */
    public static GatewayTimeout of(int errorCode, Throwable cause) {
        if (_localizedErrorMsg()) {
            return of(errorCode, cause, defaultMessage(GATEWAY_TIMEOUT));
        } else {
            touchPayload().errorCode(errorCode).cause(cause);
            return _INSTANCE;
        }
    }

    /**
     * Returns a static GatewayTimeout instance and set the {@link #payload} thread local
     * with error code, cause and message specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param cause  the cause
     * @param errorCode app defined error code
     * @param message the message
     * @param args the message arguments
     * @return a static GatewayTimeout instance as described above
     */
    public static GatewayTimeout of(int errorCode, Throwable cause, String message, Object... args) {
        touchPayload().message(message, args).errorCode(errorCode).cause(cause);
        return _INSTANCE;
    }

}
