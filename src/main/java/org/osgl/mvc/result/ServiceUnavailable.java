package org.osgl.mvc.result;

import static org.osgl.http.H.Status.SERVICE_UNAVAILABLE;

/**
 * The server is currently unavailable (because it is overloaded or down for maintenance). Generally, this
 * is a temporary state.
 */
public class ServiceUnavailable extends ErrorResult {

    /**
     * The static instance of BadRequest result.
     */
    public static final ServiceUnavailable INSTANCE = new ServiceUnavailable();

    private static final ServiceUnavailable _INSTANCE = new ServiceUnavailable() {
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


    public ServiceUnavailable() {
        super(SERVICE_UNAVAILABLE);
    }
    public ServiceUnavailable(String message, Object ... args) {
        super(SERVICE_UNAVAILABLE, message, args);
    }
    public ServiceUnavailable(Throwable cause, String message, Object ... args) {
        super(SERVICE_UNAVAILABLE, cause, message, args);
    }
    public ServiceUnavailable(Throwable cause) {
        super(SERVICE_UNAVAILABLE, cause);
    }

    public ServiceUnavailable(int errorCode) {
        super(SERVICE_UNAVAILABLE, errorCode);
    }
    public ServiceUnavailable(int errorCode, String message, Object ... args) {
        super(SERVICE_UNAVAILABLE, errorCode, message, args);
    }
    public ServiceUnavailable(int errorCode, Throwable cause, String message, Object ... args) {
        super(SERVICE_UNAVAILABLE, errorCode, cause, message, args);
    }
    public ServiceUnavailable(int errorCode, Throwable cause) {
        super(SERVICE_UNAVAILABLE, errorCode, cause);
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
    public static ServiceUnavailable get() {
        return _localizedErrorMsg() ? of(defaultMessage(SERVICE_UNAVAILABLE)) : INSTANCE;
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
    public static ServiceUnavailable of(int errorCode) {
        touchPayload().errorCode(errorCode);
        return _localizedErrorMsg() ? of(defaultMessage(SERVICE_UNAVAILABLE)) : INSTANCE;
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
    public static ServiceUnavailable of(String message, Object... args) {
        touchPayload().message(message, args);
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
    public static ServiceUnavailable of(int errorCode, String message, Object... args) {
        touchPayload().message(message, args).errorCode(errorCode);
        return _INSTANCE;
    }

    /**
     * Returns a static BadRequest instance and set the {@link #payload} thread local
     * with message specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param cause  the cause
     * @param errorCode app defined error code
     * @param message the message
     * @param args the message arguments
     * @return a static BadRequest instance as described above
     */
    public static ServiceUnavailable of(int errorCode, Throwable cause, String message, Object... args) {
        touchPayload().message(message, args).errorCode(errorCode).cause(cause);
        return _INSTANCE;
    }

}
