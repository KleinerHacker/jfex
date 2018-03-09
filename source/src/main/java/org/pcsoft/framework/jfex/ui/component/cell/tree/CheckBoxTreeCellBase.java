package org.pcsoft.framework.jfex.ui.component.cell.tree;

import javafx.scene.Node;
import javafx.scene.control.cell.CheckBoxTreeCell;
import javafx.scene.layout.HBox;
import org.pcsoft.framework.jfex.type.CheckableItem;
import org.pcsoft.framework.jfex.ui.component.cell.CellPane;


public abstract class CheckBoxTreeCellBase<T, C extends Node & CellPane<T>> extends CheckBoxTreeCell<CheckableItem<T>> {
    protected final C cellPane;

    public CheckBoxTreeCellBase(final Class<C> cellPaneClass) {
        super(param -> param.getValue().checkedProperty());
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
