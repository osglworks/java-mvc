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

import static org.osgl.http.H.Status.CONFLICT;

/**
 * Indicates that the request could not be processed because of conflict in the request, such as an edit
 * conflict between multiple simultaneous updates.
 */
public class Conflict extends ErrorResult {

    /**
     * The static instance of Conflict result.
     *
     * This is deprecated. Please use {@link #get()} instead
     */
    @Deprecated
    public static final Conflict INSTANCE = new Conflict();

    private static final Conflict _INSTANCE = new Conflict() {
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

    public Conflict() {
        super(CONFLICT);
    }

    public Conflict(String message, Object... args) {
        super(CONFLICT, message, args);
    }

    public Conflict(Throwable cause, String message, Object... args) {
        super(CONFLICT, cause, message, args);
    }

    public Conflict(Throwable cause) {
        super(CONFLICT, cause);
    }

    public Conflict(int errorCode) {
        super(CONFLICT, errorCode);
    }

    public Conflict(int errorCode, String message, Object... args) {
        super(CONFLICT, errorCode, message, args);
    }

    public Conflict(int errorCode, Throwable cause, String message, Object... args) {
        super(CONFLICT, errorCode, cause, message, args);
    }

    public Conflict(int errorCode, Throwable cause) {
        super(CONFLICT, errorCode, cause);
    }

    /**
     * Returns a static Conflict instance and set the {@link #payload} thread local
     * with default message.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @return a static Conflict instance as described above
     */
    public static Conflict get() {
        if (_localizedErrorMsg()) {
            return of(defaultMessage(CONFLICT));
        } else {
            touchPayload();
            return _INSTANCE;
        }
    }

    /**
     * Returns a static Conflict instance and set the {@link #payload} thread local
     * with message specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param message the message
     * @param args the message arguments
     * @return a static Conflict instance as described above
     */
    public static Conflict of(String message, Object... args) {
        touchPayload().message(message, args);
        return _INSTANCE;
    }

    /**
     * Returns a static Conflict instance and set the {@link #payload} thread local
     * with cause specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param cause the cause
     * @return a static Conflict instance as described above
     */
    public static Conflict of(Throwable cause) {
        if (_localizedErrorMsg()) {
            return of(cause, defaultMessage(CONFLICT));
        } else {
            touchPayload().cause(cause);
            return _INSTANCE;
        }
    }

    /**
     * Returns a static Conflict instance and set the {@link #payload} thread local
     * with cause and message specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param cause the cause
     * @param message the message
     * @param args the message arguments
     * @return a static Conflict instance as described above
     */
    public static Conflict of(Throwable cause, String message, Object... args) {
        touchPayload().message(message, args).cause(cause);
        return _INSTANCE;
    }

    /**
     * Returns a static Conflict instance and set the {@link #payload} thread local
     * with error code and default message.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param errorCode the app defined error code
     * @return a static Conflict instance as described above
     */
    public static Conflict of(int errorCode) {
        if (_localizedErrorMsg()) {
            return of(errorCode, defaultMessage(CONFLICT));
        } else {
            touchPayload().errorCode(errorCode);
            return _INSTANCE;
        }
    }

    /**
     * Returns a static Conflict instance and set the {@link #payload} thread local
     * with error code and message specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param errorCode the app defined error code
     * @param message the message
     * @param args the message arguments
     * @return a static Conflict instance as described above
     */
    public static Conflict of(int errorCode, String message, Object... args) {
        touchPayload().errorCode(errorCode).message(message, args);
        return _INSTANCE;
    }


    /**
     * Returns a static Conflict instance and set the {@link #payload} thread local
     * with error code and cause specified
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param cause  the cause
     * @param errorCode the app defined error code
     * @return a static Conflict instance as described above
     */
    public static Conflict of(int errorCode, Throwable cause) {
        if (_localizedErrorMsg()) {
            return of(errorCode, cause, defaultMessage(CONFLICT));
        } else {
            touchPayload().errorCode(errorCode).cause(cause);
            return _INSTANCE;
        }
    }


    /**
     * Returns a static Conflict instance and set the {@link #payload} thread local
     * with error code, cause and message specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param cause  the cause
     * @param errorCode the app defined error code
     * @param message the message
     * @param args the message arguments
     * @return a static Conflict instance as described above
     */
    public static Conflict of(int errorCode, Throwable cause, String message, Object... args) {
        touchPayload().errorCode(errorCode).message(message, args).cause(cause);
        return _INSTANCE;
    }
}
