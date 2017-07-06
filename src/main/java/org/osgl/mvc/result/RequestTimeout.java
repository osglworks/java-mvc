package org.osgl.mvc.result;

import static org.osgl.http.H.Status.BAD_REQUEST;
import static org.osgl.http.H.Status.REQUEST_TIMEOUT;

/**
 * The server timed out waiting for the request. According to HTTP specifications: "The client
 * did not produce a request within the time that the server was prepared to wait. The client MAY
 * repeat the request without modifications at any later time."
 */
public class RequestTimeout extends ErrorResult {

    /**
     * The static instance of RequestTimeout result.
     */
    public static final RequestTimeout INSTANCE = new RequestTimeout();

    private static final RequestTimeout _INSTANCE = new RequestTimeout() {
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


    public RequestTimeout() {
        super(REQUEST_TIMEOUT);
    }
    public RequestTimeout(String message, Object ... args) {
        super(REQUEST_TIMEOUT, message, args);
    }
    public RequestTimeout(Throwable cause, String message, Object ... args) {
        super(REQUEST_TIMEOUT, cause, message, args);
    }
    public RequestTimeout(Throwable cause) {
        super(REQUEST_TIMEOUT, cause);
    }

    public RequestTimeout(int errorCode) {
        super(REQUEST_TIMEOUT, errorCode);
    }
    public RequestTimeout(int errorCode, String message, Object ... args) {
        super(REQUEST_TIMEOUT, errorCode, message, args);
    }
    public RequestTimeout(int errorCode, Throwable cause, String message, Object ... args) {
        super(REQUEST_TIMEOUT, errorCode, cause, message, args);
    }
    public RequestTimeout(int errorCode, Throwable cause) {
        super(REQUEST_TIMEOUT, errorCode, cause);
    }

    /**
     * Returns a static RequestTimeout instance and set the {@link #payload} thread local
     * with default message.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @return a static RequestTimeout instance as described above
     */
    public static RequestTimeout get() {
        return _localizedErrorMsg() ? of(defaultMessage(BAD_REQUEST)) : INSTANCE;
    }

    /**
     * Returns a static RequestTimeout instance and set the {@link #payload} thread local
     * with default message.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param errorCode the app defined error code
     * @return a static RequestTimeout instance as described above
     */
    public static RequestTimeout of(int errorCode) {
        touchPayload().errorCode(errorCode);
        return _localizedErrorMsg() ? of(defaultMessage(BAD_REQUEST)) : INSTANCE;
    }

    /**
     * Returns a static RequestTimeout instance and set the {@link #payload} thread local
     * with message specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param message the message
     * @param args the message arguments
     * @return a static RequestTimeout instance as described above
     */
    public static RequestTimeout of(String message, Object... args) {
        touchPayload().message(message, args);
        return _INSTANCE;
    }

    /**
     * Returns a static RequestTimeout instance and set the {@link #payload} thread local
     * with message specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param errorCode app defined error code
     * @param message the message
     * @param args the message arguments
     * @return a static RequestTimeout instance as described above
     */
    public static RequestTimeout of(int errorCode, String message, Object... args) {
        touchPayload().message(message, args).errorCode(errorCode);
        return _INSTANCE;
    }

    /**
     * Returns a static RequestTimeout instance and set the {@link #payload} thread local
     * with message specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param cause  the cause
     * @param errorCode app defined error code
     * @param message the message
     * @param args the message arguments
     * @return a static RequestTimeout instance as described above
     */
    public static RequestTimeout of(int errorCode, Throwable cause, String message, Object... args) {
        touchPayload().message(message, args).errorCode(errorCode).cause(cause);
        return _INSTANCE;
    }

}
