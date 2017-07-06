package org.osgl.mvc.result;

import static org.osgl.http.H.Status.GATEWAY_TIMEOUT;

/**
 * The server was acting as a gateway or proxy and did not receive a timely response from the upstream server.
 */
public class GatewayTimeout extends ErrorResult {

    private static final GatewayTimeout _INSTANCE = new GatewayTimeout() {
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

    public GatewayTimeout() {
        super(GATEWAY_TIMEOUT);
    }
    public GatewayTimeout(String message, Object... args) {
        super(GATEWAY_TIMEOUT, message, args);
    }
    public GatewayTimeout(Throwable t) {
        super(GATEWAY_TIMEOUT, t);
    }
    public GatewayTimeout(Throwable t, String message, Object... args) {
        super(GATEWAY_TIMEOUT, t, message, args);
    }

    public GatewayTimeout(int errorCode) {
        super(GATEWAY_TIMEOUT, errorCode);
    }
    public GatewayTimeout(int errorCode, String message, Object... args) {
        super(GATEWAY_TIMEOUT, errorCode, message, args);
    }
    public GatewayTimeout(int errorCode, Throwable cause) {
        super(GATEWAY_TIMEOUT, errorCode, cause);
    }
    public GatewayTimeout(int errorCode, Throwable cause, String message, Object... args) {
        super(GATEWAY_TIMEOUT, errorCode, cause, message, args);
    }

    /**
     * Returns a static GatewayTimeout instance and set the {@link #payload} thread local
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
     * @return a static GatewayTimeout instance as described above
     */
    public static GatewayTimeout of(Throwable cause, String message, Object... args) {
        touchPayload().cause(cause).message(message, args);
        return _INSTANCE;
    }

    /**
     * Returns a static GatewayTimeout instance and set the {@link #payload} thread local
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
     * @return a static GatewayTimeout instance as described above
     */
    public static GatewayTimeout of(int errorCode, Throwable cause, String message, Object... args) {
        touchPayload().errorCode(errorCode).cause(cause).message(message, args);
        return _INSTANCE;
    }

    /**
     * Returns a static GatewayTimeout instance and set the {@link #payload} thread local
     * with message specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param message the message
     * @param args the message arguments
     * @return a static GatewayTimeout instance as described above
     */
    public static GatewayTimeout of(String message, Object... args) {
        touchPayload().message(message, args);
        return _INSTANCE;
    }

    /**
     * Returns a static GatewayTimeout instance and set the {@link #payload} thread local
     * with message specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param errorCode the app defined error code
     * @param message the message
     * @param args the message arguments
     * @return a static GatewayTimeout instance as described above
     */
    public static GatewayTimeout of(int errorCode, String message, Object... args) {
        touchPayload().errorCode(errorCode).message(message, args);
        return _INSTANCE;
    }

}
