package org.pcsoft.framework.jfex.component.cell.table;

import javafx.beans.property.ObjectProperty;
import javafx.scene.Node;
import javafx.scene.control.TableCell;
import org.pcsoft.framework.jfex.component.cell.CellPane;


public abstract class TableCellBase<S, T, P extends Node & CellPane<T>> extends TableCell<S, T> {
    protected final P cellPane;

    public TableCellBase(final Class<P> cellPaneClass) {
        try {
            cellPane = cellPaneClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new IllegalStateException("Unable to instantiate cell pane", e);
        }
    }

    @Override
    protected void updateItem(T item, boolean empty) {
        super.updateItem(item, empty);

        cleanupItem();
        if (!isItemEmpty(item, empty)) {
            setupCellPane(item);
        }
    }

    protected void cleanupItem() {
        setText(null);
        setGraphic(null);
    }

    protected boolean isItemEmpty(T item, boolean empty) {
        return item == null || empty;
    }

    protected void setupCellPane(T item) {
        cellPane.setValue(item);
        setGraphic(cellPane);
    }

    public void setValue(T value) {
        cellPane.setValue(value);
    }

    public T getValue() {
        return cellPane.getValue();
    }

    public ObjectProperty<T> valueProperty() {
        return cellPane.valueProperty();
    }
}
