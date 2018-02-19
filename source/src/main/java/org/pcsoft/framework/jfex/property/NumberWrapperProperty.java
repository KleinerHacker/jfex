package org.pcsoft.framework.jfex.property;

import javafx.beans.property.ObjectProperty;

import java.math.BigDecimal;
import java.math.BigInteger;


public class NumberWrapperProperty<T extends Number> extends SimpleWrapperProperty<Number, T> {
    private final Class<T> numberClass;

    public NumberWrapperProperty(ObjectProperty<T> property, Class<T> numberClass) {
        super(property);
        this.numberClass = numberClass;
    }

    @Override
    protected Number convertTo(T value) {
        return value;
    }

    @Override
    protected T convertFrom(Number value) {
        if (numberClass == byte.class || numberClass == Byte.class) {
            return (T) Byte.valueOf(value.byteValue());
        } else if (numberClass == short.class || numberClass == Short.class) {
            return (T) Short.valueOf(value.shortValue());
        } else if (numberClass == int.class || numberClass == Integer.class) {
            return (T) Integer.valueOf(value.intValue());
        } else if (numberClass == long.class || numberClass == Long.class) {
            return (T)Long.valueOf(value.longValue());
        } else if (numberClass == float.class || numberClass == Float.class) {
            return (T)Float.valueOf(value.floatValue());
        } else if (numberClass == double.class || numberClass == Double.class) {
            return (T)Double.valueOf(value.doubleValue());
        } else if (numberClass == BigInteger.class) {
            return (T)BigInteger.valueOf(value.longValue());
        } else if (numberClass == BigDecimal.class) {
            return (T)BigDecimal.valueOf(value.doubleValue());
        }

        throw new IllegalStateException("Not supported number class: " + numberClass.getName());
    }
}
