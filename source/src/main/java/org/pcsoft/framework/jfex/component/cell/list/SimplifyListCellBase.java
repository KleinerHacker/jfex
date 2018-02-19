package org.pcsoft.framework.jfex.component.cell.list;

import javafx.beans.property.BooleanProperty;
import javafx.scene.Node;
import org.pcsoft.framework.jfex.component.cell.SimplifyCellPane;


public abstract class SimplifyListCellBase<T, P extends Node & SimplifyCellPane<T>> extends ListCellBase<T, P> {
    public SimplifyListCellBase(final Class<P> cellPaneClass) {
        super(cellPaneClass);
    }

    public SimplifyListCellBase(final boolean simpleView, final Class<P> cellPaneClass) {
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
