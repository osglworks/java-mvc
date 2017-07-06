package org.osgl.mvc.result;

import static org.osgl.http.H.Status.UNAVAILABLE_FOR_LEGAL_REASONS;

/**
 * A server operator has received a legal demand to deny access to a resource or to a set of resources that
 * includes the requested resource. The code 451 was chosen as a reference to the novel Fahrenheit 451.
 */
public class UnavailableForLegalReasons extends ErrorResult {

    /**
     * The static instance of UnavailableForLegalReasons result.
     */
    public static final UnavailableForLegalReasons INSTANCE = new UnavailableForLegalReasons();

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
        return _localizedErrorMsg() ? of(defaultMessage(UNAVAILABLE_FOR_LEGAL_REASONS)) : INSTANCE;
    }

    /**
     * Returns a static UnavailableForLegalReasons instance and set the {@link #payload} thread local
     * with default message.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param errorCode the app defined error code
     * @return a static UnavailableForLegalReasons instance as described above
     */
    public static UnavailableForLegalReasons of(int errorCode) {
        touchPayload().errorCode(errorCode);
        return _localizedErrorMsg() ? of(defaultMessage(UNAVAILABLE_FOR_LEGAL_REASONS)) : INSTANCE;
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
     * with message specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param cause the cause
     * @return a static UnavailableForLegalReasons instance as described above
     */
    public static UnavailableForLegalReasons of(Throwable cause) {
        touchPayload().cause(cause);
        return _INSTANCE;
    }

    /**
     * Returns a static UnavailableForLegalReasons instance and set the {@link #payload} thread local
     * with message specified.
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
     * with message specified.
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
     * with message specified.
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
