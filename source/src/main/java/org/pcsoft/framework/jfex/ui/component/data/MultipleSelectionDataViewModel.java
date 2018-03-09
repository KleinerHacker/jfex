package org.pcsoft.framework.jfex.ui.component.data;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.IndexedCell;
import javafx.scene.control.MultipleSelectionModel;


public abstract class MultipleSelectionDataViewModel<T, G, C extends IndexedCell> extends SimpleDataViewModel<T, G, C> implements MultipleSelectionDataModel<T, G, C> {
    private final ObjectProperty<MultipleSelectionModel<T>> selectionModel = new SimpleObjectProperty<>(new DataSelectionModelWrapper<>());

    public MultipleSelectionModel<T> getSelectionModel() {
        return selectionModel.get();
    }

    public ObjectProperty<MultipleSelectionModel<T>> selectionModelProperty() {
        return selectionModel;
    }

    public void setSelectionModel(MultipleSelectionModel<T> selectionModel) {
        this.selectionModel.set(selectionModel);
    }
}
