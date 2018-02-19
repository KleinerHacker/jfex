package org.pcsoft.framework.jfex.component.cell.table;

import javafx.beans.property.BooleanProperty;
import javafx.scene.Node;
import org.pcsoft.framework.jfex.component.cell.SimplifyCellPane;


public abstract class SimplifyTableCellBase<S, T, P extends Node & SimplifyCellPane<T>> extends TableCellBase<S, T, P> {

    public SimplifyTableCellBase(final Class<P> cellPaneClass) {
        super(cellPaneClass);
    }

    public SimplifyTableCellBase(final boolean simpleView, final Class<P> cellPaneClass) {
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
