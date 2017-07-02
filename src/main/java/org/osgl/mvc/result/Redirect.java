package org.osgl.mvc.result;

import org.osgl.$;
import org.osgl.http.H;
import org.osgl.http.Http;
import org.osgl.util.S;

public class Redirect extends RedirectBase {

    private static Redirect _INSTANCE = new Redirect() {
        @Override
        protected String url() {
            return payload().message;
        }
    };

    private Redirect() {
        super(Http.Status.FOUND);
    }

    public Redirect(String url) {
        super(Http.Status.FOUND, url);
    }

    public Redirect(String url, Object... args) {
        this(S.fmt(url, args));
    }

    @Override
    protected void _applyStatus(H.Request request, H.Response response) {
        if (request.isAjax()) {
            response.status(H.Status.FOUND_AJAX);
        } else {
            applyStatus(response);
        }
    }

    public static Redirect of(String url) {
        payload.get().message(url);
        return _INSTANCE;
    }

    public static Redirect of(String url, Object... args) {
        payload.get().message(url, args);
        return _INSTANCE;
    }

    public static MovedPermanently movedPermanently(String url) {
        return MovedPermanently.of(url);
    }

    public static MovedPermanently movedPermanently(String url, Object... args) {
        return MovedPermanently.of(url, args);
    }

    public static Found found(String url) {
        return Found.of(url);
    }

    public static Found found(String url, Object... args) {
        return Found.of(url, args);
    }

    public static SeeOther seeOther(String url) {
        return SeeOther.of(url);
    }

    public static SeeOther seeOther(String url, Object... args) {
        return SeeOther.of(url, args);
    }

    public static TemporaryRedirect temporaryRedirect(String url) {
        return TemporaryRedirect.of(url);
    }

    public static TemporaryRedirect temporaryRedirect(String url, Object ... args) {
        return TemporaryRedirect.of(url, args);
    }

    public static PermanentRedirect permanentRedirect(String url) {
        return PermanentRedirect.of(url);
    }

    public static PermanentRedirect permanentRedirect(String url, Object... args) {
        return PermanentRedirect.of(url, args);
    }

    /**
     * This method is deprecated
     */
    @Deprecated
    public static Redirect moved(String url) {
        payload.get().message(url);
        return _INSTANCE;
    }

    /**
     * This method is deprecated
     */
    @Deprecated
    public static Redirect moved(String url, Object... args) {
        payload.get().message(url, args);
        return _INSTANCE;
    }

    public enum F {
        ;
        public static $.Function<String, Redirect> REDIRECT = new $.Transformer<String, Redirect>() {
            @Override
            public Redirect transform(String s) {
                return of(s);
            }
        };

        public static $.Function<String, MovedPermanently> MOVED_PERMANENTLY = new $.Transformer<String, MovedPermanently>() {
            @Override
            public MovedPermanently transform(String s) {
                return movedPermanently(s);
            }
        };

        public static $.Function<String, Found> FOUND = new $.Transformer<String, Found>() {
            @Override
            public Found transform(String s) {
                return found(s);
            }
        };

        public static $.Function<String, SeeOther> SEE_OTHER = new $.Transformer<String, SeeOther>() {
            @Override
            public SeeOther transform(String s) {
                return seeOther(s);
            }
        };

        public static $.Function<String, TemporaryRedirect> TEMPORARY_REDIRECT = new $.Transformer<String, TemporaryRedirect>() {
            @Override
            public TemporaryRedirect transform(String s) {
                return temporaryRedirect(s);
            }
        };

        public static $.Function<String, PermanentRedirect> PERMANENT_REDIRECT = new $.Transformer<String, PermanentRedirect>() {
            @Override
            public PermanentRedirect transform(String s) {
                return permanentRedirect(s);
            }
        };
    }
}
