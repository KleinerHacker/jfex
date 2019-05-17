package org.pcsoft.framework.jfex.controls.ui.component.cell.table;

import javafx.scene.Node;
import javafx.scene.control.TableCell;
import org.pcsoft.framework.jfex.controls.ui.component.GroupComboBox;
import org.pcsoft.framework.jfex.controls.ui.component.cell.CellPane;


public abstract class GroupTableCellBase<S, T, G extends Comparable<G>, PT extends Node & CellPane<T>, PG extends Node & CellPane<G>> extends TableCell<S, GroupComboBox.Item> {
    protected final PT typePane;
    protected final PG groupPane;

    public GroupTableCellBase(Class<PT> typePaneClass, Class<PG> groupPaneClass) {
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
