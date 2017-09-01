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

import static org.osgl.http.H.Status.SERVICE_UNAVAILABLE;

/**
 * The server is currently unavailable (because it is overloaded or down for maintenance). Generally, this
 * is a temporary state.
 */
public class ServiceUnavailable extends ErrorResult {

    private static final ServiceUnavailable _INSTANCE = new ServiceUnavailable() {
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


    public ServiceUnavailable() {
        super(SERVICE_UNAVAILABLE);
    }
    public ServiceUnavailable(String message, Object ... args) {
        super(SERVICE_UNAVAILABLE, message, args);
    }
    public ServiceUnavailable(Throwable cause, String message, Object ... args) {
        super(SERVICE_UNAVAILABLE, cause, message, args);
    }
    public ServiceUnavailable(Throwable cause) {
        super(SERVICE_UNAVAILABLE, cause);
    }

    public ServiceUnavailable(int errorCode) {
        super(SERVICE_UNAVAILABLE, errorCode);
    }
    public ServiceUnavailable(int errorCode, String message, Object ... args) {
        super(SERVICE_UNAVAILABLE, errorCode, message, args);
    }
    public ServiceUnavailable(int errorCode, Throwable cause, String message, Object ... args) {
        super(SERVICE_UNAVAILABLE, errorCode, cause, message, args);
    }
    public ServiceUnavailable(int errorCode, Throwable cause) {
        super(SERVICE_UNAVAILABLE, errorCode, cause);
    }

    /**
     * Returns a static ServiceUnavailable instance and set the {@link #payload} thread local
     * with default message.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @return a static ServiceUnavailable instance as described above
     */
    public static ServiceUnavailable get() {
        if (_localizedErrorMsg()) {
            return of(defaultMessage(SERVICE_UNAVAILABLE));
        } else {
            touchPayload();
            return _INSTANCE;
        }
    }

    /**
     * Returns a static ServiceUnavailable instance and set the {@link #payload} thread local
     * with message specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param message the message
     * @param args the message arguments
     * @return a static ServiceUnavailable instance as described above
     */
    public static ServiceUnavailable of(String message, Object... args) {
        touchPayload().message(message, args);
        return _INSTANCE;
    }

    /**
     * Returns a static ServiceUnavailable instance and set the {@link #payload} thread local
     * with cause specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param cause the cause
     * @return a static ServiceUnavailable instance as described above
     */
    public static ServiceUnavailable of(Throwable cause) {
        if (_localizedErrorMsg()) {
            return of(cause, defaultMessage(SERVICE_UNAVAILABLE));
        } else {
            touchPayload().cause(cause);
            return _INSTANCE;
        }
    }

    /**
     * Returns a static ServiceUnavailable instance and set the {@link #payload} thread local
     * with cause and message specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param cause the cause
     * @param message the message
     * @param args the message arguments
     * @return a static ServiceUnavailable instance as described above
     */
    public static ServiceUnavailable of(Throwable cause, String message, Object... args) {
        touchPayload().message(message, args).cause(cause);
        return _INSTANCE;
    }

    /**
     * Returns a static ServiceUnavailable instance and set the {@link #payload} thread local
     * with error code and default message.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param errorCode the app defined error code
     * @return a static ServiceUnavailable instance as described above
     */
    public static ServiceUnavailable of(int errorCode) {
        if (_localizedErrorMsg()) {
            return of(errorCode, defaultMessage(SERVICE_UNAVAILABLE));
        } else {
            touchPayload().errorCode(errorCode);
            return _INSTANCE;
        }
    }

    /**
     * Returns a static ServiceUnavailable instance and set the {@link #payload} thread local
     * with error code and message specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param errorCode the app defined error code
     * @param message the message
     * @param args the message arguments
     * @return a static ServiceUnavailable instance as described above
     */
    public static ServiceUnavailable of(int errorCode, String message, Object... args) {
        touchPayload().errorCode(errorCode).message(message, args);
        return _INSTANCE;
    }


    /**
     * Returns a static ServiceUnavailable instance and set the {@link #payload} thread local
     * with error code and cause specified
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param cause  the cause
     * @param errorCode the app defined error code
     * @return a static ServiceUnavailable instance as described above
     */
    public static ServiceUnavailable of(int errorCode, Throwable cause) {
        if (_localizedErrorMsg()) {
            return of(errorCode, cause, defaultMessage(SERVICE_UNAVAILABLE));
        } else {
            touchPayload().errorCode(errorCode).cause(cause);
            return _INSTANCE;
        }
    }


    /**
     * Returns a static ServiceUnavailable instance and set the {@link #payload} thread local
     * with error code, cause and message specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param cause  the cause
     * @param errorCode the app defined error code
     * @param message the message
     * @param args the message arguments
     * @return a static ServiceUnavailable instance as described above
     */
    public static ServiceUnavailable of(int errorCode, Throwable cause, String message, Object... args) {
        touchPayload().errorCode(errorCode).message(message, args).cause(cause);
        return _INSTANCE;
    }

}
