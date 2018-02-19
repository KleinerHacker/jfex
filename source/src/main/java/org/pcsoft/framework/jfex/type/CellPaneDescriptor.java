package org.pcsoft.framework.jfex.type;

import javafx.beans.property.BooleanProperty;
import javafx.scene.Node;
import org.pcsoft.framework.jfex.component.cell.CellPane;
import org.pcsoft.framework.jfex.component.cell.SimplifyCellPane;

import java.util.function.BiConsumer;


public class CellPaneDescriptor<P extends Node & CellPane<T>, T> {
    protected final P cellPane;
    protected final Class<T> type;
    protected final BiConsumer<P, T> itemSetter;

    public CellPaneDescriptor(final Class<P> cellPaneClass, final Class<T> type, final BiConsumer<P, T> itemSetter) {
        try {
            this.cellPane = cellPaneClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new IllegalStateException("Unable to create instance of cell pane!", e);
        }
        this.type = type;
        this.itemSetter = itemSetter;
    }

    public void bindSimpleView(BooleanProperty simpleView) {
        if (!(cellPane instanceof SimplifyCellPane))
            throw new IllegalStateException("Cell Pane not implements " + SimplifyCellPane.class.getName() + ": " + cellPane.getClass().getName());

        ((SimplifyCellPane) cellPane).simpleViewProperty().bindBidirectional(simpleView);
    }

    public void unbindSimpleView(BooleanProperty simpleView) {
        if (!(cellPane instanceof SimplifyCellPane))
            throw new IllegalStateException("Cell Pane not implements " + SimplifyCellPane.class.getName() + ": " + cellPane.getClass().getName());

        ((SimplifyCellPane) cellPane).simpleViewProperty().unbindBidirectional(simpleView);
    }

    public Class<T> getType() {
        return type;
    }

    public P getCellPane() {
        return cellPane;
    }

    public void setItem(final T item) {
        itemSetter.accept(cellPane, item);
    }
}
