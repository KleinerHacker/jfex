package org.pcsoft.framework.jfex.util;

import org.apache.commons.lang.StringUtils;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;


public final class HexUtils {

    @SuppressWarnings("findbugs:IM_BAD_CHECK_FOR_ODD")
    public static byte[] toByteArray(final String hex) {
        if (hex == null)
            return null;

        String value = hex;
        if (value.length() % 2 == 1) {
            value = StringUtils.leftPad(value, value.length()+1, '0');
        }

        return new HexBinaryAdapter().unmarshal(value);
    }

    public static String toHexString(final byte[] bytes) {
        if (bytes == null)
            return null;

        return new HexBinaryAdapter().marshal(bytes);
    }

    private HexUtils() {
    }
}
