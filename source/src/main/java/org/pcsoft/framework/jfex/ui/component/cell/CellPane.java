package org.pcsoft.framework.jfex.ui.component.cell;

import javafx.beans.property.ObjectProperty;


public interface CellPane<T> {
    T getValue();
    ObjectProperty<T> valueProperty();
    void setValue(final T value);
}
