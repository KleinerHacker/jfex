package org.pcsoft.framework.jfex.converter;

import javafx.util.StringConverter;


public final class EnumerationStringConverter<T extends Enum<T>> extends StringConverter<T> {

    private final Class<T> clazz;

    public EnumerationStringConverter(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public String toString(T object) {
        return object.name();
    }

    @Override
    public T fromString(String string) {
        return Enum.valueOf(clazz, string);
    }
}
