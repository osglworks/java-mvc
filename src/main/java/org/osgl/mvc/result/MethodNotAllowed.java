package org.osgl.mvc.result;

import org.osgl.util.S;

import static org.osgl.http.H.Status.METHOD_NOT_ALLOWED;

/**
 * Indicate a 405 Method not allowed response
 */
public class MethodNotAllowed extends ErrorResult {

    /**
     * The static instance of `MethodNotAllowed` result
     */
    public static final MethodNotAllowed INSTANCE = new MethodNotAllowed();

    private static final MethodNotAllowed _INSTANCE = new MethodNotAllowed() {
        @Override
        public String getMessage() {
            return messageBag.get();
        }
    };

    public MethodNotAllowed() {
        super(METHOD_NOT_ALLOWED);
    }

    public MethodNotAllowed(String message, Object... args) {
        super(METHOD_NOT_ALLOWED, message, args);
    }

    public MethodNotAllowed(Throwable cause, String message, Object ... args) {
        super(METHOD_NOT_ALLOWED, cause, message, args);
    }

    public MethodNotAllowed(Throwable cause) {
        super(METHOD_NOT_ALLOWED, cause);
    }

    /**
     * Returns a static MethodNotAllowed instance and set the {@link #messageBag} thread local
     * with default message.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #messageBag} thread local
     *
     * @return a static MethodNotAllowed instance as described above
     */
    public static MethodNotAllowed get() {
        return _localizedErrorMsg() ? get(defaultMessage(METHOD_NOT_ALLOWED)) : INSTANCE;
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
     * @return a static NotImpleemented instance as described above
     */
    public static MethodNotAllowed get(String message, Object... args) {
        messageBag.set(S.fmt(message, args));
        return _INSTANCE;
    }

}
