package org.pcsoft.framework.jfex.component.cell.tree_table;

import javafx.scene.Node;
import javafx.scene.control.TreeTableCell;
import org.pcsoft.framework.jfex.component.GroupComboBox;
import org.pcsoft.framework.jfex.component.cell.CellPane;


public abstract class GroupTreeTableCellBase<S, T, G extends Comparable<G>, PT extends Node & CellPane<T>, PG extends Node & CellPane<G>> extends TreeTableCell<S, GroupComboBox.Item> {
    protected final PT typePane;
    protected final PG groupPane;

    public GroupTreeTableCellBase(Class<PT> typePaneClass, Class<PG> groupPaneClass) {
        try {
            typePane = typePaneClass.newInstance();
            groupPane = groupPaneClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new IllegalStateException("Unable to instantiate cell pane", e);
        }
    }

    @Override
    protected void updateItem(GroupComboBox.Item item, boolean empty) {
        super.updateItem(item, empty);

        cleanupItem();

        if (!empty) {
            if (item instanceof GroupComboBox.GroupValueItem) {
                setupGroupCellPane((GroupComboBox.GroupValueItem<G>) item);
            } else if (item instanceof GroupComboBox.DataValueItem) {
                setupValueCellPane((GroupComboBox.DataValueItem<T>) item);
            }
        }
    }

    protected void setupValueCellPane(GroupComboBox.DataValueItem<T> item) {
        typePane.setValue(item.getValue());
        setGraphic(typePane);
        setDisable(false);
    }

    protected void setupGroupCellPane(GroupComboBox.GroupValueItem<G> item) {
        groupPane.setValue(item.getInfo());
        setGraphic(groupPane);
        setDisable(true);
    }

    protected void cleanupItem() {
        setText(null);
        setGraphic(null);
        setDisable(false);
    }
}
