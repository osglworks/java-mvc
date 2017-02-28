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
        return _localizedErrorMsg() ? of(defaultMessage(CONFLICT)) : INSTANCE;
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
    public static Conflict of(int errorCode) {
        payload.get().errorCode(errorCode);
        return _localizedErrorMsg() ? of(defaultMessage(CONFLICT)) : INSTANCE;
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
     * @param cause the cause
     * @return a static Conflict instance as described above
     */
    public static Conflict of(Throwable cause) {
        payload.get().cause(cause);
        return _INSTANCE;
    }

    /**
     * Returns a static Conflict instance and set the {@link #payload} thread local
     * with message specified.
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
        payload.get().message(message, args).cause(cause);
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
    public static Conflict of(int errorCode, String message, Object... args) {
        payload.get().errorCode(errorCode).message(message, args);
        return _INSTANCE;
    }


    /**
     * Returns a static Conflict instance and set the {@link #payload} thread local
     * with message specified.
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
        payload.get().errorCode(errorCode).message(message, args).cause(cause);
        return _INSTANCE;
    }
}
