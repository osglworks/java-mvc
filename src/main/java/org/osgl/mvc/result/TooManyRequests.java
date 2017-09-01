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

import static org.osgl.http.H.Status.TOO_MANY_REQUESTS;

/**
 * The user has sent too many requests in a given amount of time. Intended for use with rate-limiting schemes.
 */
public class TooManyRequests extends ErrorResult {

    /**
     * The static instance of TooManyRequests result.
     */
    public static final TooManyRequests INSTANCE = new TooManyRequests();

    private static final TooManyRequests _INSTANCE = new TooManyRequests() {
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

    public TooManyRequests() {
        super(TOO_MANY_REQUESTS);
    }

    public TooManyRequests(String message, Object... args) {
        super(TOO_MANY_REQUESTS, message, args);
    }

    public TooManyRequests(Throwable cause, String message, Object... args) {
        super(TOO_MANY_REQUESTS, cause, message, args);
    }

    public TooManyRequests(Throwable cause) {
        super(TOO_MANY_REQUESTS, cause);
    }

    public TooManyRequests(int errorCode) {
        super(TOO_MANY_REQUESTS, errorCode);
    }

    public TooManyRequests(int errorCode, String message, Object... args) {
        super(TOO_MANY_REQUESTS, errorCode, message, args);
    }

    public TooManyRequests(int errorCode, Throwable cause, String message, Object... args) {
        super(TOO_MANY_REQUESTS, errorCode, cause, message, args);
    }

    public TooManyRequests(int errorCode, Throwable cause) {
        super(TOO_MANY_REQUESTS, errorCode, cause);
    }

    /**
     * Returns a static TooManyRequests instance and set the {@link #payload} thread local
     * with default message.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @return a static TooManyRequests instance as described above
     */
    public static TooManyRequests get() {
        if (_localizedErrorMsg()) {
            return of(defaultMessage(TOO_MANY_REQUESTS));
        } else {
            touchPayload();
            return _INSTANCE;
        }
    }

    /**
     * Returns a static TooManyRequests instance and set the {@link #payload} thread local
     * with message specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param message the message
     * @param args the message arguments
     * @return a static TooManyRequests instance as described above
     */
    public static TooManyRequests of(String message, Object... args) {
        touchPayload().message(message, args);
        return _INSTANCE;
    }

    /**
     * Returns a static TooManyRequests instance and set the {@link #payload} thread local
     * with cause specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param cause the cause
     * @return a static TooManyRequests instance as described above
     */
    public static TooManyRequests of(Throwable cause) {
        if (_localizedErrorMsg()) {
            return of(cause, defaultMessage(TOO_MANY_REQUESTS));
        } else {
            touchPayload().cause(cause);
            return _INSTANCE;
        }
    }

    /**
     * Returns a static TooManyRequests instance and set the {@link #payload} thread local
     * with cause and message specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param cause the cause
     * @param message the message
     * @param args the message arguments
     * @return a static TooManyRequests instance as described above
     */
    public static TooManyRequests of(Throwable cause, String message, Object... args) {
        touchPayload().message(message, args).cause(cause);
        return _INSTANCE;
    }

    /**
     * Returns a static TooManyRequests instance and set the {@link #payload} thread local
     * with error code and default message.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param errorCode the app defined error code
     * @return a static TooManyRequests instance as described above
     */
    public static TooManyRequests of(int errorCode) {
        if (_localizedErrorMsg()) {
            return of(errorCode, defaultMessage(TOO_MANY_REQUESTS));
        } else {
            touchPayload().errorCode(errorCode);
            return _INSTANCE;
        }
    }

    /**
     * Returns a static TooManyRequests instance and set the {@link #payload} thread local
     * with error code and message specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param errorCode the app defined error code
     * @param message the message
     * @param args the message arguments
     * @return a static TooManyRequests instance as described above
     */
    public static TooManyRequests of(int errorCode, String message, Object... args) {
        touchPayload().errorCode(errorCode).message(message, args);
        return _INSTANCE;
    }


    /**
     * Returns a static TooManyRequests instance and set the {@link #payload} thread local
     * with error code and cause specified
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param cause  the cause
     * @param errorCode the app defined error code
     * @return a static TooManyRequests instance as described above
     */
    public static TooManyRequests of(int errorCode, Throwable cause) {
        if (_localizedErrorMsg()) {
            return of(errorCode, cause, defaultMessage(TOO_MANY_REQUESTS));
        } else {
            touchPayload().errorCode(errorCode).cause(cause);
            return _INSTANCE;
        }
    }


    /**
     * Returns a static TooManyRequests instance and set the {@link #payload} thread local
     * with error code, cause and message specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param cause  the cause
     * @param errorCode the app defined error code
     * @param message the message
     * @param args the message arguments
     * @return a static TooManyRequests instance as described above
     */
    public static TooManyRequests of(int errorCode, Throwable cause, String message, Object... args) {
        touchPayload().errorCode(errorCode).message(message, args).cause(cause);
        return _INSTANCE;
    }
}
