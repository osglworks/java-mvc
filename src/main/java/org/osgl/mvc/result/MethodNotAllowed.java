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

import static org.osgl.http.H.Status.METHOD_NOT_ALLOWED;

/**
 * Indicate a 405 Method not allowed response
 */
public class MethodNotAllowed extends ErrorResult {

    /**
     * The static instance of `MethodNotAllowed` result
     *
     * This field is deprecated, please use {@link #get()} instead
     */
    @Deprecated
    public static final MethodNotAllowed INSTANCE = new MethodNotAllowed();

    private static final MethodNotAllowed _INSTANCE = new MethodNotAllowed() {
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

    public MethodNotAllowed() {
        super(METHOD_NOT_ALLOWED);
    }

    public MethodNotAllowed(String message, Object... args) {
        super(METHOD_NOT_ALLOWED, message, args);
    }

    public MethodNotAllowed(Throwable cause, String message, Object ... args) {
        super(METHOD_NOT_ALLOWED, cause, message, args);
    }

    public MethodNotAllowed(Throwable cause) {
        super(METHOD_NOT_ALLOWED, cause);
    }

    public MethodNotAllowed(int errorCode) {
        super(METHOD_NOT_ALLOWED, errorCode);
    }

    public MethodNotAllowed(int errorCode, String message, Object... args) {
        super(METHOD_NOT_ALLOWED, errorCode, message, args);
    }

    public MethodNotAllowed(int errorCode, Throwable cause, String message, Object ... args) {
        super(METHOD_NOT_ALLOWED, cause, message, args);
    }

    public MethodNotAllowed(int errorCode, Throwable cause) {
        super(METHOD_NOT_ALLOWED, errorCode, cause);
    }

    /**
     * Returns a static MethodNotAllowed instance and set the {@link #payload} thread local
     * with default message.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @return a static MethodNotAllowed instance as described above
     */
    public static MethodNotAllowed get() {
        if (_localizedErrorMsg()) {
            return of(defaultMessage(METHOD_NOT_ALLOWED));
        } else {
            touchPayload();
            return _INSTANCE;
        }
    }

    /**
     * Returns a static MethodNotAllowed instance and set the {@link #payload} thread local
     * with message specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param message the message
     * @param args the message arguments
     * @return a static MethodNotAllowed instance as described above
     */
    public static MethodNotAllowed of(String message, Object... args) {
        touchPayload().message(message, args);
        return _INSTANCE;
    }

    /**
     * Returns a static MethodNotAllowed instance and set the {@link #payload} thread local
     * with cause specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param cause the cause
     * @return a static MethodNotAllowed instance as described above
     */
    public static MethodNotAllowed of(Throwable cause) {
        if (_localizedErrorMsg()) {
            return of(cause, defaultMessage(METHOD_NOT_ALLOWED));
        } else {
            touchPayload().cause(cause);
            return _INSTANCE;
        }
    }

    /**
     * Returns a static MethodNotAllowed instance and set the {@link #payload} thread local
     * with cause and message specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param cause the cause
     * @param message the message
     * @param args the message arguments
     * @return a static MethodNotAllowed instance as described above
     */
    public static MethodNotAllowed of(Throwable cause, String message, Object... args) {
        touchPayload().message(message, args).cause(cause);
        return _INSTANCE;
    }

    /**
     * Returns a static MethodNotAllowed instance and set the {@link #payload} thread local
     * with error code and default message.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param errorCode the app defined error code
     * @return a static MethodNotAllowed instance as described above
     */
    public static MethodNotAllowed of(int errorCode) {
        if (_localizedErrorMsg()) {
            return of(errorCode, defaultMessage(METHOD_NOT_ALLOWED));
        } else {
            touchPayload().errorCode(errorCode);
            return _INSTANCE;
        }
    }

    /**
     * Returns a static MethodNotAllowed instance and set the {@link #payload} thread local
     * with error code and message specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param errorCode the app defined error code
     * @param message the message
     * @param args the message arguments
     * @return a static MethodNotAllowed instance as described above
     */
    public static MethodNotAllowed of(int errorCode, String message, Object... args) {
        touchPayload().errorCode(errorCode).message(message, args);
        return _INSTANCE;
    }


    /**
     * Returns a static MethodNotAllowed instance and set the {@link #payload} thread local
     * with error code and cause specified
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param cause  the cause
     * @param errorCode the app defined error code
     * @return a static MethodNotAllowed instance as described above
     */
    public static MethodNotAllowed of(int errorCode, Throwable cause) {
        if (_localizedErrorMsg()) {
            return of(errorCode, cause, defaultMessage(METHOD_NOT_ALLOWED));
        } else {
            touchPayload().errorCode(errorCode).cause(cause);
            return _INSTANCE;
        }
    }


    /**
     * Returns a static MethodNotAllowed instance and set the {@link #payload} thread local
     * with error code, cause and message specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param cause  the cause
     * @param errorCode the app defined error code
     * @param message the message
     * @param args the message arguments
     * @return a static MethodNotAllowed instance as described above
     */
    public static MethodNotAllowed of(int errorCode, Throwable cause, String message, Object... args) {
        touchPayload().errorCode(errorCode).message(message, args).cause(cause);
        return _INSTANCE;
    }
}
