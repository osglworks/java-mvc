package org.osgl.mvc.result;

import static org.osgl.http.H.Status.METHOD_NOT_ALLOWED;

/**
 * Indicate a 405 Method not allowed response
 */
public class MethodNotAllowed extends ErrorResult {

    /**
     * The static instance of `MethodNotAllowed` result
     */
    public static final MethodNotAllowed INSTANCE = new MethodNotAllowed();

    private static final MethodNotAllowed _INSTANCE = new MethodNotAllowed() {
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

    public MethodNotAllowed() {
        super(METHOD_NOT_ALLOWED);
    }

    public MethodNotAllowed(String message, Object... args) {
        super(METHOD_NOT_ALLOWED, message, args);
    }

    public MethodNotAllowed(Throwable cause, String message, Object ... args) {
        super(METHOD_NOT_ALLOWED, cause, message, args);
    }

    public MethodNotAllowed(Throwable cause) {
        super(METHOD_NOT_ALLOWED, cause);
    }

    public MethodNotAllowed(int errorCode) {
        super(METHOD_NOT_ALLOWED, errorCode);
    }

    public MethodNotAllowed(int errorCode, String message, Object... args) {
        super(METHOD_NOT_ALLOWED, errorCode, message, args);
    }

    public MethodNotAllowed(int errorCode, Throwable cause, String message, Object ... args) {
        super(METHOD_NOT_ALLOWED, cause, message, args);
    }

    public MethodNotAllowed(int errorCode, Throwable cause) {
        super(METHOD_NOT_ALLOWED, errorCode, cause);
    }

    /**
     * Returns a static MethodNotAllowed instance and set the {@link #payload} thread local
     * with default message.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @return a static MethodNotAllowed instance as described above
     */
    public static MethodNotAllowed get() {
        return _localizedErrorMsg() ? of(defaultMessage(METHOD_NOT_ALLOWED)) : INSTANCE;
    }

    /**
     * Returns a static MethodNotAllowed instance and set the {@link #payload} thread local
     * with default message.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param errorCode the app defined error code
     * @return a static MethodNotAllowed instance as described above
     */
    public static MethodNotAllowed of(int errorCode) {
        touchPayload().errorCode(errorCode);
        return _localizedErrorMsg() ? of(defaultMessage(METHOD_NOT_ALLOWED)) : INSTANCE;
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
     * @return a static NotImpleemented instance as described above
     */
    public static MethodNotAllowed of(String message, Object... args) {
        touchPayload().message(message, args);
        return _INSTANCE;
    }

    /**
     * Returns a static NotImplemented instance and set the {@link #payload} thread local
     * with message specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param  cause the cause
     * @param message the message
     * @param args the message arguments
     * @return a static NotImpleemented instance as described above
     */
    public static MethodNotAllowed of(Throwable cause, String message, Object... args) {
        touchPayload().message(message, args).cause(cause);
        return _INSTANCE;
    }

    /**
     * Returns a static NotImplemented instance and set the {@link #payload} thread local
     * with message specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param  cause the cause
     * @return a static NotImpleemented instance as described above
     */
    public static MethodNotAllowed of(Throwable cause) {
        touchPayload().cause(cause);
        return _INSTANCE;
    }

    /**
     * Returns a static NotImplemented instance and set the {@link #payload} thread local
     * with message specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param errorCode the app defined error code
     * @param message the message
     * @param args the message arguments
     * @return a static NotImpleemented instance as described above
     */
    public static MethodNotAllowed of(int errorCode, String message, Object... args) {
        touchPayload().errorCode(errorCode).message(message, args);
        return _INSTANCE;
    }

    /**
     * Returns a static NotImplemented instance and set the {@link #payload} thread local
     * with message specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param cause the cause
     * @param errorCode the app defined error code
     * @param message the message
     * @param args the message arguments
     * @return a static NotImpleemented instance as described above
     */
    public static MethodNotAllowed of(int errorCode, Throwable cause, String message, Object... args) {
        touchPayload().errorCode(errorCode).message(message, args).cause(cause);
        return _INSTANCE;
    }

}
