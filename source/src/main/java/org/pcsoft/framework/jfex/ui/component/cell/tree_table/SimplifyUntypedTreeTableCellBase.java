package org.pcsoft.framework.jfex.ui.component.cell.tree_table;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import org.pcsoft.framework.jfex.type.CellPaneDescriptor;


public abstract class SimplifyUntypedTreeTableCellBase<T, C> extends UntypedTreeTableCellBase<T, C> {
    private final BooleanProperty simpleView = new SimpleBooleanProperty();

    public SimplifyUntypedTreeTableCellBase(CellPaneDescriptor... descriptors) {
        super(descriptors);
        for (final CellPaneDescriptor descriptor : descriptorList) {
            descriptor.bindSimpleView(simpleView);
        }
    }

    public boolean isSimpleView() {
        return simpleView.get();
    }

    public BooleanProperty simpleViewProperty() {
        return simpleView;
    }

    public void setSimpleView(boolean simpleView) {
        this.simpleView.set(simpleView);
    }
}
