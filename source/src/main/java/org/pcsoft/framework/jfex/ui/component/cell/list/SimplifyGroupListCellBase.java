package org.pcsoft.framework.jfex.ui.component.cell.list;

import javafx.beans.property.BooleanProperty;
import javafx.scene.Node;
import org.pcsoft.framework.jfex.ui.component.cell.CellPane;
import org.pcsoft.framework.jfex.ui.component.cell.SimplifyCellPane;


public abstract class SimplifyGroupListCellBase<T, G extends Comparable<G>, PT extends Node & SimplifyCellPane<T>, PG extends Node & CellPane<G>> extends GroupListCellBase<T, G, PT, PG> {
    public SimplifyGroupListCellBase(Class<PT> typePaneClass, Class<PG> groupPaneClass) {
        super(typePaneClass, groupPaneClass);
    }

    public SimplifyGroupListCellBase(final boolean simpleView, Class<PT> typePaneClass, Class<PG> groupPaneClass) {
        this(typePaneClass, groupPaneClass);
        typePane.setSimpleView(simpleView);
    }

    public void setSimpleView(boolean simpleView) {
        typePane.setSimpleView(simpleView);
    }

    public boolean getSimpleView() {
        return typePane.getSimpleView();
    }

    public BooleanProperty simpleViewProperty() {
        return typePane.simpleViewProperty();
    }
}
