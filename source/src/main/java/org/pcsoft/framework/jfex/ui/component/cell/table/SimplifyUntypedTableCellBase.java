package org.pcsoft.framework.jfex.ui.component.cell.table;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import org.pcsoft.framework.jfex.type.CellPaneDescriptor;


public abstract class SimplifyUntypedTableCellBase<T, C> extends UntypedTableCellBase<T, C> {
    private final BooleanProperty simpleView = new SimpleBooleanProperty();

    public SimplifyUntypedTableCellBase(CellPaneDescriptor... descriptors) {
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
