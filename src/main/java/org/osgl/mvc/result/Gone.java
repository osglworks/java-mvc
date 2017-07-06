package org.osgl.mvc.result;

import static org.osgl.http.H.Status.GONE;

/**
 * Indicates that the resource requested is no longer available and will not be available again.
 * This should be used when a resource has been intentionally removed and the resource should be
 * purged. Upon receiving a 410 status code, the client should not request the resource in the
 * future. Clients such as search engines should remove the resource from their indices. Most use
 * cases do not require clients and search engines to purge the resource, and a "404 Not Found"
 * may be used instead.
 */
public class Gone extends ErrorResult {

    /**
     * The static instance of Gone result.
     */
    public static final Gone INSTANCE = new Gone();

    private static final Gone _INSTANCE = new Gone() {
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

    public Gone() {
        super(GONE);
    }

    public Gone(String message, Object... args) {
        super(GONE, message, args);
    }

    public Gone(Throwable cause, String message, Object... args) {
        super(GONE, cause, message, args);
    }

    public Gone(Throwable cause) {
        super(GONE, cause);
    }


    public Gone(int errorCode) {
        super(GONE, errorCode);
    }

    public Gone(int errorCode, String message, Object... args) {
        super(GONE, errorCode, message, args);
    }

    public Gone(int errorCode, Throwable cause, String message, Object... args) {
        super(GONE, errorCode, cause, message, args);
    }

    public Gone(int errorCode, Throwable cause) {
        super(GONE, errorCode, cause);
    }

    /**
     * Returns a static Gone instance and set the {@link #payload} thread local
     * with default message.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @return a static Gone instance as described above
     */
    public static Gone get() {
        return _localizedErrorMsg() ? of(defaultMessage(GONE)) : INSTANCE;
    }

    /**
     * Returns a static Gone instance and set the {@link #payload} thread local
     * with message specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param message the message
     * @param args the message arguments
     * @return a static Gone instance as described above
     */
    public static Gone of(String message, Object... args) {
        touchPayload().message(message, args);
        return _INSTANCE;
    }

    /**
     * Returns a static Gone instance and set the {@link #payload} thread local
     * with message specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param cause the cause
     * @return a static Gone instance as described above
     */
    public static Gone of(Throwable cause) {
        touchPayload().cause(cause);
        return _INSTANCE;
    }

    /**
     * Returns a static Gone instance and set the {@link #payload} thread local
     * with message specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param cause the cause
     * @param message the message
     * @param args the message arguments
     * @return a static Gone instance as described above
     */
    public static Gone of(Throwable cause, String message, Object... args) {
        touchPayload().message(message, args).cause(cause);
        return _INSTANCE;
    }

}
