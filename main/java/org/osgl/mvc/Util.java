package org.osgl.mvc;

import org.osgl.util.E;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

class Util {
    static byte[] bytesOf(String s) {
        try {
            return s.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            throw E.encodingException(e);
        }
    }

    static String decodeUrl(String s) {
        try {
            return URLDecoder.decode(s, "utf-8");
        } catch (UnsupportedEncodingException e) {
            throw E.encodingException(e);
        }
    }

}
