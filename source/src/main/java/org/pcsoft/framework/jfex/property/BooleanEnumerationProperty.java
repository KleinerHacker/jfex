package org.pcsoft.framework.jfex.property;

import javafx.beans.Observable;
import javafx.beans.property.BooleanProperty;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;


public class BooleanEnumerationProperty<T extends Enum> extends ExtendedWrapperProperty<T> {
    public static final class Value<T extends Enum> {
        private final BooleanProperty property;
        private final T value;

        public Value(T value, BooleanProperty property) {
            this.property = property;
            this.value = value;
        }

        public BooleanProperty getProperty() {
            return property;
        }

        public T getValue() {
            return value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Value<?> value1 = (Value<?>) o;
            return Objects.equals(value, value1.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }
    }

    private final Set<Value<T>> valueList = new HashSet<>();
    private final AtomicBoolean ignoreUpdate = new AtomicBoolean(false);

    @SafeVarargs
    public BooleanEnumerationProperty(Value<T>... values) {
        this(null, values);
    }

    @SafeVarargs
    public BooleanEnumerationProperty(T value, Value<T>... values) {
        this(value, Arrays.asList(values));
    }

    public BooleanEnumerationProperty(Collection<Value<T>> values) {
        this(null, values);
    }

    public BooleanEnumerationProperty(final T value, Collection<Value<T>> values) {
        super(value, values.stream().map(item -> item.property).collect(Collectors.toList()));
        valueList.addAll(values);
        setPseudoValue(value);
    }

    @Override
    protected T getPseudoValue() {
        return valueList.stream()
                .filter(item -> item.property.get())
                .map(item -> item.value)
                .findFirst().orElse(null);
    }

    @Override
    protected void setPseudoValue(final T value) {
        valueList.forEach(item -> item.property.set(item.value == value));
    }

    @Override
    protected void onValueChange(Observable observable) {
        if (!ignoreUpdate.get()) {
            ignoreUpdate.set(true);
            try {
                valueList.stream()
                        .filter(item -> item.property != observable)
                        .forEach(item -> item.property.set(false));
            } finally {
                ignoreUpdate.set(false);
            }
        }
    }
}
