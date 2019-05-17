package org.pcsoft.framework.jfex.commons.util;

import javafx.beans.Observable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.StringProperty;
import org.pcsoft.framework.jfex.commons.property.ExtendedWrapperProperty;
import org.pcsoft.framework.jfex.commons.property.SimpleWrapperProperty;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public final class Properties {
    public static <Outer, Inner> ObjectProperty<Outer> createProperty(Property<Inner> property, Function<Inner, Outer> convertTo, Function<Outer, Inner> convertFrom) {
        return new SimpleWrapperProperty<>(property) {

            @Override
            protected Outer convertTo(Inner value) {
                return convertTo.apply(value);
            }

            @Override
            protected Inner convertFrom(Outer value) {
                return convertFrom.apply(value);
            }
        };
    }

    public static <T>ObjectProperty<T> createProperty(Supplier<T> getter, Consumer<T> setter, Observable... observables) {
        return new ExtendedWrapperProperty<>(observables) {
            @Override
            protected T getPseudoValue() {
                return getter.get();
            }

            @Override
            protected void setPseudoValue(T value) {
                setter.accept(value);
            }
        };
    }

    private Properties() {
    }
}
