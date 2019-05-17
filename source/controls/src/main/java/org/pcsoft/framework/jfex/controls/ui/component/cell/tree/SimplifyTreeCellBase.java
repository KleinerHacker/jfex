package org.pcsoft.framework.jfex.controls.ui.component.cell.tree;

import javafx.beans.property.BooleanProperty;
import javafx.scene.Node;
import org.pcsoft.framework.jfex.controls.ui.component.cell.SimplifyCellPane;


public abstract class SimplifyTreeCellBase<T, P extends Node & SimplifyCellPane<T>> extends TreeCellBase<T, P> {

    public SimplifyTreeCellBase(final Class<P> cellPaneClass) {
        super(cellPaneClass);
    }

    public SimplifyTreeCellBase(final boolean simpleView, final Class<P> cellPaneClass) {
        this(cellPaneClass);
        cellPane.setSimpleView(simpleView);
    }

    public boolean getSimpleView() {
        return cellPane.getSimpleView();
    }

    public BooleanProperty simpleViewProperty() {
        return cellPane.simpleViewProperty();
    }

    public void setSimpleView(boolean simpleView) {
        cellPane.setSimpleView(simpleView);
    }
}
