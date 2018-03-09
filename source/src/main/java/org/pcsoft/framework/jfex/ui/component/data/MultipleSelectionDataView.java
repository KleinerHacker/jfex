package org.pcsoft.framework.jfex.ui.component.data;

import javafx.scene.control.IndexedCell;
import javafx.scene.control.MultipleSelectionModel;

import java.net.URL;
import java.util.ResourceBundle;


public abstract class MultipleSelectionDataView<T, G, C extends IndexedCell, M extends MultipleSelectionDataViewModel<T, G, C>> extends SimpleDataView<T, G, C, M> {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

        final DataSelectionModelWrapper<T> selectionModel = (DataSelectionModelWrapper<T>) viewModel.getSelectionModel();
        selectionModel.setWrappedModel(getInnerSelectionModel());
    }

    @Override
    protected void onStartRefresh() {
        final DataSelectionModelWrapper<T> selectionModel = (DataSelectionModelWrapper<T>) viewModel.getSelectionModel();
        selectionModel.setWrappedModel(null);
    }

    @Override
    protected void onFinishRefresh() {
        final DataSelectionModelWrapper<T> selectionModel = (DataSelectionModelWrapper<T>) viewModel.getSelectionModel();
        selectionModel.setWrappedModel(getInnerSelectionModel());
        selectionModel.synchronizeSelection();
    }

    protected abstract MultipleSelectionModel<Item> getInnerSelectionModel();
}
