package org.pcsoft.framework.jfex.controls.type;

import javafx.beans.value.ObservableValue;
import org.controlsfx.control.PropertySheet;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Property for property sheet, see {@link org.pcsoft.framework.jfex.component.PropertySheetEx}
 * @param <T>
 */
public class SimpleProperty<T> implements PropertySheet.Item {
    private final Class<T> type;
    private final String name, description, category;
    private final Supplier<T> getter;
    private final Consumer<T> setter;
    private final ObservableValue<T> observable;

    public SimpleProperty(Class<T> type, String name, String description, String category, Supplier<T> getter, Consumer<T> setter) {
        this(type, name, description, category, getter, setter,  null);
    }

    public SimpleProperty(Class<T> type, String name, String description, String category, Supplier<T> getter, Consumer<T> setter, ObservableValue<T> observable) {
        this.type = type;
        this.name = name;
        this.description = description;
        this.category = category;
        this.getter = getter;
        this.setter = setter;
        this.observable = observable;
    }

    @Override
    public Class<?> getType() {
        return type;
    }

    @Override
    public String getCategory() {
        return category;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public Object getValue() {
        return getter.get();
    }

    @Override
    public void setValue(Object value) {
        setter.accept((T) value);
    }

    @Override
    public Optional<ObservableValue<? extends Object>> getObservableValue() {
        return Optional.ofNullable(observable);
    }
}
