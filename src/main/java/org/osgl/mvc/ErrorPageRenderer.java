package org.osgl.mvc;

/*-
 * #%L
 * OSGL MVC
 * %%
 * Copyright (C) 2014 - 2017 OSGL (Open Source General Library)
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

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
        if (request.isAjax() && fmt == H.Format.HTML) {
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
