package org.pcsoft.framework.jfex.ui.component.data;

import javafx.scene.control.IndexedCell;


@FunctionalInterface
public interface CellRendererCallback<L extends IndexedCell, T> {

    void onRender(L cell, T item, boolean empty);

}
