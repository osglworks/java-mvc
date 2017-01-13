package org.osgl.mvc.result;

import org.osgl.util.S;

import static org.osgl.http.H.Status.NOT_ACCEPTABLE;

/**
 * Indicate a 406 Not Acceptable
 */
public class NotAcceptable extends ErrorResult {

    /**
     * The static instance of NotFound result.
     */
    public static final NotAcceptable INSTANCE = new NotAcceptable();

    private static final NotAcceptable _INSTANCE = new NotAcceptable() {
        @Override
        public String getMessage() {
            return messageBag.get();
        }
    };

    public NotAcceptable() {
        super(NOT_ACCEPTABLE);
    }

    public NotAcceptable(String message, Object... args) {
        super(NOT_ACCEPTABLE, message, args);
    }

    /**
     * Returns a static NotFound instance and set the {@link #messageBag} thread local
     * with default message.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #messageBag} thread local
     *
     * @return a static NotFound instance as described above
     */
    public static NotAcceptable get() {
        return _localizedErrorMsg() ? get(defaultMessage(NOT_ACCEPTABLE)) : INSTANCE;
    }

    /**
     * Returns a static NotFound instance and set the {@link #messageBag} thread local
     * with message specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #messageBag} thread local
     *
     * @param message the message
     * @param args the message arguments
     * @return a static NotFound instance as described above
     */
    public static NotAcceptable get(String message, Object... args) {
        messageBag.set(S.fmt(message, args));
        return _INSTANCE;
    }

}
