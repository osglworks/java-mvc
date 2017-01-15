package org.osgl.mvc.result;

import static org.osgl.http.H.Status.BAD_REQUEST;

/**
 * Indicate a client error
 */
public class BadRequest extends ErrorResult {

    /**
     * The static instance of BadRequest result.
     */
    public static final BadRequest INSTANCE = new BadRequest();

    private static final BadRequest _INSTANCE = new BadRequest() {
        @Override
        public String getMessage() {
            return payload().message;
        }

        @Override
        public Integer errorCode() {
            return payload().errorCode;
        }
    };


    public BadRequest() {
        super(BAD_REQUEST);
    }
    public BadRequest(String message, Object ... args) {
        super(BAD_REQUEST, message, args);
    }
    public BadRequest(Throwable cause, String message, Object ... args) {
        super(BAD_REQUEST, cause, message, args);
    }
    public BadRequest(Throwable cause) {
        super(BAD_REQUEST, cause);
    }

    public BadRequest(int errorCode) {
        super(BAD_REQUEST, errorCode);
    }
    public BadRequest(int errorCode, String message, Object ... args) {
        super(BAD_REQUEST, errorCode, message, args);
    }
    public BadRequest(int errorCode, Throwable cause, String message, Object ... args) {
        super(BAD_REQUEST, errorCode, cause, message, args);
    }
    public BadRequest(int errorCode, Throwable cause) {
        super(BAD_REQUEST, errorCode, cause);
    }

    /**
     * Returns a static BadRequest instance and set the {@link #payload} thread local
     * with default message.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @return a static BadRequest instance as described above
     */
    public static BadRequest get() {
        return _localizedErrorMsg() ? of(defaultMessage(BAD_REQUEST)) : INSTANCE;
    }

    /**
     * Returns a static BadRequest instance and set the {@link #payload} thread local
     * with default message.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param errorCode the app defined error code
     * @return a static BadRequest instance as described above
     */
    public static BadRequest of(int errorCode) {
        payload.get().errorCode(errorCode);
        return _localizedErrorMsg() ? of(defaultMessage(BAD_REQUEST)) : INSTANCE;
    }

    /**
     * Returns a static BadRequest instance and set the {@link #payload} thread local
     * with message specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param message the message
     * @param args the message arguments
     * @return a static BadRequest instance as described above
     */
    public static BadRequest of(String message, Object... args) {
        payload.get().message(message, args);
        return _INSTANCE;
    }

    /**
     * Returns a static BadRequest instance and set the {@link #payload} thread local
     * with message specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param errorCode app defined error code
     * @param message the message
     * @param args the message arguments
     * @return a static BadRequest instance as described above
     */
    public static BadRequest of(int errorCode, String message, Object... args) {
        payload.get().message(message, args).errorCode(errorCode);
        return _INSTANCE;
    }

}
