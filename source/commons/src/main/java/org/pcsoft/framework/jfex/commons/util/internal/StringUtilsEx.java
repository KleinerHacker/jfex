package org.pcsoft.framework.jfex.commons.util.internal;


public final class StringUtilsEx {

    public static String insert(final String str, final String insertionStr, final int index) {
        if (index < 0 || index > str.length())
            throw new IndexOutOfBoundsException("Index " + index + " is out of range; must be between 0 and " + str.length());

        return str.substring(0, index) + insertionStr + str.substring(index);
    }

    private StringUtilsEx() {
    }
}
