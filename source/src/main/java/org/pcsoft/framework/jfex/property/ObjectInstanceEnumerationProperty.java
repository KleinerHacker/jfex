package org.pcsoft.framework.jfex.property;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;

import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Property to observe a lot of object instances via object properties. The usage is an enumeration for each settable
 * object instance. If enumeration value is set the object instances changed. If the object instances changed the
 * enumeration value changed. This behavior likes a choice in XSD.<br/>
 * This property is usable in bidirectional mode.
 */
public class ObjectInstanceEnumerationProperty<E> extends SimpleObjectProperty<E> {

    public static final class Value<T> {
        private final Property<T> property;
        private final T cacheInstance;

        public Value(Property<T> property, T cacheInstance) {
            this.property = property;
            this.cacheInstance = cacheInstance;
        }

        public Property<T> getProperty() {
            return property;
        }

        public T getCacheInstance() {
            return cacheInstance;
        }
    }

    private final Map<E, Value> valueMap;

    public ObjectInstanceEnumerationProperty(final Map<E, Value> valueMap) {
        super();
        this.valueMap = valueMap;

        final AtomicBoolean handle = new AtomicBoolean(true);
        for (final Value value : valueMap.values()) {
            value.property.addListener((v, o, n) -> {
                if (!handle.get())
                    return;

                //Reset values for all other values
                handle.set(false);
                for (final Value valueIn : this.valueMap.values()) {
                    if (value != valueIn) {
                        valueIn.property.setValue(null);
                    }
                }
                handle.set(true);

                //Fire change event
                fireValueChangedEvent();
            });
        }
    }

    @Override
    public E get() {
        return this.getValue();
    }

    @Override
    public void set(E newValue) {
        this.setValue(newValue);
    }

    @Override
    public void setValue(E v) {
        setEnumerationValue(v, valueMap);
        fireValueChangedEvent();
    }

    @Override
    public E getValue() {
        return getEnumerationValue(valueMap);
    }

    private static <E> E getEnumerationValue(final Map<E, Value> valueMap) {
        for (final E key : valueMap.keySet()) {
            if (valueMap.get(key).property.getValue() != null)
                return key;
        }

        return null;
    }

    private static <E> void setEnumerationValue(E newValue, final Map<E, Value> valueMap) {
        for (final E key : valueMap.keySet()) {
            final Value value = valueMap.get(key);
            if (key.equals(newValue)) {
                value.property.setValue(value.cacheInstance);
            } else {
                value.property.setValue(null);
            }
        }
    }


}
