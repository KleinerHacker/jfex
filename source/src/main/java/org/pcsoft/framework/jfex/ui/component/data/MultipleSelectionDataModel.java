package org.pcsoft.framework.jfex.ui.component.data;

import javafx.beans.property.ObjectProperty;
import javafx.scene.control.IndexedCell;
import javafx.scene.control.MultipleSelectionModel;


public interface MultipleSelectionDataModel<T, G, C extends IndexedCell> extends SimpleDataModel<T, G, C> {
    MultipleSelectionModel<T> getSelectionModel();

    ObjectProperty<MultipleSelectionModel<T>> selectionModelProperty();

    void setSelectionModel(MultipleSelectionModel<T> selectionModel);
}
