package org.osgl.mvc.result;

import static org.osgl.http.H.Status.BAD_REQUEST;
import static org.osgl.http.H.Status.PAYMENT_REQUIRED;

/**
 * Reserved for future use. The original intention was that this code might be used as part of
 * some form of digital cash or micropayment scheme, as proposed for example by GNU Taler, but
 * that has not yet happened, and this code is not usually used. Google Developers API uses this
 * status if a particular developer has exceeded the daily limit on requests.
 */
public class PaymentRequired extends ErrorResult {

    /**
     * The static instance of PaymentRequired result.
     */
    public static final PaymentRequired INSTANCE = new PaymentRequired();

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
        return _localizedErrorMsg() ? of(defaultMessage(BAD_REQUEST)) : INSTANCE;
    }

    /**
     * Returns a static PaymentRequired instance and set the {@link #payload} thread local
     * with default message.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param errorCode the app defined error code
     * @return a static PaymentRequired instance as described above
     */
    public static PaymentRequired of(int errorCode) {
        touchPayload().errorCode(errorCode);
        return _localizedErrorMsg() ? of(defaultMessage(BAD_REQUEST)) : INSTANCE;
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
     * with message specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param errorCode app defined error code
     * @param message the message
     * @param args the message arguments
     * @return a static PaymentRequired instance as described above
     */
    public static PaymentRequired of(int errorCode, String message, Object... args) {
        touchPayload().message(message, args).errorCode(errorCode);
        return _INSTANCE;
    }

    /**
     * Returns a static PaymentRequired instance and set the {@link #payload} thread local
     * with message specified.
     *
     * When calling the instance on {@link #getMessage()} method, it will return whatever
     * stored in the {@link #payload} thread local
     *
     * @param cause  the cause
     * @param errorCode app defined error code
     * @param message the message
     * @param args the message arguments
     * @return a static PaymentRequired instance as described above
     */
    public static PaymentRequired of(int errorCode, Throwable cause, String message, Object... args) {
        touchPayload().message(message, args).errorCode(errorCode).cause(cause);
        return _INSTANCE;
    }

}
