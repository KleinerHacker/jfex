package org.pcsoft.framework.jfex.converter;

import javafx.util.StringConverter;


public final class SimpleStringConverter<T> extends StringConverter<T> {

    @FunctionalInterface
    public static interface NameResolver<T> {
        String getNameFor(T object);
    }

    @FunctionalInterface
    public static interface ObjectResolver<T> {
        T getObjectFor(String name);
    }

    private final NameResolver<T> nameResolver;
    private final ObjectResolver<T> objectResolver;

    public SimpleStringConverter(NameResolver<T> nameResolver, ObjectResolver<T> objectResolver) {
        this.nameResolver = nameResolver;
        this.objectResolver = objectResolver;
    }

    @Override
    public String toString(T object) {
        return nameResolver.getNameFor(object);
    }

    @Override
    public T fromString(String string) {
        return objectResolver.getObjectFor(string);
    }
}
