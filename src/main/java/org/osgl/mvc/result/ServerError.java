package org.osgl.mvc.result;

import static org.osgl.http.H.Status.INTERNAL_SERVER_ERROR;

/**
 * Indicate an internal server error and set 500 status on response
 */
public class ServerError extends ErrorResult {

    private static final ServerError _INSTANCE = new ServerError() {
        @Override
        public String getMessage() {
            return payload.get().message;
        }

        @Override
        public synchronized Throwable getCause() {
            return payload.get().cause;
        }

        @Override
        public Integer errorCode() {
            return payload.get().errorCode;
        }
    };

    public ServerError() {
        super(INTERNAL_SERVER_ERROR);
    }
    public ServerError(String message, Object... args) {
        super(INTERNAL_SERVER_ERROR, message, args);
    }
    public ServerError(Throwable t) {
        super(INTERNAL_SERVER_ERROR, t);
    }
    public ServerError(Throwable t, String message, Object... args) {
        super(INTERNAL_SERVER_ERROR, t, message, args);
    }

    public ServerError(int errorCode) {
        super(INTERNAL_SERVER_ERROR, errorCode);
    }
    public ServerError(int errorCode, String message, Object... args) {
        super(INTERNAL_SERVER_ERROR, errorCode, message, args);
    }
    public ServerError(int errorCode, Throwable cause) {
        super(INTERNAL_SERVER_ERROR, errorCode, cause);
    }
    public ServerError(int errorCode, Throwable cause, String message, Object... args) {
        super(INTERNAL_SERVER_ERROR, errorCode, cause, message, args);
    }

    /**
     * Returns a static ServerError instance and set the {@link #payload} thread local
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
     * @return a static ServerError instance as described above
     */
    public static ServerError of(Throwable cause, String message, Object... args) {
        payload.get().cause(cause).message(message, args);
        return _INSTANCE;
    }

    /**
     * Returns a static ServerError instance and set the {@link #payload} thread local
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
     * @return a static ServerError instance as described above
     */
    public static ServerError of(int errorCode, Throwable cause, String message, Object... args) {
        payload.get().errorCode(errorCode).cause(cause).message(message, args);
        return _INSTANCE;
    }

    /**
     * Returns a static ServerError instance and set the {@link #payload} thread local
     * with message specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param message the message
     * @param args the message arguments
     * @return a static ServerError instance as described above
     */
    public static ServerError of(String message, Object... args) {
        payload.get().message(message, args);
        return _INSTANCE;
    }

    /**
     * Returns a static ServerError instance and set the {@link #payload} thread local
     * with message specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param errorCode the app defined error code
     * @param message the message
     * @param args the message arguments
     * @return a static ServerError instance as described above
     */
    public static ServerError of(int errorCode, String message, Object... args) {
        payload.get().errorCode(errorCode).message(message, args);
        return _INSTANCE;
    }

}
