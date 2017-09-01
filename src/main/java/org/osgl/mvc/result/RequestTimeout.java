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

import static org.osgl.http.H.Status.REQUEST_TIMEOUT;

/**
 * The server timed out waiting for the request. According to HTTP specifications: "The client
 * did not produce a request within the time that the server was prepared to wait. The client MAY
 * repeat the request without modifications at any later time."
 */
public class RequestTimeout extends ErrorResult {

    private static final RequestTimeout _INSTANCE = new RequestTimeout() {
        @Override
        public String getMessage() {
            return payload().message;
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


    public RequestTimeout() {
        super(REQUEST_TIMEOUT);
    }
    public RequestTimeout(String message, Object ... args) {
        super(REQUEST_TIMEOUT, message, args);
    }
    public RequestTimeout(Throwable cause, String message, Object ... args) {
        super(REQUEST_TIMEOUT, cause, message, args);
    }
    public RequestTimeout(Throwable cause) {
        super(REQUEST_TIMEOUT, cause);
    }

    public RequestTimeout(int errorCode) {
        super(REQUEST_TIMEOUT, errorCode);
    }
    public RequestTimeout(int errorCode, String message, Object ... args) {
        super(REQUEST_TIMEOUT, errorCode, message, args);
    }
    public RequestTimeout(int errorCode, Throwable cause, String message, Object ... args) {
        super(REQUEST_TIMEOUT, errorCode, cause, message, args);
    }
    public RequestTimeout(int errorCode, Throwable cause) {
        super(REQUEST_TIMEOUT, errorCode, cause);
    }

    /**
     * Returns a static RequestTimeout instance and set the {@link #payload} thread local
     * with default message.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @return a static RequestTimeout instance as described above
     */
    public static RequestTimeout get() {
        if (_localizedErrorMsg()) {
            return of(defaultMessage(REQUEST_TIMEOUT));
        } else {
            touchPayload();
            return _INSTANCE;
        }
    }

    /**
     * Returns a static RequestTimeout instance and set the {@link #payload} thread local
     * with message specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param message the message
     * @param args the message arguments
     * @return a static RequestTimeout instance as described above
     */
    public static RequestTimeout of(String message, Object... args) {
        touchPayload().message(message, args);
        return _INSTANCE;
    }

    /**
     * Returns a static RequestTimeout instance and set the {@link #payload} thread local
     * with cause specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param cause the cause
     * @return a static RequestTimeout instance as described above
     */
    public static RequestTimeout of(Throwable cause) {
        if (_localizedErrorMsg()) {
            return of(cause, defaultMessage(REQUEST_TIMEOUT));
        } else {
            touchPayload().cause(cause);
            return _INSTANCE;
        }
    }

    /**
     * Returns a static RequestTimeout instance and set the {@link #payload} thread local
     * with cause and message specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param cause the cause
     * @param message the message
     * @param args the message arguments
     * @return a static RequestTimeout instance as described above
     */
    public static RequestTimeout of(Throwable cause, String message, Object... args) {
        touchPayload().message(message, args).cause(cause);
        return _INSTANCE;
    }

    /**
     * Returns a static RequestTimeout instance and set the {@link #payload} thread local
     * with error code and default message.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param errorCode the app defined error code
     * @return a static RequestTimeout instance as described above
     */
    public static RequestTimeout of(int errorCode) {
        if (_localizedErrorMsg()) {
            return of(errorCode, defaultMessage(REQUEST_TIMEOUT));
        } else {
            touchPayload().errorCode(errorCode);
            return _INSTANCE;
        }
    }

    /**
     * Returns a static RequestTimeout instance and set the {@link #payload} thread local
     * with error code and message specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param errorCode the app defined error code
     * @param message the message
     * @param args the message arguments
     * @return a static RequestTimeout instance as described above
     */
    public static RequestTimeout of(int errorCode, String message, Object... args) {
        touchPayload().errorCode(errorCode).message(message, args);
        return _INSTANCE;
    }


    /**
     * Returns a static RequestTimeout instance and set the {@link #payload} thread local
     * with error code and cause specified
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param cause  the cause
     * @param errorCode the app defined error code
     * @return a static RequestTimeout instance as described above
     */
    public static RequestTimeout of(int errorCode, Throwable cause) {
        if (_localizedErrorMsg()) {
            return of(errorCode, cause, defaultMessage(REQUEST_TIMEOUT));
        } else {
            touchPayload().errorCode(errorCode).cause(cause);
            return _INSTANCE;
        }
    }


    /**
     * Returns a static RequestTimeout instance and set the {@link #payload} thread local
     * with error code, cause and message specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param cause  the cause
     * @param errorCode the app defined error code
     * @param message the message
     * @param args the message arguments
     * @return a static RequestTimeout instance as described above
     */
    public static RequestTimeout of(int errorCode, Throwable cause, String message, Object... args) {
        touchPayload().errorCode(errorCode).message(message, args).cause(cause);
        return _INSTANCE;
    }

}
