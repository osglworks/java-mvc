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

import static org.osgl.http.H.Status.EXPECTATION_FAILED;

/**
 * The server cannot meet the requirements of the Expect request-header field.
 */
public class ExpectationFailed extends ErrorResult {

    private static final ExpectationFailed _INSTANCE = new ExpectationFailed() {
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

    public ExpectationFailed() {
        super(EXPECTATION_FAILED);
    }

    public ExpectationFailed(int errorCode) {
        super(EXPECTATION_FAILED, errorCode);
    }

    public ExpectationFailed(String message, Object... args) {
        super(EXPECTATION_FAILED, message, args);
    }

    public ExpectationFailed(int errorCode, String message, Object... args) {
        super(EXPECTATION_FAILED, errorCode, message, args);
    }
    /**
     * Returns a static ExpectationFailed instance and set the {@link #payload} thread local
     * with default message.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @return a static ExpectationFailed instance as described above
     */
    public static ExpectationFailed get() {
        if (_localizedErrorMsg()) {
            return of(defaultMessage(EXPECTATION_FAILED));
        } else {
            touchPayload();
            return _INSTANCE;
        }
    }

    /**
     * Returns a static ExpectationFailed instance and set the {@link #payload} thread local
     * with message specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param message the message
     * @param args the message arguments
     * @return a static ExpectationFailed instance as described above
     */
    public static ExpectationFailed of(String message, Object... args) {
        touchPayload().message(message, args);
        return _INSTANCE;
    }

    /**
     * Returns a static ExpectationFailed instance and set the {@link #payload} thread local
     * with cause specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param cause the cause
     * @return a static ExpectationFailed instance as described above
     */
    public static ExpectationFailed of(Throwable cause) {
        if (_localizedErrorMsg()) {
            return of(cause, defaultMessage(EXPECTATION_FAILED));
        } else {
            touchPayload().cause(cause);
            return _INSTANCE;
        }
    }

    /**
     * Returns a static ExpectationFailed instance and set the {@link #payload} thread local
     * with cause and message specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param cause the cause
     * @param message the message
     * @param args the message arguments
     * @return a static ExpectationFailed instance as described above
     */
    public static ExpectationFailed of(Throwable cause, String message, Object... args) {
        touchPayload().message(message, args).cause(cause);
        return _INSTANCE;
    }

    /**
     * Returns a static ExpectationFailed instance and set the {@link #payload} thread local
     * with user error code
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param errorCode the app defined error code
     * @return a static ExpectationFailed instance as described above
     */
    public static ExpectationFailed of(int errorCode) {
        if (_localizedErrorMsg()) {
            return of(errorCode, defaultMessage(EXPECTATION_FAILED));
        } else {
            touchPayload().errorCode(errorCode);
            return _INSTANCE;
        }
    }

    /**
     * Returns a static ExpectationFailed instance and set the {@link #payload} thread local
     * with error code and message specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param errorCode the app defined error code
     * @param message the message
     * @param args the message arguments
     * @return a static ExpectationFailed instance as described above
     */
    public static ExpectationFailed of(int errorCode, String message, Object... args) {
        touchPayload().errorCode(errorCode).message(message, args);
        return _INSTANCE;
    }

    /**
     * Returns a static ExpectationFailed instance and set the {@link #payload} thread local
     * with cause specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param cause the cause
     * @param errorCode the app defined error code
     * @return a static ExpectationFailed instance as described above
     */
    public static ExpectationFailed of(int errorCode, Throwable cause) {
        if (_localizedErrorMsg()) {
            return of(errorCode, cause, defaultMessage(EXPECTATION_FAILED));
        } else {
            touchPayload().errorCode(errorCode).cause(cause);
            return _INSTANCE;
        }
    }

    /**
     * Returns a static ExpectationFailed instance and set the {@link #payload} thread local
     * with error code, cause and message specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param cause the cause
     * @param errorCode the app defined error code
     * @param message the message
     * @param args the message arguments
     * @return a static ExpectationFailed instance as described above
     */
    public static ExpectationFailed of(int errorCode, Throwable cause, String message, Object... args) {
        touchPayload().errorCode(errorCode).message(message, args).cause(cause);
        return _INSTANCE;
    }
}
