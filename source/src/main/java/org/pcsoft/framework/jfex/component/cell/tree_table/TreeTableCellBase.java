package org.pcsoft.framework.jfex.component.cell.tree_table;

import javafx.beans.property.ObjectProperty;
import javafx.scene.Node;
import javafx.scene.control.TreeTableCell;
import org.pcsoft.framework.jfex.component.cell.CellPane;


public abstract class TreeTableCellBase<S, T, P extends Node & CellPane<T>> extends TreeTableCell<S, T> {
    protected final P cellPane;

    public TreeTableCellBase(final Class<P> cellPaneClass) {
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
