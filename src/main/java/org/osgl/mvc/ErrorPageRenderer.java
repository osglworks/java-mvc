package org.osgl.mvc;

import org.osgl.$;
import org.osgl.exception.NotAppliedException;
import org.osgl.http.H;
import org.osgl.mvc.result.ErrorResult;
import org.osgl.util.IO;

/**
 *
 */
public class ErrorPageRenderer extends $.F3<H.Request, H.Response, ErrorResult, Void> {

    public ErrorPageRenderer() {

    }

    @Override
    public Void apply(H.Request request, H.Response response, ErrorResult error
    ) throws NotAppliedException, $.Break {
        H.Format fmt = request.accept();
        if (request.isAjax() && fmt == H.Format.html) {
            fmt = H.Format.TXT;
        }
        MvcConfig.applyBeforeCommitResultHandler(error, request, response);
        String s = renderTemplate(error, fmt);
        if (null != s) {
            response.writeContent(s);
        } else {
            IO.close(response.outputStream());
        }
        MvcConfig.applyAfterCommitResultHandler(error, request, response);
        return null;
    }

    protected String renderTemplate(ErrorResult error, H.Format format) {
        return error.getMessage();
    }
}
