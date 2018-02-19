package org.pcsoft.framework.jfex.type;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleBooleanProperty;


public final class CheckableItem<T> {
    private final BooleanProperty checked = new SimpleBooleanProperty(false);
    private final ReadOnlyObjectProperty<T> value;

    public CheckableItem(final T value) {
        this.value = new ReadOnlyObjectWrapper<>(value).getReadOnlyProperty();
    }

    public boolean getChecked() {
        return checked.get();
    }

    public BooleanProperty checkedProperty() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked.set(checked);
    }

    public T getValue() {
        return value.get();
    }

    public ReadOnlyObjectProperty<T> valueProperty() {
        return value;
    }
}
