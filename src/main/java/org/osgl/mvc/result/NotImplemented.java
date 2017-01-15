package org.osgl.mvc.result;

import static org.osgl.http.H.Status.NOT_IMPLEMENTED;

/**
 * Indicate a 501 Not implemented response
 */
public class NotImplemented extends ErrorResult {

    /**
     * The static instance of NotImplemented result.
     */
    public static final NotImplemented INSTANCE = new NotImplemented();

    private static final NotImplemented _INSTANCE = new NotImplemented() {
        @Override
        public String getMessage() {
            return payload().message;
        }

        @Override
        public Integer errorCode() {
            return payload().errorCode;
        }
    };

    public NotImplemented() {
        super(NOT_IMPLEMENTED);
    }

    public NotImplemented(int errorCode) {
        super(NOT_IMPLEMENTED, errorCode);
    }

    public NotImplemented(String message, Object... args) {
        super(NOT_IMPLEMENTED, message, args);
    }

    public NotImplemented(int errorCode, String message, Object... args) {
        super(NOT_IMPLEMENTED, errorCode, message, args);
    }

    public NotImplemented(Throwable cause, String message, Object... args) {
        super(NOT_IMPLEMENTED, cause, message, args);
    }

    public NotImplemented(int errorCode, Throwable cause, String message, Object... args) {
        super(NOT_IMPLEMENTED, errorCode, cause, message, args);
    }

    public NotImplemented(Throwable cause) {
        super(NOT_IMPLEMENTED, cause);
    }

    public NotImplemented(int errorCode, Throwable cause) {
        super(NOT_IMPLEMENTED, errorCode, cause);
    }

    /**
     * Returns a static NotImplemented instance and set the {@link #payload} thread local
     * with default message.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @return a static NotImplemented instance as described above
     */
    public static NotImplemented get() {
        return _localizedErrorMsg() ? of(defaultMessage(NOT_IMPLEMENTED)) : INSTANCE;
    }

    /**
     * Returns a static NotImplemented instance and set the {@link #payload} thread local
     * with default message.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param errorCode the app defined error code
     * @return a static NotImplemented instance as described above
     */
    public static NotImplemented of(int errorCode) {
        return _localizedErrorMsg() ? of(defaultMessage(NOT_IMPLEMENTED)) : INSTANCE;
    }

    /**
     * Returns a static NotImplemented instance and set the {@link #payload} thread local
     * with message specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param message the message
     * @param args the message arguments
     * @return a static NotImplemented instance as described above
     */
    public static NotImplemented of(String message, Object... args) {
        payload.get().message(message, args);
        return _INSTANCE;
    }
}
