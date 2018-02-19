package org.pcsoft.framework.jfex.util;

import org.apache.commons.codec.binary.Base64;


public final class Base64Utils {

    public static byte[] toByteArray(String base64) {
        if (base64 == null || base64.trim().isEmpty())
            return null;

        return Base64.decodeBase64(base64);
    }

    public static String toBase64(byte[] bytes) {
        if (bytes == null)
            return null;

        return Base64.encodeBase64String(bytes);
    }

    private Base64Utils() {
    }
}
