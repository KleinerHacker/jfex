package org.pcsoft.framework.jfex.ui.component.cell.tree_table;

import javafx.beans.property.BooleanProperty;
import javafx.scene.Node;
import org.pcsoft.framework.jfex.ui.component.cell.SimplifyCellPane;


public abstract class SimplifyTreeTableCellBase<S, T, P extends Node & SimplifyCellPane<T>> extends TreeTableCellBase<S, T, P> {
    public SimplifyTreeTableCellBase(final Class<P> cellPaneClass) {
        super(cellPaneClass);
    }

    public SimplifyTreeTableCellBase(final boolean simpleView, final Class<P> cellPaneClass) {
        this(cellPaneClass);
        cellPane.setSimpleView(simpleView);
    }

    public boolean getSimpleView() {
        return cellPane.getSimpleView();
    }

    public void setSimpleView(boolean simpleView) {
        cellPane.setSimpleView(simpleView);
    }

    public BooleanProperty simpleViewProperty() {
        return cellPane.simpleViewProperty();
    }
}
