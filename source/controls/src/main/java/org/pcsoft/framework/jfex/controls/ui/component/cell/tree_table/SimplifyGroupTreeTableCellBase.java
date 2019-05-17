package org.pcsoft.framework.jfex.controls.ui.component.cell.tree_table;

import javafx.beans.property.BooleanProperty;
import javafx.scene.Node;
import org.pcsoft.framework.jfex.controls.ui.component.cell.CellPane;
import org.pcsoft.framework.jfex.controls.ui.component.cell.SimplifyCellPane;


public abstract class SimplifyGroupTreeTableCellBase<S, T, G extends Comparable<G>, PT extends Node & SimplifyCellPane<T>, PG extends Node & CellPane<G>> extends GroupTreeTableCellBase<S, T, G, PT, PG> {
    public SimplifyGroupTreeTableCellBase(Class<PT> typePaneClass, Class<PG> groupPaneClass) {
        super(typePaneClass, groupPaneClass);
    }

    public SimplifyGroupTreeTableCellBase(final boolean simpleView, Class<PT> typePaneClass, Class<PG> groupPaneClass) {
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
