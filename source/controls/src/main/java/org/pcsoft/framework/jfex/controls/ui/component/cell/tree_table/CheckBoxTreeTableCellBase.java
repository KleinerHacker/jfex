package org.pcsoft.framework.jfex.controls.ui.component.cell.tree_table;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.scene.Node;
import javafx.scene.control.cell.CheckBoxTreeTableCell;
import javafx.scene.layout.HBox;
import org.pcsoft.framework.jfex.controls.type.CheckableItem;
import org.pcsoft.framework.jfex.controls.ui.component.cell.CellPane;


public abstract class CheckBoxTreeTableCellBase<S, T, C extends Node & CellPane<T>> extends CheckBoxTreeTableCell<S, CheckableItem<T>> {
    protected final C cellPane;

    public CheckBoxTreeTableCellBase(final Class<C> cellPaneClass) {
        setSelectedStateCallback(param -> getItem() != null ? getItem().checkedProperty() : new ReadOnlyObjectWrapper<>(false).getReadOnlyProperty());
        try {
            cellPane = cellPaneClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException("Unable to instantiate cell pane!", e);
        }
    }

    @Override
    public void updateItem(CheckableItem<T> item, boolean empty) {
        super.updateItem(item, empty);

        final Node ckb = getGraphic();

        cleanupItem();
        if (!isEmptyItem(item, empty)) {
            setupCellPane(item, ckb);
        }
    }

    protected void setupCellPane(CheckableItem<T> item, Node ckb) {
        cellPane.setValue(item.getValue());
        setGraphic(new HBox(5, ckb, cellPane));
    }

    protected boolean isEmptyItem(CheckableItem<T> item, boolean empty) {
        return item == null || empty;
    }

    protected void cleanupItem() {
        setText(null);
        setGraphic(null);
    }
}
