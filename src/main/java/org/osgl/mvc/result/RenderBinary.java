package org.osgl.mvc.result;

import org.osgl.$;
import org.osgl.http.H;
import org.osgl.storage.impl.SObject;
import org.osgl.util.E;
import org.osgl.util.S;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
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

        private Disposition disposition = Disposition.inline;
        private long length;
        private String name;
        private SObject binary;
        private String contentType;
        private $.Function<OutputStream, ?> outputStreamVisitor;


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
         * @param contentType the content type of the binary stream
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
            this.binary = SObject.of(name, $.notNull(file));
            this.name = name;
            this.disposition = Disposition.of(inline);
        }

        public RenderBinary($.Function<OutputStream, ?> outputStreamVisitor) {
            this.outputStreamVisitor = $.notNull(outputStreamVisitor);
        }

        @Override
        public void apply(H.Request req, H.Response resp) {
            boolean hasName = S.notBlank(name);
            try {
                applyCookies(resp);
                applyHeaders(resp);
                if (null != contentType) {
                    resp.contentType(contentType);
                } else if (hasName) {
                    String ext = S.afterLast(name, ".");
                    if (S.notBlank(ext)) {
                        H.Format format = H.Format.of(ext);
                        if (null != format) {
                            resp.initContentType(format.contentType());
                        }
                    }
                    resp.initContentType("application/octet-stream");
                }
                if (!resp.containsHeader(CONTENT_DISPOSITION)) {
                    resp.contentDisposition(name, disposition.isInline());
                }
                if (!resp.containsHeader(CONTENT_LENGTH)) {
                    if (0 < length) {
                        resp.header(CONTENT_LENGTH, S.string(length));
                    }
                }
                applyStatus(resp);
                applyBeforeCommitHandler(req, resp);
                if (null != binary) {
                    resp.writeBinary(binary);
                } else {
                    outputStreamVisitor.apply(resp.outputStream());
                }
            } catch (Exception e) {
                throw E.unexpected(e);
            } finally {
                try {
                    resp.commit();
                    applyAfterCommitHandler(req, resp);
                } finally {
                    clearThreadLocals();
                }
            }
        }

        private boolean canAsciiEncode(String string) {
            CharsetEncoder asciiEncoder = Charset.forName("US-ASCII").newEncoder();
            return asciiEncoder.canEncode(string);
        }
}
