package org.osgl.mvc.result;

import static org.osgl.http.H.Status.NOT_ACCEPTABLE;

/**
 * Indicate a 406 Not Acceptable
 */
public class NotAcceptable extends ErrorResult {

    /**
     * The static instance of NotFound result.
     */
    public static final NotAcceptable INSTANCE = new NotAcceptable();

    private static final NotAcceptable _INSTANCE = new NotAcceptable() {
        @Override
        public String getMessage() {
            return payload().message;
        }

        @Override
        public Integer errorCode() {
            return payload().errorCode;
        }
    };

    public NotAcceptable() {
        super(NOT_ACCEPTABLE);
    }

    public NotAcceptable(int errorCode) {
        super(NOT_ACCEPTABLE, errorCode);
    }

    public NotAcceptable(String message, Object... args) {
        super(NOT_ACCEPTABLE, message, args);
    }

    public NotAcceptable(int errorCode, String message, Object... args) {
        super(NOT_ACCEPTABLE, errorCode, message, args);
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
    public static NotAcceptable get() {
        return _localizedErrorMsg() ? of(defaultMessage(NOT_ACCEPTABLE)) : INSTANCE;
    }

    /**
     * Returns a static NotFound instance and set the {@link #payload} thread local
     * with default message.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param errorCode the app defined error code
     * @return a static NotFound instance as described above
     */
    public static NotAcceptable of(int errorCode) {
        payload.get().errorCode(errorCode);
        return _localizedErrorMsg() ? of(defaultMessage(NOT_ACCEPTABLE)) : INSTANCE;
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
    public static NotAcceptable of(String message, Object... args) {
        payload.get().message(message, args);
        return _INSTANCE;
    }

    /**
     * Returns a static NotFound instance and set the {@link #payload} thread local
     * with message specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param errorCode the app defined error code
     * @param message the message
     * @param args the message arguments
     * @return a static NotFound instance as described above
     */
    public static NotAcceptable of(int errorCode, String message, Object... args) {
        payload.get().errorCode(errorCode);
        payload.get().message(message, args);
        return _INSTANCE;
    }
}
