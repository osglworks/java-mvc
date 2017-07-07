package org.osgl.mvc.result;

import static org.osgl.http.H.Status.UNAVAILABLE_FOR_LEGAL_REASONS;

/**
 * A server operator has received a legal demand to deny access to a resource or to a set of resources that
 * includes the requested resource. The code 451 was chosen as a reference to the novel Fahrenheit 451.
 */
public class UnavailableForLegalReasons extends ErrorResult {

    private static final UnavailableForLegalReasons _INSTANCE = new UnavailableForLegalReasons() {
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

    public UnavailableForLegalReasons() {
        super(UNAVAILABLE_FOR_LEGAL_REASONS);
    }

    public UnavailableForLegalReasons(String message, Object... args) {
        super(UNAVAILABLE_FOR_LEGAL_REASONS, message, args);
    }

    public UnavailableForLegalReasons(Throwable cause, String message, Object... args) {
        super(UNAVAILABLE_FOR_LEGAL_REASONS, cause, message, args);
    }

    public UnavailableForLegalReasons(Throwable cause) {
        super(UNAVAILABLE_FOR_LEGAL_REASONS, cause);
    }

    public UnavailableForLegalReasons(int errorCode) {
        super(UNAVAILABLE_FOR_LEGAL_REASONS, errorCode);
    }

    public UnavailableForLegalReasons(int errorCode, String message, Object... args) {
        super(UNAVAILABLE_FOR_LEGAL_REASONS, errorCode, message, args);
    }

    public UnavailableForLegalReasons(int errorCode, Throwable cause, String message, Object... args) {
        super(UNAVAILABLE_FOR_LEGAL_REASONS, errorCode, cause, message, args);
    }

    public UnavailableForLegalReasons(int errorCode, Throwable cause) {
        super(UNAVAILABLE_FOR_LEGAL_REASONS, errorCode, cause);
    }

    /**
     * Returns a static UnavailableForLegalReasons instance and set the {@link #payload} thread local
     * with default message.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @return a static UnavailableForLegalReasons instance as described above
     */
    public static UnavailableForLegalReasons get() {
        if (_localizedErrorMsg()) {
            return of(defaultMessage(UNAVAILABLE_FOR_LEGAL_REASONS));
        } else {
            touchPayload();
            return _INSTANCE;
        }
    }

    /**
     * Returns a static UnavailableForLegalReasons instance and set the {@link #payload} thread local
     * with message specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param message the message
     * @param args the message arguments
     * @return a static UnavailableForLegalReasons instance as described above
     */
    public static UnavailableForLegalReasons of(String message, Object... args) {
        touchPayload().message(message, args);
        return _INSTANCE;
    }

    /**
     * Returns a static UnavailableForLegalReasons instance and set the {@link #payload} thread local
     * with cause specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param cause the cause
     * @return a static UnavailableForLegalReasons instance as described above
     */
    public static UnavailableForLegalReasons of(Throwable cause) {
        if (_localizedErrorMsg()) {
            return of(cause, defaultMessage(UNAVAILABLE_FOR_LEGAL_REASONS));
        } else {
            touchPayload().cause(cause);
            return _INSTANCE;
        }
    }

    /**
     * Returns a static UnavailableForLegalReasons instance and set the {@link #payload} thread local
     * with cause and message specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param cause the cause
     * @param message the message
     * @param args the message arguments
     * @return a static UnavailableForLegalReasons instance as described above
     */
    public static UnavailableForLegalReasons of(Throwable cause, String message, Object... args) {
        touchPayload().message(message, args).cause(cause);
        return _INSTANCE;
    }

    /**
     * Returns a static UnavailableForLegalReasons instance and set the {@link #payload} thread local
     * with error code and default message.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param errorCode the app defined error code
     * @return a static UnavailableForLegalReasons instance as described above
     */
    public static UnavailableForLegalReasons of(int errorCode) {
        if (_localizedErrorMsg()) {
            return of(errorCode, defaultMessage(UNAVAILABLE_FOR_LEGAL_REASONS));
        } else {
            touchPayload().errorCode(errorCode);
            return _INSTANCE;
        }
    }

    /**
     * Returns a static UnavailableForLegalReasons instance and set the {@link #payload} thread local
     * with error code and message specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param errorCode the app defined error code
     * @param message the message
     * @param args the message arguments
     * @return a static UnavailableForLegalReasons instance as described above
     */
    public static UnavailableForLegalReasons of(int errorCode, String message, Object... args) {
        touchPayload().errorCode(errorCode).message(message, args);
        return _INSTANCE;
    }


    /**
     * Returns a static UnavailableForLegalReasons instance and set the {@link #payload} thread local
     * with error code and cause specified
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param cause  the cause
     * @param errorCode the app defined error code
     * @return a static UnavailableForLegalReasons instance as described above
     */
    public static UnavailableForLegalReasons of(int errorCode, Throwable cause) {
        if (_localizedErrorMsg()) {
            return of(errorCode, cause, defaultMessage(UNAVAILABLE_FOR_LEGAL_REASONS));
        } else {
            touchPayload().errorCode(errorCode).cause(cause);
            return _INSTANCE;
        }
    }


    /**
     * Returns a static UnavailableForLegalReasons instance and set the {@link #payload} thread local
     * with error code, cause and message specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param cause  the cause
     * @param errorCode the app defined error code
     * @param message the message
     * @param args the message arguments
     * @return a static UnavailableForLegalReasons instance as described above
     */
    public static UnavailableForLegalReasons of(int errorCode, Throwable cause, String message, Object... args) {
        touchPayload().errorCode(errorCode).message(message, args).cause(cause);
        return _INSTANCE;
    }

}
