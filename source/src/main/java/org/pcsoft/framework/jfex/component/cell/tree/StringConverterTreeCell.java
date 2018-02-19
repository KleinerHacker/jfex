package org.pcsoft.framework.jfex.component.cell.tree;

import javafx.scene.control.TreeCell;
import javafx.util.StringConverter;


public class StringConverterTreeCell<T> extends TreeCell<T> {
    private final StringConverter<T> converter;

    public StringConverterTreeCell(StringConverter<T> converter) {
        this.converter = converter;
    }

    @Override
    protected void updateItem(T item, boolean empty) {
        super.updateItem(item, empty);

        setText(null);
        setGraphic(null);
        if (item != null && !empty) {
            setText(converter.toString(item));
        }
    }
}
