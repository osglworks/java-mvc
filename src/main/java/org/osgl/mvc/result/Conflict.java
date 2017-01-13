package org.osgl.mvc.result;

import org.osgl.util.S;

import static org.osgl.http.H.Status.CONFLICT;

/**
 * Indicate a 404 Not found response
 */
public class Conflict extends ErrorResult {

    /**
     * The static instance of Conflict result.
     */
    public static final Conflict INSTANCE = new Conflict();

    private static final Conflict _INSTANCE = new Conflict() {
        @Override
        public String getMessage() {
            return messageBag.get();
        }
    };

    public Conflict() {
        super(CONFLICT);
    }

    public Conflict(String message, Object... args) {
        super(CONFLICT, message, args);
    }

    public Conflict(Throwable cause, String message, Object... args) {
        super(CONFLICT, cause, message, args);
    }

    public Conflict(Throwable cause) {
        super(CONFLICT, cause);
    }

    /**
     * Returns a static Conflict instance and set the {@link #messageBag} thread local
     * with default message.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #messageBag} thread local
     *
     * @return a static Conflict instance as described above
     */
    public static Conflict get() {
        return _localizedErrorMsg() ? get(defaultMessage(CONFLICT)) : INSTANCE;
    }

    /**
     * Returns a static Conflict instance and set the {@link #messageBag} thread local
     * with message specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #messageBag} thread local
     *
     * @param message the message
     * @param args the message arguments
     * @return a static Conflict instance as described above
     */
    public static Conflict get(String message, Object... args) {
        messageBag.set(S.fmt(message, args));
        return _INSTANCE;
    }

}
