package org.osgl.mvc.result;

import static org.osgl.http.H.Status.NOT_FOUND;

/**
 * Indicate a 404 Not found response
 */
public class NotFound extends ErrorResult {

    /**
     * The static instance of NotFound result.
     */
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
        return _localizedErrorMsg() ? of(defaultMessage(NOT_FOUND)) : INSTANCE;
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
     * with message specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param cause the cause
     * @return a static NotFound instance as described above
     */
    public static NotFound of(Throwable cause) {
        touchPayload().cause(cause);
        return _INSTANCE;
    }

    /**
     * Returns a static NotFound instance and set the {@link #payload} thread local
     * with message specified.
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

}
