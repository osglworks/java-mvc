package org.osgl.mvc.result;

import org.osgl.util.S;

import static org.osgl.http.H.Status.NOT_IMPLEMENTED;

/**
 * Indicate a 501 Not implemented response
 */
public class NotImplemented extends ErrorResult {

    /**
     * The static instance of NotImplemented result.
     */
    public static final NotImplemented INSTANCE = new NotImplemented();

    private static final NotImplemented _INSTANCE = new NotImplemented() {
        @Override
        public String getMessage() {
            return messageBag.get();
        }
    };

    public NotImplemented() {
        super(NOT_IMPLEMENTED);
    }

    public NotImplemented(String message, Object... args) {
        super(NOT_IMPLEMENTED, message, args);
    }

    public NotImplemented(Throwable cause, String message, Object... args) {
        super(NOT_IMPLEMENTED, cause, message, args);
    }

    public NotImplemented(Throwable cause) {
        super(NOT_IMPLEMENTED, cause);
    }

    /**
     * Returns a static NotImplemented instance and set the {@link #messageBag} thread local
     * with default message.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #messageBag} thread local
     *
     * @return a static NotImplemented instance as described above
     */
    public static NotImplemented get() {
        return _localizedErrorMsg() ? get(defaultMessage(NOT_IMPLEMENTED)) : INSTANCE;
    }

    /**
     * Returns a static NotImplemented instance and set the {@link #messageBag} thread local
     * with message specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #messageBag} thread local
     *
     * @param message the message
     * @param args the message arguments
     * @return a static NotImplemented instance as described above
     */
    public static NotImplemented get(String message, Object... args) {
        messageBag.set(S.fmt(message, args));
        return _INSTANCE;
    }
}
