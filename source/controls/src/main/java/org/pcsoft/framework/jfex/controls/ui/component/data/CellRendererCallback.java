package org.pcsoft.framework.jfex.controls.ui.component.data;

import javafx.scene.control.IndexedCell;


@FunctionalInterface
public interface CellRendererCallback<L extends IndexedCell, T> {

    void onRender(L cell, T item, boolean empty);

}
