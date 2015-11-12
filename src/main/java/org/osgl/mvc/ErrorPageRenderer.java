package org.osgl.mvc;

import org.osgl._;
import org.osgl.exception.NotAppliedException;
import org.osgl.http.H;
import org.osgl.mvc.result.ErrorResult;

/**
 *
 */
public class ErrorPageRenderer extends _.F3<H.Request, H.Response, ErrorResult, Void> {

    public ErrorPageRenderer() {

    }

    @Override
    public Void apply(H.Request request, H.Response response, ErrorResult error
    ) throws NotAppliedException, _.Break {
        H.Format fmt = request.accept();
        if (request.isAjax() && fmt == H.Format.html) {
            fmt = H.Format.TXT;
        }
        String s = renderTemplate(error, fmt);
        if (null != s) {
            response.writeContent(s);
        }
        return null;
    }

    protected String renderTemplate(ErrorResult error, H.Format format) {
        return error.getMessage();
    }
}
