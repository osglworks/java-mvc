package org.osgl.mvc.result;

import static org.osgl.http.H.Status.INTERNAL_SERVER_ERROR;

/**
 * Indicate an internal server error and set 500 status on response
 */
public class InternalServerError extends ErrorResult {

    private static final InternalServerError _INSTANCE = new InternalServerError() {
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

    public InternalServerError() {
        super(INTERNAL_SERVER_ERROR);
    }
    public InternalServerError(String message, Object... args) {
        super(INTERNAL_SERVER_ERROR, message, args);
    }
    public InternalServerError(Throwable t) {
        super(INTERNAL_SERVER_ERROR, t);
    }
    public InternalServerError(Throwable t, String message, Object... args) {
        super(INTERNAL_SERVER_ERROR, t, message, args);
    }

    public InternalServerError(int errorCode) {
        super(INTERNAL_SERVER_ERROR, errorCode);
    }
    public InternalServerError(int errorCode, String message, Object... args) {
        super(INTERNAL_SERVER_ERROR, errorCode, message, args);
    }
    public InternalServerError(int errorCode, Throwable cause) {
        super(INTERNAL_SERVER_ERROR, errorCode, cause);
    }
    public InternalServerError(int errorCode, Throwable cause, String message, Object... args) {
        super(INTERNAL_SERVER_ERROR, errorCode, cause, message, args);
    }

    /**
     * Returns a static InternalServerError instance and set the {@link #payload} thread local
     * with default message.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @return a static InternalServerError instance as described above
     */
    public static InternalServerError get() {
        if (_localizedErrorMsg()) {
            return of(defaultMessage(INTERNAL_SERVER_ERROR));
        } else {
            touchPayload();
            return _INSTANCE;
        }
    }

    /**
     * Returns a static InternalServerError instance and set the {@link #payload} thread local
     * with message specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param message the message
     * @param args the message arguments
     * @return a static InternalServerError instance as described above
     */
    public static InternalServerError of(String message, Object... args) {
        touchPayload().message(message, args);
        return _INSTANCE;
    }

    /**
     * Returns a static InternalServerError instance and set the {@link #payload} thread local
     * with cause specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param cause the cause
     * @return a static InternalServerError instance as described above
     */
    public static InternalServerError of(Throwable cause) {
        if (_localizedErrorMsg()) {
            return of(cause, defaultMessage(INTERNAL_SERVER_ERROR));
        } else {
            touchPayload().cause(cause);
            return _INSTANCE;
        }
    }

    /**
     * Returns a static InternalServerError instance and set the {@link #payload} thread local
     * with cause and message specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param cause the cause
     * @param message the message
     * @param args the message arguments
     * @return a static InternalServerError instance as described above
     */
    public static InternalServerError of(Throwable cause, String message, Object... args) {
        touchPayload().message(message, args).cause(cause);
        return _INSTANCE;
    }

    /**
     * Returns a static InternalServerError instance and set the {@link #payload} thread local
     * with error code and default message.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param errorCode the app defined error code
     * @return a static InternalServerError instance as described above
     */
    public static InternalServerError of(int errorCode) {
        if (_localizedErrorMsg()) {
            return of(errorCode, defaultMessage(INTERNAL_SERVER_ERROR));
        } else {
            touchPayload().errorCode(errorCode);
            return _INSTANCE;
        }
    }

    /**
     * Returns a static InternalServerError instance and set the {@link #payload} thread local
     * with error code and message specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param errorCode the app defined error code
     * @param message the message
     * @param args the message arguments
     * @return a static InternalServerError instance as described above
     */
    public static InternalServerError of(int errorCode, String message, Object... args) {
        touchPayload().errorCode(errorCode).message(message, args);
        return _INSTANCE;
    }


    /**
     * Returns a static InternalServerError instance and set the {@link #payload} thread local
     * with error code and cause specified
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param cause  the cause
     * @param errorCode the app defined error code
     * @return a static InternalServerError instance as described above
     */
    public static InternalServerError of(int errorCode, Throwable cause) {
        if (_localizedErrorMsg()) {
            return of(errorCode, cause, defaultMessage(INTERNAL_SERVER_ERROR));
        } else {
            touchPayload().errorCode(errorCode).cause(cause);
            return _INSTANCE;
        }
    }


    /**
     * Returns a static InternalServerError instance and set the {@link #payload} thread local
     * with error code, cause and message specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param cause  the cause
     * @param errorCode the app defined error code
     * @param message the message
     * @param args the message arguments
     * @return a static InternalServerError instance as described above
     */
    public static InternalServerError of(int errorCode, Throwable cause, String message, Object... args) {
        touchPayload().errorCode(errorCode).message(message, args).cause(cause);
        return _INSTANCE;
    }
}
