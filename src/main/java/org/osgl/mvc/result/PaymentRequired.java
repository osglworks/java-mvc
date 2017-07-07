package org.osgl.mvc.result;

import static org.osgl.http.H.Status.PAYMENT_REQUIRED;

/**
 * Reserved for future use. The original intention was that this code might be used as part of
 * some form of digital cash or micropayment scheme, as proposed for example by GNU Taler, but
 * that has not yet happened, and this code is not usually used. Google Developers API uses this
 * status if a particular developer has exceeded the daily limit on requests.
 */
public class PaymentRequired extends ErrorResult {

    private static final PaymentRequired _INSTANCE = new PaymentRequired() {
        @Override
        public String getMessage() {
            return payload().message;
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


    public PaymentRequired() {
        super(PAYMENT_REQUIRED);
    }
    public PaymentRequired(String message, Object ... args) {
        super(PAYMENT_REQUIRED, message, args);
    }
    public PaymentRequired(Throwable cause, String message, Object ... args) {
        super(PAYMENT_REQUIRED, cause, message, args);
    }
    public PaymentRequired(Throwable cause) {
        super(PAYMENT_REQUIRED, cause);
    }

    public PaymentRequired(int errorCode) {
        super(PAYMENT_REQUIRED, errorCode);
    }
    public PaymentRequired(int errorCode, String message, Object ... args) {
        super(PAYMENT_REQUIRED, errorCode, message, args);
    }
    public PaymentRequired(int errorCode, Throwable cause, String message, Object ... args) {
        super(PAYMENT_REQUIRED, errorCode, cause, message, args);
    }
    public PaymentRequired(int errorCode, Throwable cause) {
        super(PAYMENT_REQUIRED, errorCode, cause);
    }

    /**
     * Returns a static PaymentRequired instance and set the {@link #payload} thread local
     * with default message.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @return a static PaymentRequired instance as described above
     */
    public static PaymentRequired get() {
        if (_localizedErrorMsg()) {
            return of(defaultMessage(PAYMENT_REQUIRED));
        } else {
            touchPayload();
            return _INSTANCE;
        }
    }

    /**
     * Returns a static PaymentRequired instance and set the {@link #payload} thread local
     * with message specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param message the message
     * @param args the message arguments
     * @return a static PaymentRequired instance as described above
     */
    public static PaymentRequired of(String message, Object... args) {
        touchPayload().message(message, args);
        return _INSTANCE;
    }

    /**
     * Returns a static PaymentRequired instance and set the {@link #payload} thread local
     * with cause specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param cause the cause
     * @return a static PaymentRequired instance as described above
     */
    public static PaymentRequired of(Throwable cause) {
        if (_localizedErrorMsg()) {
            return of(cause, defaultMessage(PAYMENT_REQUIRED));
        } else {
            touchPayload().cause(cause);
            return _INSTANCE;
        }
    }

    /**
     * Returns a static PaymentRequired instance and set the {@link #payload} thread local
     * with cause and message specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param cause the cause
     * @param message the message
     * @param args the message arguments
     * @return a static PaymentRequired instance as described above
     */
    public static PaymentRequired of(Throwable cause, String message, Object... args) {
        touchPayload().message(message, args).cause(cause);
        return _INSTANCE;
    }

    /**
     * Returns a static PaymentRequired instance and set the {@link #payload} thread local
     * with error code and default message.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param errorCode the app defined error code
     * @return a static PaymentRequired instance as described above
     */
    public static PaymentRequired of(int errorCode) {
        if (_localizedErrorMsg()) {
            return of(errorCode, defaultMessage(PAYMENT_REQUIRED));
        } else {
            touchPayload().errorCode(errorCode);
            return _INSTANCE;
        }
    }

    /**
     * Returns a static PaymentRequired instance and set the {@link #payload} thread local
     * with error code and message specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param errorCode the app defined error code
     * @param message the message
     * @param args the message arguments
     * @return a static PaymentRequired instance as described above
     */
    public static PaymentRequired of(int errorCode, String message, Object... args) {
        touchPayload().errorCode(errorCode).message(message, args);
        return _INSTANCE;
    }


    /**
     * Returns a static PaymentRequired instance and set the {@link #payload} thread local
     * with error code and cause specified
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param cause  the cause
     * @param errorCode the app defined error code
     * @return a static PaymentRequired instance as described above
     */
    public static PaymentRequired of(int errorCode, Throwable cause) {
        if (_localizedErrorMsg()) {
            return of(errorCode, cause, defaultMessage(PAYMENT_REQUIRED));
        } else {
            touchPayload().errorCode(errorCode).cause(cause);
            return _INSTANCE;
        }
    }


    /**
     * Returns a static PaymentRequired instance and set the {@link #payload} thread local
     * with error code, cause and message specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param cause  the cause
     * @param errorCode the app defined error code
     * @param message the message
     * @param args the message arguments
     * @return a static PaymentRequired instance as described above
     */
    public static PaymentRequired of(int errorCode, Throwable cause, String message, Object... args) {
        touchPayload().errorCode(errorCode).message(message, args).cause(cause);
        return _INSTANCE;
    }

}
