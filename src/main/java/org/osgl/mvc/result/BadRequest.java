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

import static org.osgl.http.H.Status.BAD_REQUEST;

/**
 * Indicate a client error
 */
public class BadRequest extends ErrorResult {

    /**
     * The static instance of BadRequest result.
     *
     * This is deprecated. Please use {@link #get()} instead
     */
    @Deprecated
    public static final BadRequest INSTANCE = new BadRequest();

    private static final BadRequest _INSTANCE = new BadRequest() {
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


    public BadRequest() {
        super(BAD_REQUEST);
    }
    public BadRequest(String message, Object ... args) {
        super(BAD_REQUEST, message, args);
    }
    public BadRequest(Throwable cause, String message, Object ... args) {
        super(BAD_REQUEST, cause, message, args);
    }
    public BadRequest(Throwable cause) {
        super(BAD_REQUEST, cause);
    }

    public BadRequest(int errorCode) {
        super(BAD_REQUEST, errorCode);
    }
    public BadRequest(int errorCode, String message, Object ... args) {
        super(BAD_REQUEST, errorCode, message, args);
    }
    public BadRequest(int errorCode, Throwable cause, String message, Object ... args) {
        super(BAD_REQUEST, errorCode, cause, message, args);
    }
    public BadRequest(int errorCode, Throwable cause) {
        super(BAD_REQUEST, errorCode, cause);
    }

    /**
     * Returns a static BadRequest instance and set the {@link #payload} thread local
     * with default message.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @return a static BadRequest instance as described above
     */
    public static BadRequest get() {
        if (_localizedErrorMsg()) {
            return of(defaultMessage(BAD_REQUEST));
        } else {
            touchPayload();
            return _INSTANCE;
        }
    }

    /**
     * Returns a static BadRequest instance and set the {@link #payload} thread local
     * with message specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param message the message
     * @param args the message arguments
     * @return a static BadRequest instance as described above
     */
    public static BadRequest of(String message, Object... args) {
        touchPayload().message(message, args);
        return _INSTANCE;
    }

    /**
     * Returns a static BadRequest instance and set the {@link #payload} thread local
     * with cause specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param cause the cause
     * @return a static BadRequest instance as described above
     */
    public static BadRequest of(Throwable cause) {
        if (_localizedErrorMsg()) {
            return of(cause, defaultMessage(BAD_REQUEST));
        } else {
            touchPayload().cause(cause);
            return _INSTANCE;
        }
    }

    /**
     * Returns a static BadRequest instance and set the {@link #payload} thread local
     * with cause and message specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param cause  the cause
     * @param message the message
     * @param args the message arguments
     * @return a static BadRequest instance as described above
     */
    public static BadRequest of(Throwable cause, String message, Object... args) {
        touchPayload().message(message, args).cause(cause);
        return _INSTANCE;
    }

    /**
     * Returns a static BadRequest instance and set the {@link #payload} thread local
     * with error code and default message.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param errorCode the app defined error code
     * @return a static BadRequest instance as described above
     */
    public static BadRequest of(int errorCode) {
        if (_localizedErrorMsg()) {
            return of(errorCode, defaultMessage(BAD_REQUEST));
        } else {
            touchPayload().errorCode(errorCode);
            return _INSTANCE;
        }
    }

    /**
     * Returns a static BadRequest instance and set the {@link #payload} thread local
     * with error code and message specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param errorCode app defined error code
     * @param message the message
     * @param args the message arguments
     * @return a static BadRequest instance as described above
     */
    public static BadRequest of(int errorCode, String message, Object... args) {
        touchPayload().message(message, args).errorCode(errorCode);
        return _INSTANCE;
    }

    /**
     * Returns a static BadRequest instance and set the {@link #payload} thread local
     * with error code and cause specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param cause  the cause
     * @param errorCode app defined error code
     * @return a static BadRequest instance as described above
     */
    public static BadRequest of(int errorCode, Throwable cause) {
        if (_localizedErrorMsg()) {
            return of(errorCode, cause, defaultMessage(BAD_REQUEST));
        } else {
            touchPayload().errorCode(errorCode).cause(cause);
            return _INSTANCE;
        }
    }

    /**
     * Returns a static BadRequest instance and set the {@link #payload} thread local
     * with error code, cause and message specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param cause  the cause
     * @param errorCode app defined error code
     * @param message the message
     * @param args the message arguments
     * @return a static BadRequest instance as described above
     */
    public static BadRequest of(int errorCode, Throwable cause, String message, Object... args) {
        touchPayload().message(message, args).errorCode(errorCode).cause(cause);
        return _INSTANCE;
    }

}
