package org.osgl.mvc.result;

import static org.osgl.http.H.Status.BAD_GATEWAY;

/**
 * The server was acting as a gateway or proxy and received an invalid response from the upstream server.
 */
public class BadGateway extends ErrorResult {

    private static final BadGateway _INSTANCE = new BadGateway() {
        @Override
        public String getMessage() {
            return payload().message;
        }

        @Override
        public synchronized Throwable getCause() {
            return payload().cause;
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

    public BadGateway() {
        super(BAD_GATEWAY);
    }
    public BadGateway(String message, Object... args) {
        super(BAD_GATEWAY, message, args);
    }
    public BadGateway(Throwable t) {
        super(BAD_GATEWAY, t);
    }
    public BadGateway(Throwable t, String message, Object... args) {
        super(BAD_GATEWAY, t, message, args);
    }

    public BadGateway(int errorCode) {
        super(BAD_GATEWAY, errorCode);
    }
    public BadGateway(int errorCode, String message, Object... args) {
        super(BAD_GATEWAY, errorCode, message, args);
    }
    public BadGateway(int errorCode, Throwable cause) {
        super(BAD_GATEWAY, errorCode, cause);
    }
    public BadGateway(int errorCode, Throwable cause, String message, Object... args) {
        super(BAD_GATEWAY, errorCode, cause, message, args);
    }

    /**
     * Returns a static BadGateway instance and set the {@link #payload} thread local
     * with message specified, and store the cause specified into the {@link #payload}
     * thread local
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local; When calling the instance on
     * {@link #getCause()} method, it will return whatever stored in the {@link #payload}
     * thread local
     *
     * @param cause the cause
     * @param message the message
     * @param args the message arguments
     * @return a static BadGateway instance as described above
     */
    public static BadGateway of(Throwable cause, String message, Object... args) {
        touchPayload().cause(cause).message(message, args);
        return _INSTANCE;
    }

    /**
     * Returns a static BadGateway instance and set the {@link #payload} thread local
     * with message specified, and store the cause specified into the {@link #payload}
     * thread local
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local; When calling the instance on
     * {@link #getCause()} method, it will return whatever stored in the {@link #payload}
     * thread local
     *
     * @param errorCode the app defined error code
     * @param cause the cause
     * @param message the message
     * @param args the message arguments
     * @return a static BadGateway instance as described above
     */
    public static BadGateway of(int errorCode, Throwable cause, String message, Object... args) {
        touchPayload().errorCode(errorCode).cause(cause).message(message, args);
        return _INSTANCE;
    }

    /**
     * Returns a static BadGateway instance and set the {@link #payload} thread local
     * with message specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param message the message
     * @param args the message arguments
     * @return a static BadGateway instance as described above
     */
    public static BadGateway of(String message, Object... args) {
        touchPayload().message(message, args);
        return _INSTANCE;
    }

    /**
     * Returns a static BadGateway instance and set the {@link #payload} thread local
     * with message specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param errorCode the app defined error code
     * @param message the message
     * @param args the message arguments
     * @return a static BadGateway instance as described above
     */
    public static BadGateway of(int errorCode, String message, Object... args) {
        touchPayload().errorCode(errorCode).message(message, args);
        return _INSTANCE;
    }

}
