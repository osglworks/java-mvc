package org.osgl.mvc.result;

import org.osgl.util.S;

import static org.osgl.http.H.Status.NOT_FOUND;

/**
 * Indicate a 404 Not found response
 */
public class NotFound extends ErrorResult {

    /**
     * The static instance of NotFound result.
     */
    public static final NotFound INSTANCE = new NotFound();

    private static final NotFound _INSTANCE = new NotFound() {
        @Override
        public String getMessage() {
            return messageBag.get();
        }
    };

    public NotFound() {
        super(NOT_FOUND);
    }

    public NotFound(String message, Object... args) {
        super(NOT_FOUND, message, args);
    }

    public NotFound(Throwable cause, String message, Object... args) {
        super(NOT_FOUND, cause, message, args);
    }

    public NotFound(Throwable cause) {
        super(NOT_FOUND, cause);
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
    public static NotFound get() {
        return _localizedErrorMsg() ? get(defaultMessage(NOT_FOUND)) : INSTANCE;
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
    public static NotFound get(String message, Object... args) {
        messageBag.set(S.fmt(message, args));
        return _INSTANCE;
    }

}
