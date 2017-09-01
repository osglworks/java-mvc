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

import static org.osgl.http.H.Status.FORBIDDEN;

/**
 * HTTP 403 Forbidden
 */
public class Forbidden extends ErrorResult {

    /**
     * The static instance of Forbidden result.
     */
    public static final Forbidden INSTANCE = new Forbidden();

    private static final Forbidden _INSTANCE = new Forbidden() {
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

    public Forbidden() {
        super(FORBIDDEN);
    }

    public Forbidden(int errorCode) {
        super(FORBIDDEN,  errorCode);
    }

    public Forbidden(String message, Object... args) {
        super(FORBIDDEN, message, args);
    }

    public Forbidden(int errorCode, String message, Object... args) {
        super(FORBIDDEN, errorCode, message, args);
    }

    public Forbidden(Throwable cause, String message, Object... args) {
        super(FORBIDDEN, cause, message, args);
    }

    public Forbidden(int errorCode, Throwable cause, String message, Object... args) {
        super(FORBIDDEN, errorCode, cause, message, args);
    }

    public Forbidden(Throwable cause) {
        super(FORBIDDEN, cause);
    }

    public Forbidden(int errorCode,  Throwable cause) {
        super(FORBIDDEN, errorCode, cause);
    }

    /**
     * Returns a static Forbidden instance and set the {@link #payload} thread local
     * with default message.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @return a static Forbidden instance as described above
     */
    public static Forbidden get() {
        return _localizedErrorMsg() ? of(defaultMessage(FORBIDDEN)) : INSTANCE;
    }

    /**
     * Returns a static Forbidden instance and set the {@link #payload} thread local
     * with message specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param message the message
     * @param args the message arguments
     * @return a static Forbidden instance as described above
     */
    public static Forbidden of(String message, Object... args) {
        touchPayload().message(message, args);
        return _INSTANCE;
    }

    /**
     * Returns a static Forbidden instance and set the {@link #payload} thread local
     * with cause specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param cause the cause
     * @return a static Forbidden instance as described above
     */
    public static Forbidden of(Throwable cause) {
        if (_localizedErrorMsg()) {
            return of(cause, defaultMessage(FORBIDDEN));
        } else {
            touchPayload().cause(cause);
            return _INSTANCE;
        }
    }

    /**
     * Returns a static Forbidden instance and set the {@link #payload} thread local
     * with cause and message specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param cause the cause
     * @param message the message
     * @param args the message arguments
     * @return a static Forbidden instance as described above
     */
    public static Forbidden of(Throwable cause, String message, Object... args) {
        touchPayload().message(message, args).cause(cause);
        return _INSTANCE;
    }

    /**
     * Returns a static Forbidden instance and set the {@link #payload} thread local
     * with error code and default message.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param errorCode the app defined error code
     * @return a static Forbidden instance as described above
     */
    public static Forbidden of(int errorCode) {
        touchPayload().errorCode(errorCode);
        return _localizedErrorMsg() ? of(defaultMessage(FORBIDDEN)) : INSTANCE;
    }

    /**
     * Returns a static Forbidden instance and set the {@link #payload} thread local
     * with error code and message specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param errorCode the app defined error code
     * @param message the message
     * @param args the message arguments
     * @return a static Forbidden instance as described above
     */
    public static Forbidden of(int errorCode, String message, Object... args) {
        touchPayload().errorCode(errorCode).message(message, args);
        return _INSTANCE;
    }

    /**
     * Returns a static Forbidden instance and set the {@link #payload} thread local
     * with error code and cause specified
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param errorCode the app defined error code
     * @return a static Forbidden instance as described above
     */
    public static Forbidden of(int errorCode, Throwable cause) {
        touchPayload().errorCode(errorCode).cause(cause);
        return _INSTANCE;
    }

    /**
     * Returns a static Forbidden instance and set the {@link #payload} thread local
     * with error code, cause and message specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param errorCode the app defined error code
     * @param message the message
     * @param args the message arguments
     * @return a static Forbidden instance as described above
     */
    public static Forbidden of(int errorCode, Throwable cause, String message, Object... args) {
        touchPayload().errorCode(errorCode).cause(cause).message(message, args);
        return _INSTANCE;
    }

    /**
     * Returns a static Forbidden instance and set the {@link #payload} thread local
     * with default message.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * This method is deprecated. Please use {@link #of(int, Throwable)} instead
     *
     * @param cause the cause
     * @param errorCode the app defined error code
     * @return a static Forbidden instance as described above
     */
    @Deprecated
    public static Forbidden of(Throwable cause, int errorCode) {
        if (_localizedErrorMsg()) {
            return of(errorCode, cause, defaultMessage(FORBIDDEN));
        } else {
            touchPayload().errorCode(errorCode).cause(cause);
            return _INSTANCE;
        }
    }


    /**
     * Returns a static Forbidden instance and set the {@link #payload} thread local
     * with message specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * This is deprecated. Please use {@link #of(int, Throwable, String, Object...)} instead
     *
     * @param cause the cause
     * @param errorCode the app defined error code
     * @param message the message
     * @param args the message arguments
     * @return a static Forbidden instance as described above
     */
    @Deprecated
    public static Forbidden of(Throwable cause, int errorCode, String message, Object... args) {
        touchPayload().errorCode(errorCode).message(message, args).cause(cause);
        return _INSTANCE;
    }
}
