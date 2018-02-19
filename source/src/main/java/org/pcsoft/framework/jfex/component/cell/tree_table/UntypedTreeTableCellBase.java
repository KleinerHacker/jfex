package org.pcsoft.framework.jfex.component.cell.tree_table;

import javafx.scene.control.TreeTableCell;
import org.pcsoft.framework.jfex.type.CellPaneDescriptor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public abstract class UntypedTreeTableCellBase<T, C> extends TreeTableCell<T, C> {
    protected final List<CellPaneDescriptor> descriptorList = new ArrayList<>();

    public UntypedTreeTableCellBase(final CellPaneDescriptor... descriptors) {
        descriptorList.addAll(Arrays.asList(descriptors));
    }

    @Override
    protected void updateItem(C item, boolean empty) {
        super.updateItem(item, empty);

        cleanupItem();
        if (!isEmptyItem(item, empty)) {
            final CellPaneDescriptor cellPaneDescriptor = descriptorList.stream()
                    .filter(i -> item.getClass() == i.getType())
                    .findFirst().orElse(null);
            if (cellPaneDescriptor == null) {
                setText("<UNKNOWN CLASS: " + item.getClass().getName() + ">");
            } else {
                setupCellPane(item, cellPaneDescriptor);
            }
        }
    }

    protected void setupCellPane(C item, CellPaneDescriptor cellPaneDescriptor) {
        cellPaneDescriptor.setItem(item);
        setGraphic(cellPaneDescriptor.getCellPane());
    }

    private boolean isEmptyItem(C item, boolean empty) {
        return item == null || empty;
    }

    protected void cleanupItem() {
        setText(null);
        setGraphic(null);
    }
}
