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

import static org.osgl.http.H.Status.INTERNAL_SERVER_ERROR;

/**
 * Indicate an internal server error and set 500 status on response
 *
 * This class is deprecated. Please use {@link InternalServerError} instead
 */
@Deprecated
public class ServerError extends ErrorResult {

    private static final ServerError _INSTANCE = new ServerError() {
        @Override
        public String getMessage() {
            return payload().message;
        }

        @Override
        public synchronized Throwable getCause() {
            return payload().cause;
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

    public ServerError() {
        super(INTERNAL_SERVER_ERROR);
    }
    public ServerError(String message, Object... args) {
        super(INTERNAL_SERVER_ERROR, message, args);
    }
    public ServerError(Throwable t) {
        super(INTERNAL_SERVER_ERROR, t);
    }
    public ServerError(Throwable t, String message, Object... args) {
        super(INTERNAL_SERVER_ERROR, t, message, args);
    }

    public ServerError(int errorCode) {
        super(INTERNAL_SERVER_ERROR, errorCode);
    }
    public ServerError(int errorCode, String message, Object... args) {
        super(INTERNAL_SERVER_ERROR, errorCode, message, args);
    }
    public ServerError(int errorCode, Throwable cause) {
        super(INTERNAL_SERVER_ERROR, errorCode, cause);
    }
    public ServerError(int errorCode, Throwable cause, String message, Object... args) {
        super(INTERNAL_SERVER_ERROR, errorCode, cause, message, args);
    }

    /**
     * Returns a static ServerError instance and set the {@link #payload} thread local
     * with message specified, and store the cause specified into the {@link #payload}
     * thread local
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local; When calling the instance on
     * {@link #getCause()} method, it will return whatever stored in the {@link #payload}
     * thread local
     *
     * @param cause the cause
     * @param message the message
     * @param args the message arguments
     * @return a static ServerError instance as described above
     */
    public static ServerError of(Throwable cause, String message, Object... args) {
        touchPayload().cause(cause).message(message, args);
        return _INSTANCE;
    }

    /**
     * Returns a static ServerError instance and set the {@link #payload} thread local
     * with message specified, and store the cause specified into the {@link #payload}
     * thread local
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local; When calling the instance on
     * {@link #getCause()} method, it will return whatever stored in the {@link #payload}
     * thread local
     *
     * @param errorCode the app defined error code
     * @param cause the cause
     * @param message the message
     * @param args the message arguments
     * @return a static ServerError instance as described above
     */
    public static ServerError of(int errorCode, Throwable cause, String message, Object... args) {
        touchPayload().errorCode(errorCode).cause(cause).message(message, args);
        return _INSTANCE;
    }

    /**
     * Returns a static ServerError instance and set the {@link #payload} thread local
     * with message specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param message the message
     * @param args the message arguments
     * @return a static ServerError instance as described above
     */
    public static ServerError of(String message, Object... args) {
        touchPayload().message(message, args);
        return _INSTANCE;
    }

    /**
     * Returns a static ServerError instance and set the {@link #payload} thread local
     * with message specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param errorCode the app defined error code
     * @param message the message
     * @param args the message arguments
     * @return a static ServerError instance as described above
     */
    public static ServerError of(int errorCode, String message, Object... args) {
        touchPayload().errorCode(errorCode).message(message, args);
        return _INSTANCE;
    }

}
