package org.osgl.mvc.result;

import org.osgl.mvc.MvcConfig;
import org.osgl.util.S;

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
            return messageBag.get();
        }
    };


    public BadRequest() {
        super(BAD_REQUEST, defMessage());
    }
    public BadRequest(String message, Object ... args) {
        super(BAD_REQUEST, message, args);
    }
    public BadRequest(Throwable cause, String message, Object ... args) {
        super(BAD_REQUEST, cause, message, args);
    }

    public BadRequest(Throwable cause) {
        super(BAD_REQUEST, cause, defMessage());
    }

    /**
     * Returns a static BadRequest instance and set the {@link #messageBag} thread local
     * with default message.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #messageBag} thread local
     *
     * @return a static BadRequest instance as described above
     */
    public static BadRequest get() {
        return _localizedErrorMsg() ? get(defMessage()) : INSTANCE;
    }

    /**
     * Returns a static BadRequest instance and set the {@link #messageBag} thread local
     * with message specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #messageBag} thread local
     *
     * @param message the message
     * @param args the message arguments
     * @return a static BadRequest instance as described above
     */
    public static BadRequest get(String message, Object... args) {
        messageBag.set(S.fmt(message, args));
        return _INSTANCE;
    }

    private static String defMessage() {
        return _localizedErrorMsg() ? MvcConfig.MSG_ID_BAD_REQUEST : "400 Bad Request";
    }

}
