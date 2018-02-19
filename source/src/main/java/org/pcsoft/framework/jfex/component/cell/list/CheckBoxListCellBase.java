package org.pcsoft.framework.jfex.component.cell.list;

import javafx.scene.Node;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.layout.HBox;
import org.pcsoft.framework.jfex.component.cell.CellPane;
import org.pcsoft.framework.jfex.type.CheckableItem;


public abstract class CheckBoxListCellBase<T, C extends Node & CellPane<T>> extends CheckBoxListCell<CheckableItem<T>> {
    protected final C cellPane;

    public CheckBoxListCellBase(final Class<C> cellPaneClass) {
        super(CheckableItem::checkedProperty);
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
