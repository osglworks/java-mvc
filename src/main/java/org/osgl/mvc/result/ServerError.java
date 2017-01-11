package org.osgl.mvc.result;

import org.osgl.mvc.MvcConfig;
import org.osgl.util.S;

import static org.osgl.http.H.Status.INTERNAL_SERVER_ERROR;

/**
 * Indicate an internal server error and set 500 status on response
 */
public class ServerError extends ErrorResult {

    private static final ServerError _INSTANCE = new ServerError() {
        @Override
        public String getMessage() {
            return messageBag.get();
        }

        @Override
        public synchronized Throwable getCause() {
            return causeBag.get();
        }
    };

    public ServerError() {
        this(defMessage());
    }
    public ServerError(String message, Object... args) {
        super(INTERNAL_SERVER_ERROR, message, args);
    }
    public ServerError(Throwable t) {
        this(t, "Internal Error");
    }
    public ServerError(Throwable t, String message, Object... args) {
        super(INTERNAL_SERVER_ERROR, t, message, args);
    }

    static ThreadLocal<Throwable> causeBag = new ThreadLocal<Throwable>();


    /**
     * Returns a static ServerError instance and set the {@link #messageBag} thread local
     * with message specified, and store the cause specified into the {@link #causeBag}
     * thread local
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #messageBag} thread local; When calling the instance on
     * {@link #getCause()} method, it will return whatever stored in the {@link #causeBag}
     * thread local
     *
     * @param cause the cause
     * @param message the message
     * @param args the message arguments
     * @return a static ServerError instance as described above
     */
    public static ServerError get(Throwable cause, String message, Object... args) {
        causeBag.set(cause);
        messageBag.set(S.fmt(message, args));
        return _INSTANCE;
    }

    /**
     * Returns a static ServerError instance and set the {@link #messageBag} thread local
     * with message specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #messageBag} thread local
     *
     * @param message the message
     * @param args the message arguments
     * @return a static ServerError instance as described above
     */
    public static ServerError get(String message, Object... args) {
        messageBag.set(S.fmt(message, args));
        return _INSTANCE;
    }

    private static String defMessage() {
        return _localizedErrorMsg() ? MvcConfig.MSG_ID_SERVER_ERROR : "Internal Error";
    }

}
