package org.pcsoft.framework.jfex.util;

import java.math.BigInteger;


public final class NumberConversionUtils {

    public static Short convertToShort(final String value) {
        if (value == null || value.trim().isEmpty())
            return null;

        try {
            return Short.parseShort(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static String convertFromShort(final Short value) {
        return _convertFromShort(value, null);
    }

    public static String convertFromShort(final Short value, final short defValue) {
        return _convertFromShort(value, defValue);
    }

    private static String _convertFromShort(final Short value, final Short defValue) {
        if (value == null)
            return defValue == null ? null : String.valueOf(defValue);

        return String.valueOf(value);
    }

    public static Integer convertToInteger(final String value) {
        if (value == null || value.trim().isEmpty())
            return null;

        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static String convertFromInteger(final Integer value) {
        return _convertFromInteger(value, null);
    }

    public static String convertFromInteger(final Integer value, final int defValue) {
        return _convertFromInteger(value, defValue);
    }

    private static String _convertFromInteger(final Integer value, final Integer defValue) {
        if (value == null)
            return defValue == null ? null : String.valueOf(defValue);

        return String.valueOf(value);
    }

    public static <T extends Number>T convertToNumber(final String value, final Class<T> clazz) {
        if (value == null || value.trim().isEmpty())
            return null;

        try {
            if (clazz == Long.class || clazz == long.class)
                return (T)Long.valueOf(value);
            else if (clazz == Integer.class || clazz == int.class)
                return (T)Integer.valueOf(value);
            else if (clazz == Short.class || clazz == short.class)
                return (T)Short.valueOf(value);
            else if (clazz == Byte.class || clazz == byte.class)
                return (T)Byte.valueOf(value);
            else if (clazz == Float.class || clazz == float.class)
                return (T)Float.valueOf(value);
            else if (clazz == Double.class || clazz == double.class)
                return (T)Double.valueOf(value);
            else if (clazz == BigInteger.class)
                return (T)new BigInteger(value);
            else if (clazz == Number.class) {
                try {
                    return (T)Integer.valueOf(value);
                } catch (NumberFormatException e) {
                    return (T)Double.valueOf(value);
                }
            }
            else
                throw new IllegalArgumentException("Unknown class: " + clazz.getName());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static String convertFromNumber(final Number value) {
        return convertFromNumber(value, null);
    }

    public static String convertFromNumber(final Number value, final Number defValue) {
        if (value == null)
            return defValue == null ? null : String.valueOf(defValue);

        return String.valueOf(value);
    }

    private NumberConversionUtils() {
    }
}
