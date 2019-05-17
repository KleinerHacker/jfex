package org.pcsoft.framework.jfex.commons.util;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang.StringUtils;


public final class HexUtils {

    @SuppressWarnings("findbugs:IM_BAD_CHECK_FOR_ODD")
    public static byte[] toByteArray(final String hex) {
        if (hex == null)
            return null;

        String value = hex;
        if (value.length() % 2 == 1) {
            value = StringUtils.leftPad(value, value.length()+1, '0');
        }

        try {
            return Hex.decodeHex(value);
        } catch (DecoderException e) {
            throw new IllegalArgumentException("Unable to decode hex string: " + value, e);
        }
    }

    public static String toHexString(final byte[] bytes) {
        if (bytes == null)
            return null;

        return Hex.encodeHexString(bytes);
    }

    private HexUtils() {
    }
}
