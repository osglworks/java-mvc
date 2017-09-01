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

import static org.osgl.http.H.Status.NOT_FOUND;

/**
 * Indicate a 404 Not found response
 */
public class NotFound extends ErrorResult {

    /**
     * The static instance of NotFound result.、
     *
     * This field is deprecated. Please use {@link #get()} instead
     */
    @Deprecated
    public static final NotFound INSTANCE = new NotFound();

    private static final NotFound _INSTANCE = new NotFound() {
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

    public NotFound() {
        super(NOT_FOUND);
    }

    public NotFound(String message, Object... args) {
        super(NOT_FOUND, message, args);
    }

    public NotFound(Throwable cause, String message, Object... args) {
        super(NOT_FOUND, cause, message, args);
    }

    public NotFound(Throwable cause) {
        super(NOT_FOUND, cause);
    }


    public NotFound(int errorCode) {
        super(NOT_FOUND, errorCode);
    }

    public NotFound(int errorCode, String message, Object... args) {
        super(NOT_FOUND, errorCode, message, args);
    }

    public NotFound(int errorCode, Throwable cause, String message, Object... args) {
        super(NOT_FOUND, errorCode, cause, message, args);
    }

    public NotFound(int errorCode, Throwable cause) {
        super(NOT_FOUND, errorCode, cause);
    }

    /**
     * Returns a static NotFound instance and set the {@link #payload} thread local
     * with default message.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @return a static NotFound instance as described above
     */
    public static NotFound get() {
        if (_localizedErrorMsg()) {
            return of(defaultMessage(NOT_FOUND));
        } else {
            touchPayload();
            return _INSTANCE;
        }
    }

    /**
     * Returns a static NotFound instance and set the {@link #payload} thread local
     * with message specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param message the message
     * @param args the message arguments
     * @return a static NotFound instance as described above
     */
    public static NotFound of(String message, Object... args) {
        touchPayload().message(message, args);
        return _INSTANCE;
    }

    /**
     * Returns a static NotFound instance and set the {@link #payload} thread local
     * with cause specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param cause the cause
     * @return a static NotFound instance as described above
     */
    public static NotFound of(Throwable cause) {
        if (_localizedErrorMsg()) {
            return of(cause, defaultMessage(NOT_FOUND));
        } else {
            touchPayload().cause(cause);
            return _INSTANCE;
        }
    }

    /**
     * Returns a static NotFound instance and set the {@link #payload} thread local
     * with cause and message specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param cause the cause
     * @param message the message
     * @param args the message arguments
     * @return a static NotFound instance as described above
     */
    public static NotFound of(Throwable cause, String message, Object... args) {
        touchPayload().message(message, args).cause(cause);
        return _INSTANCE;
    }

    /**
     * Returns a static NotFound instance and set the {@link #payload} thread local
     * with error code and default message.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param errorCode the app defined error code
     * @return a static NotFound instance as described above
     */
    public static NotFound of(int errorCode) {
        if (_localizedErrorMsg()) {
            return of(errorCode, defaultMessage(NOT_FOUND));
        } else {
            touchPayload().errorCode(errorCode);
            return _INSTANCE;
        }
    }

    /**
     * Returns a static NotFound instance and set the {@link #payload} thread local
     * with error code and message specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param errorCode the app defined error code
     * @param message the message
     * @param args the message arguments
     * @return a static NotFound instance as described above
     */
    public static NotFound of(int errorCode, String message, Object... args) {
        touchPayload().errorCode(errorCode).message(message, args);
        return _INSTANCE;
    }


    /**
     * Returns a static NotFound instance and set the {@link #payload} thread local
     * with error code and cause specified
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param cause  the cause
     * @param errorCode the app defined error code
     * @return a static NotFound instance as described above
     */
    public static NotFound of(int errorCode, Throwable cause) {
        if (_localizedErrorMsg()) {
            return of(errorCode, cause, defaultMessage(NOT_FOUND));
        } else {
            touchPayload().errorCode(errorCode).cause(cause);
            return _INSTANCE;
        }
    }


    /**
     * Returns a static NotFound instance and set the {@link #payload} thread local
     * with error code, cause and message specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param cause  the cause
     * @param errorCode the app defined error code
     * @param message the message
     * @param args the message arguments
     * @return a static NotFound instance as described above
     */
    public static NotFound of(int errorCode, Throwable cause, String message, Object... args) {
        touchPayload().errorCode(errorCode).message(message, args).cause(cause);
        return _INSTANCE;
    }

}
