package org.pcsoft.framework.jfex.ui.component.cell.tree_table;

import javafx.scene.control.TreeTableCell;
import javafx.util.StringConverter;


public class StringConverterTreeTableCell<S, T> extends TreeTableCell<S, T> {
    private final StringConverter<T> converter;

    public StringConverterTreeTableCell(StringConverter<T> converter) {
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
