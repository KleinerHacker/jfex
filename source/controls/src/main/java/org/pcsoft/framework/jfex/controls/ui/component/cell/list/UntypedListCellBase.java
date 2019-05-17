package org.pcsoft.framework.jfex.controls.ui.component.cell.list;

import javafx.scene.control.ListCell;
import org.pcsoft.framework.jfex.controls.type.CellPaneDescriptor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public abstract class UntypedListCellBase<T> extends ListCell<T> {
    protected final List<CellPaneDescriptor> descriptorList = new ArrayList<>();

    public UntypedListCellBase(final CellPaneDescriptor... descriptors) {
        descriptorList.addAll(Arrays.asList(descriptors));
    }

    @Override
    protected void updateItem(T item, boolean empty) {
        super.updateItem(item, empty);

        cleanupItem();

        if (!isItemEmpty(item, empty)) {
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

    protected void setupCellPane(T item, CellPaneDescriptor cellPaneDescriptor) {
        cellPaneDescriptor.setItem(item);
        setGraphic(cellPaneDescriptor.getCellPane());
    }

    protected boolean isItemEmpty(T item, boolean empty) {
        return item == null || empty;
    }

    protected void cleanupItem() {
        setText(null);
        setGraphic(null);
    }
}
