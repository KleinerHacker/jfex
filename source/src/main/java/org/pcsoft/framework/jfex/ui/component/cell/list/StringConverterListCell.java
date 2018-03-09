package org.pcsoft.framework.jfex.ui.component.cell.list;

import javafx.scene.control.ListCell;
import javafx.util.StringConverter;


public class StringConverterListCell<T> extends ListCell<T> {
    private final StringConverter<T> converter;

    public StringConverterListCell(StringConverter<T> converter) {
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
