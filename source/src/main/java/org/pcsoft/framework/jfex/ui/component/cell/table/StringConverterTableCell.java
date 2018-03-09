package org.pcsoft.framework.jfex.ui.component.cell.table;

import javafx.scene.control.TableCell;
import javafx.util.StringConverter;


public class StringConverterTableCell<S, T> extends TableCell<S, T> {
    private final StringConverter<T> converter;

    public StringConverterTableCell(StringConverter<T> converter) {
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
