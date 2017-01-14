package org.osgl.mvc.result;

import static org.osgl.http.H.Status.CONFLICT;

/**
 * Indicate a 404 Not found response
 */
public class Conflict extends ErrorResult {

    /**
     * The static instance of Conflict result.
     */
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
        super(errorCode, CONFLICT);
    }

    public Conflict(int errorCode, String message, Object... args) {
        super(errorCode, CONFLICT, message, args);
    }

    public Conflict(int errorCode, Throwable cause, String message, Object... args) {
        super(errorCode, CONFLICT, cause, message, args);
    }

    public Conflict(int errorCode, Throwable cause) {
        super(errorCode, CONFLICT, cause);
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
        return _localizedErrorMsg() ? get(defaultMessage(CONFLICT)) : INSTANCE;
    }

    /**
     * Returns a static Conflict instance and set the {@link #payload} thread local
     * with default message.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param errorCode the app defined error code
     * @return a static Conflict instance as described above
     */
    public static Conflict get(int errorCode) {
        payload.get().errorCode(errorCode);
        return _localizedErrorMsg() ? get(defaultMessage(CONFLICT)) : INSTANCE;
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
    public static Conflict get(String message, Object... args) {
        payload.get().message(message, args);
        return _INSTANCE;
    }

    /**
     * Returns a static Conflict instance and set the {@link #payload} thread local
     * with message specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param errorCode the app defined error code
     * @param message the message
     * @param args the message arguments
     * @return a static Conflict instance as described above
     */
    public static Conflict get(int errorCode, String message, Object... args) {
        payload.get().errorCode(errorCode).message(message, args);
        return _INSTANCE;
    }
}
