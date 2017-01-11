package org.osgl.mvc.result;

import org.osgl.mvc.MvcConfig;
import org.osgl.util.S;

import static org.osgl.http.H.Status.FORBIDDEN;

/**
 * HTTP 403 Forbidden
 */
public class Forbidden extends ErrorResult {

    /**
     * The static instance of Forbidden result.
     */
    public static final Forbidden INSTANCE = new Forbidden();

    private static final Forbidden _INSTANCE = new Forbidden() {
        @Override
        public String getMessage() {
            return messageBag.get();
        }
    };

    public Forbidden() {
        super(FORBIDDEN, defMessage());
    }

    public Forbidden(String message, Object... args) {
        super(FORBIDDEN, message, args);
    }

    public Forbidden(Throwable cause, String message, Object... args) {
        super(FORBIDDEN, cause, message, args);
    }

    public Forbidden(Throwable cause) {
        super(FORBIDDEN, cause, defMessage());
    }

    /**
     * Returns a static Forbidden instance and set the {@link #messageBag} thread local
     * with default message.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #messageBag} thread local
     *
     * @return a static Forbidden instance as described above
     */
    public static Forbidden get() {
        return _localizedErrorMsg() ? get(defMessage()) : INSTANCE;
    }

    /**
     * Returns a static Forbidden instance and set the {@link #messageBag} thread local
     * with message specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #messageBag} thread local
     *
     * @param message the message
     * @param args the message arguments
     * @return a static Forbidden instance as described above
     */
    public static Forbidden get(String message, Object... args) {
        messageBag.set(S.fmt(message, args));
        return _INSTANCE;
    }

    private static String defMessage() {
        return _localizedErrorMsg() ? MvcConfig.MSG_ID_FORBIDDEN : "403 Forbidden";
    }
}
