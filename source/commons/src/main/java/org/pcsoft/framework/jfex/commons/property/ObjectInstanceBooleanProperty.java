package org.pcsoft.framework.jfex.commons.property;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleBooleanProperty;

import java.util.stream.Stream;

/**
 * Property to observe an object instance via object property. This property is usable in bidirectional mode.
 */
public class ObjectInstanceBooleanProperty<T> extends SimpleBooleanProperty {
    public static final class ValuePair<T> {
        private final Property<T> value;
        private final T cacheInstance;

        public ValuePair(Property<T> value, T cacheInstance) {
            this.value = value;
            this.cacheInstance = cacheInstance;
        }

        public Property<T> valueProperty() {
            return value;
        }

        public T getCacheInstance() {
            return cacheInstance;
        }
    }

    private final ValuePair<T>[] values;

    /**
     * Instanciate the property
     * @param value Value to observe instance change
     * @param cacheInstance Cache instance to use if property value is changed from externally
     */
    public ObjectInstanceBooleanProperty(Property<T> value, T cacheInstance) {
        this(new ValuePair<>(value, cacheInstance));
    }

    public ObjectInstanceBooleanProperty(ValuePair... values) {
        super(checkAllValuesSet(values));
        this.values = values;
        for (final ValuePair valuePair : values) {
            valuePair.value.addListener((v, o, n) -> fireValueChangedEvent());
        }
    }

    private static boolean checkAllValuesSet(ValuePair[] values) {
        return Stream.of(values).allMatch(item -> item.value.getValue() != null);
    }

    @Override
    public boolean get() {
        return this.getValue();
    }

    @Override
    public void set(boolean newValue) {
        super.set(newValue);
        this.setValue(newValue);
    }

    @Override
    public void setValue(Boolean v) {
        for (final ValuePair<T> valuePair : values) {
            valuePair.value.setValue(v != null && v ? valuePair.cacheInstance : null);
        }
    }

    @Override
    public Boolean getValue() {
        return checkAllValuesSet(values);
    }
}
