package org.pcsoft.framework.jfex.commons.converter;

import javafx.util.StringConverter;

import java.util.HashMap;
import java.util.Map;


public final class MappingStringConverter<T> extends StringConverter<T> {

    @FunctionalInterface
    public static interface KeyResolver<T> {
        String resolveKey(T object);
    }

    private final KeyResolver<T> keyResolver;
    private final Map<String, T> map = new HashMap<>();

    public MappingStringConverter(KeyResolver<T> keyResolver) {
        this.keyResolver = keyResolver;
    }

    @Override
    public String toString(T object) {
        final String key = keyResolver.resolveKey(object);
        if (!map.containsKey(key)) {
            map.put(key, object);
        }

        return key;
    }

    @Override
    public T fromString(String string) {
        return map.get(string);
    }
}
