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

import static org.osgl.http.H.Status.BAD_GATEWAY;

/**
 * The server was acting as a gateway or proxy and received an invalid response from the upstream server.
 */
public class BadGateway extends ErrorResult {

    private static final BadGateway _INSTANCE = new BadGateway() {
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

    public BadGateway() {
        super(BAD_GATEWAY);
    }
    public BadGateway(String message, Object... args) {
        super(BAD_GATEWAY, message, args);
    }
    public BadGateway(Throwable t) {
        super(BAD_GATEWAY, t);
    }
    public BadGateway(Throwable t, String message, Object... args) {
        super(BAD_GATEWAY, t, message, args);
    }

    public BadGateway(int errorCode) {
        super(BAD_GATEWAY, errorCode);
    }
    public BadGateway(int errorCode, String message, Object... args) {
        super(BAD_GATEWAY, errorCode, message, args);
    }
    public BadGateway(int errorCode, Throwable cause) {
        super(BAD_GATEWAY, errorCode, cause);
    }
    public BadGateway(int errorCode, Throwable cause, String message, Object... args) {
        super(BAD_GATEWAY, errorCode, cause, message, args);
    }


    /**
     * Returns a static BadGateway instance and set the {@link #payload} thread local
     * with default message.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @return a static BadGateway instance as described above
     */
    public static BadGateway get() {
        if (_localizedErrorMsg()) {
            return of(defaultMessage(BAD_GATEWAY));
        } else {
            touchPayload();
            return _INSTANCE;
        }
    }

    /**
     * Returns a static BadGateway instance and set the {@link #payload} thread local
     * with message specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param message the message
     * @param args the message arguments
     * @return a static BadGateway instance as described above
     */
    public static BadGateway of(String message, Object... args) {
        touchPayload().message(message, args);
        return _INSTANCE;
    }

    /**
     * Returns a static BadGateway instance and set the {@link #payload} thread local
     * with cause specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param cause the cause
     * @return a static BadGateway instance as described above
     */
    public static BadGateway of(Throwable cause) {
        if (_localizedErrorMsg()) {
            return of(cause, defaultMessage(BAD_GATEWAY));
        } else {
            touchPayload().cause(cause);
            return _INSTANCE;
        }
    }

    /**
     * Returns a static BadGateway instance and set the {@link #payload} thread local
     * with cause and message specified
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local; When calling the instance on
     * {@link #getCause()} method, it will return whatever stored in the {@link #payload}
     * thread local
     *
     * @param cause the cause
     * @param message the message
     * @param args the message arguments
     * @return a static BadGateway instance as described above
     */
    public static BadGateway of(Throwable cause, String message, Object... args) {
        touchPayload().cause(cause).message(message, args);
        return _INSTANCE;
    }

    /**
     * Returns a static BadGateway instance and set the {@link #payload} thread local
     * with error code specified
     *
     * @param errorCode the app defined error code
     * @return a static BadGateway instance as described above
     */
    public static BadGateway of(int errorCode) {
        if (_localizedErrorMsg()) {
            return of(errorCode, defaultMessage(BAD_GATEWAY));
        } else {
            touchPayload().errorCode(errorCode);
            return _INSTANCE;
        }
    }

    /**
     * Returns a static BadGateway instance and set the {@link #payload} thread local
     * with error code and message specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param errorCode the app defined error code
     * @param message the message
     * @param args the message arguments
     * @return a static BadGateway instance as described above
     */
    public static BadGateway of(int errorCode, String message, Object... args) {
        touchPayload().errorCode(errorCode).message(message, args);
        return _INSTANCE;
    }

    /**
     * Returns a static BadGateway instance and set the {@link #payload} thread local
     * with error code and cause specified
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local; When calling the instance on
     * {@link #getCause()} method, it will return whatever stored in the {@link #payload}
     * thread local
     *
     * @param errorCode the app defined error code
     * @param cause the cause
     * @return a static BadGateway instance as described above
     */
    public static BadGateway of(int errorCode, Throwable cause) {
        if (_localizedErrorMsg()) {
            return of(errorCode, cause, defaultMessage(BAD_GATEWAY));
        } else {
            touchPayload().errorCode(errorCode).cause(cause);
            return _INSTANCE;
        }
    }

    /**
     * Returns a static BadGateway instance and set the {@link #payload} thread local
     * with error code, cause and message specified
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local; When calling the instance on
     * {@link #getCause()} method, it will return whatever stored in the {@link #payload}
     * thread local
     *
     * @param errorCode the app defined error code
     * @param cause the cause
     * @param message the message
     * @param args the message arguments
     * @return a static BadGateway instance as described above
     */
    public static BadGateway of(int errorCode, Throwable cause, String message, Object... args) {
        touchPayload().errorCode(errorCode).cause(cause).message(message, args);
        return _INSTANCE;
    }

}
