package org.pcsoft.framework.jfex.ui.component.cell.tree;

import javafx.beans.property.BooleanProperty;
import javafx.scene.Node;
import org.pcsoft.framework.jfex.ui.component.cell.CellPane;
import org.pcsoft.framework.jfex.ui.component.cell.SimplifyCellPane;


public abstract class SimplifyGroupTreeCellBase<T, G extends Comparable<G>, PT extends Node & SimplifyCellPane<T>, PG extends Node & CellPane<G>> extends GroupTreeCellBase<T, G, PT, PG> {
    public SimplifyGroupTreeCellBase(Class<PT> typePaneClass, Class<PG> groupPaneClass) {
        super(typePaneClass, groupPaneClass);
    }

    public SimplifyGroupTreeCellBase(final boolean simpleView, Class<PT> typePaneClass, Class<PG> groupPaneClass) {
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
