package org.pcsoft.framework.jfex.controls.type;

import javafx.beans.property.BooleanProperty;
import javafx.scene.Node;
import org.pcsoft.framework.jfex.controls.ui.component.cell.SimplifyCellPane;

import java.util.function.BiConsumer;


public class SimplifyCellPaneDescriptor<P extends Node & SimplifyCellPane<T>, T> extends CellPaneDescriptor<P, T> {
    public SimplifyCellPaneDescriptor(Class<P> cellPaneClass, Class<T> type, BiConsumer<P, T> itemSetter) {
        super(cellPaneClass, type, itemSetter);
    }

    public void bindSimpleView(BooleanProperty simpleView) {
        cellPane.simpleViewProperty().bindBidirectional(simpleView);
    }

    public void unbindSimpleView(BooleanProperty simpleView) {
        cellPane.simpleViewProperty().unbindBidirectional(simpleView);
    }
}
