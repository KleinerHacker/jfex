package org.pcsoft.framework.jfex.ui.component.cell.list;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import org.pcsoft.framework.jfex.type.CellPaneDescriptor;


public abstract class SimplifyUntypedListCellBase<T> extends UntypedListCellBase<T> {
    private final BooleanProperty simpleView = new SimpleBooleanProperty();

    public SimplifyUntypedListCellBase(CellPaneDescriptor... descriptors) {
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
