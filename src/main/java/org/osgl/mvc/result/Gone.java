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

import static org.osgl.http.H.Status.GONE;

/**
 * Indicates that the resource requested is no longer available and will not be available again.
 * This should be used when a resource has been intentionally removed and the resource should be
 * purged. Upon receiving a 410 status code, the client should not request the resource in the
 * future. Clients such as search engines should remove the resource from their indices. Most use
 * cases do not require clients and search engines to purge the resource, and a "404 Not Found"
 * may be used instead.
 */
public class Gone extends ErrorResult {


    private static final Gone _INSTANCE = new Gone() {
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

    public Gone() {
        super(GONE);
    }

    public Gone(String message, Object... args) {
        super(GONE, message, args);
    }

    public Gone(Throwable cause, String message, Object... args) {
        super(GONE, cause, message, args);
    }

    public Gone(Throwable cause) {
        super(GONE, cause);
    }


    public Gone(int errorCode) {
        super(GONE, errorCode);
    }

    public Gone(int errorCode, String message, Object... args) {
        super(GONE, errorCode, message, args);
    }

    public Gone(int errorCode, Throwable cause, String message, Object... args) {
        super(GONE, errorCode, cause, message, args);
    }

    public Gone(int errorCode, Throwable cause) {
        super(GONE, errorCode, cause);
    }

    /**
     * Returns a static Gone instance and set the {@link #payload} thread local
     * with default message.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @return a static Gone instance as described above
     */
    public static Gone get() {
        if (_localizedErrorMsg()) {
            return of(defaultMessage(GONE));
        } else {
            touchPayload();
            return _INSTANCE;
        }
    }

    /**
     * Returns a static Gone instance and set the {@link #payload} thread local
     * with message specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param message the message
     * @param args the message arguments
     * @return a static Gone instance as described above
     */
    public static Gone of(String message, Object... args) {
        touchPayload().message(message, args);
        return _INSTANCE;
    }

    /**
     * Returns a static Gone instance and set the {@link #payload} thread local
     * with cause specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param cause the cause
     * @return a static Gone instance as described above
     */
    public static Gone of(Throwable cause) {
        if (_localizedErrorMsg()) {
            return of(cause, defaultMessage(GONE));
        } else {
            touchPayload().cause(cause);
            return _INSTANCE;
        }
    }

    /**
     * Returns a static Gone instance and set the {@link #payload} thread local
     * with cause and message specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param cause the cause
     * @param message the message
     * @param args the message arguments
     * @return a static Gone instance as described above
     */
    public static Gone of(Throwable cause, String message, Object... args) {
        touchPayload().message(message, args).cause(cause);
        return _INSTANCE;
    }

    /**
     * Returns a static Gone instance and set the {@link #payload} thread local
     * with error code and default message.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param errorCode the app defined error code
     * @return a static Gone instance as described above
     */
    public static Gone of(int errorCode) {
        if (_localizedErrorMsg()) {
            return of(errorCode, defaultMessage(GONE));
        } else {
            touchPayload().errorCode(errorCode);
            return _INSTANCE;
        }
    }

    /**
     * Returns a static Gone instance and set the {@link #payload} thread local
     * with error code and message specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param errorCode the app defined error code
     * @param message the message
     * @param args the message arguments
     * @return a static Gone instance as described above
     */
    public static Gone of(int errorCode, String message, Object... args) {
        touchPayload().errorCode(errorCode).message(message, args);
        return _INSTANCE;
    }


    /**
     * Returns a static Gone instance and set the {@link #payload} thread local
     * with error code and cause specified
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param cause  the cause
     * @param errorCode the app defined error code
     * @return a static Gone instance as described above
     */
    public static Gone of(int errorCode, Throwable cause) {
        if (_localizedErrorMsg()) {
            return of(errorCode, cause, defaultMessage(GONE));
        } else {
            touchPayload().errorCode(errorCode).cause(cause);
            return _INSTANCE;
        }
    }


    /**
     * Returns a static Gone instance and set the {@link #payload} thread local
     * with error code, cause and message specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param cause  the cause
     * @param errorCode the app defined error code
     * @param message the message
     * @param args the message arguments
     * @return a static Gone instance as described above
     */
    public static Gone of(int errorCode, Throwable cause, String message, Object... args) {
        touchPayload().errorCode(errorCode).message(message, args).cause(cause);
        return _INSTANCE;
    }
}
