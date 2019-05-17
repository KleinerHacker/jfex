package org.pcsoft.framework.jfex.controls.ui.component.cell.tree;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import org.pcsoft.framework.jfex.controls.type.CellPaneDescriptor;


public abstract class SimplifyUntypedTreeCellBase<T> extends UntypedTreeCellBase<T> {
    private final BooleanProperty simpleView = new SimpleBooleanProperty();

    public SimplifyUntypedTreeCellBase(CellPaneDescriptor... descriptors) {
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
