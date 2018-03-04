package org.osgl.mvc.result;

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
import org.osgl.http.H;
import org.osgl.http.Http;
import org.osgl.util.Output;
import org.osgl.util.S;

public class RenderXML extends RenderContent {

    private static RenderXML _INSTANCE = new RenderXML() {
        @Override
        public String content() {
            return payload().message;
        }

        @Override
        public $.Visitor<Output> contentWriter() {
            return payload().contentWriter;
        }

        @Override
        public Http.Status status() {
            Http.Status status = payload().status;
            return null == status ? super.status() : status;
        }
    };

    private RenderXML() {
        super(H.Format.XML);
    }

    public RenderXML(String xml, Object... args) {
        super(S.fmt(xml, args), H.Format.XML);
    }

    public RenderXML(H.Status status, String xml, Object... args) {
        super(status, S.fmt(xml, args), H.Format.XML);
    }

    public RenderXML(String xmlStr) {
        super(xmlStr, H.Format.XML);
    }

    public RenderXML(H.Status status, String xmlStr) {
        super(status, xmlStr, H.Format.XML);
    }

    public static RenderXML of($.Visitor<Output> contentWriter) {
        touchPayload().contentWriter(contentWriter);
        return _INSTANCE;
    }

    public static RenderXML of(String xml) {
        touchPayload().message(xml);
        return _INSTANCE;
    }

    public static RenderXML of(H.Status status, String xml) {
        touchPayload().message(xml).status(status);
        return _INSTANCE;
    }

    public static RenderXML of(String xml, Object... args) {
        touchPayload().message(xml, args);
        return _INSTANCE;
    }

    public static RenderXML of(H.Status status, String xml, Object... args) {
        touchPayload().message(xml, args).status(status);
        return _INSTANCE;
    }

}
