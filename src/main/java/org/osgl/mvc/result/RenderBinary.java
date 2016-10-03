package org.osgl.mvc.result;

import org.osgl.http.H;
import org.osgl.storage.impl.SObject;
import org.osgl.util.E;
import org.osgl.util.IO;
import org.osgl.util.S;

import java.io.File;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

import static org.osgl.http.H.Header.Names.CONTENT_DISPOSITION;
import static org.osgl.http.H.Header.Names.CONTENT_LENGTH;

public class RenderBinary extends Result {
        private enum Disposition {
            inline, attachment;

            public boolean isInline() {
                return inline == this;
            }

            public boolean isAttachment() {
                return attachment == this;
            }

            public static Disposition of(boolean inline) {
                return inline ? Disposition.inline : Disposition.attachment;
            }
        }

        private Disposition disposition = Disposition.attachment;
        private long length;
        private String name;
        private SObject binary;
        private String contentType;


        /**
         * send a binary stream as the response
         * @param is the stream to read from
         * @param name the name to use as Content-Diposition attachement filename
         */
        public RenderBinary(InputStream is, String name) {
            this(is, name, false);
        }

        public RenderBinary(InputStream is, String name, long length) {
            this(is, name, length, false);
        }

        /**
         * send a binary stream as the response
         * @param is the stream to read from
         * @param name the name to use as Content-Diposition attachement filename
         * @param inline true to set the response Content-Disposition to inline
         */
        public RenderBinary(InputStream is, String name, boolean inline) {
            this(is, name, null, inline);
        }

        /**
         * send a binary stream as the response
         * @param is the stream to read from
         * @param name the name to use as Content-Diposition attachement filename
         * @param inline true to set the response Content-Disposition to inline
         */
        public RenderBinary(InputStream is, String name, String contentType, boolean inline) {
            this.binary = SObject.of(name, is);
            this.name = name;
            this.contentType = contentType;
            this.disposition = Disposition.of(inline);
        }

        public RenderBinary(InputStream is, String name, long length, String contentType, boolean inline) {
            this.binary = SObject.of(name, is);
            this.name = name;
            this.contentType = contentType;
            this.disposition = Disposition.of(inline);
            this.length = length;
        }

        public RenderBinary(InputStream is, String name, long length, boolean inline) {
            this.binary = SObject.of(name, is);
            this.name = name;
            this.length = length;
            this.disposition = Disposition.of(inline);
        }

        /**
         * Send a file as the response. Content-disposion is set to attachment.
         *
         * @param file readable file to send back
         * @param name a name to use as Content-disposion's filename
         */
        public RenderBinary(File file, String name) {
            this(file, name, false);
        }

        /**
         * Send a file as the response.
         * Content-disposion is set to attachment, name is taken from file's name
         * @param file readable file to send back
         */
        public RenderBinary(File file) {
            this(file, file.getName(), true);
        }

        /**
         * Send a file as the response.
         * Content-disposition is set to attachment, name is taken from file's name
         * @param file readable file to send back
         */
        public RenderBinary(File file, String name, boolean inline) {
            if (file == null) {
                throw new RuntimeException("file is null");
            }
            this.binary = SObject.of(name, file);
            this.name = name;
            this.disposition = Disposition.of(inline);
        }

        @Override
        public void apply(H.Request req, H.Response resp) {
            boolean hasName = S.notBlank(name);
            try {
                if (null != contentType) {
                    resp.contentType(contentType);
                } else if (hasName) {
                    String ext = S.afterLast(name, ".");
                    resp.initContentType(H.Format.of(ext).toContentType());
                }
                if (!resp.containsHeader(CONTENT_DISPOSITION)) {
                    resp.contentDisposition(name, disposition.isInline());
                }
                if (!resp.containsHeader(CONTENT_LENGTH)) {
                    if (0 < length) {
                        resp.header(CONTENT_LENGTH, S.string(length));
                    }
                }
                applyBeforeCommitHandler(req, resp);
                IO.copy(binary.asInputStream(), resp.outputStream(), false);
                applyAfterCommitHandler(req, resp);
            } catch (Exception e) {
                throw E.unexpected(e);
            }
        }

        private boolean canAsciiEncode(String string) {
            CharsetEncoder asciiEncoder = Charset.forName("US-ASCII").newEncoder();
            return asciiEncoder.canEncode(string);
        }
}
