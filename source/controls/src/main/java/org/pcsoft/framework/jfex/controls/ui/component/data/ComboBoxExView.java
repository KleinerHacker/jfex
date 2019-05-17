package org.pcsoft.framework.jfex.controls.ui.component.data;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import org.pcsoft.framework.jfex.controls.ui.component.OverlayProgressBarPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class ComboBoxExView<T, G> extends SimpleDataView<T, G, ListCell, ComboBoxExViewModel<T, G>> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ComboBoxExView.class);

    @FXML
    private ComboBox<Item> cmb;
    @FXML
    private OverlayProgressBarPane pnlProgress;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

        cmb.setCellFactory(param -> new ListCell<Item>() {
            @Override
            protected void updateItem(Item item, boolean empty) {
                super.updateItem(item, empty);
                renderCell(this, item, empty);
            }

            @Override
            public void updateSelected(boolean selected) {
                super.updateSelected(selected);
                renderCell(this, getItem(), isEmpty());
            }
        });
        cmb.setButtonCell(new ListCell<Item>() {
            @Override
            protected void updateItem(Item item, boolean empty) {
                super.updateItem(item, empty);

                if (item instanceof ItemValue) {
                    if (viewModel.getValueButtonCellRendererCallback() != null) {
                        viewModel.getValueButtonCellRendererCallback().onRender(this, (T) item.getData(), empty);
                    }
                }
            }
        });
        cmb.promptTextProperty().bind(viewModel.promptTextProperty());

        cmb.valueProperty().bindBidirectional(viewModel.selectedItemProperty());
    }

    @Override
    protected List<Item> getComponentList() {
        return cmb.getItems();
    }

    @Override
    protected void onStartRefresh() {
        cmb.valueProperty().unbindBidirectional(viewModel.selectedItemProperty());
    }

    @Override
    protected void onFinishRefresh() {
        if (!cmb.getItems().contains(viewModel.getSelectedItem())) {
            viewModel.setSelectedItem(null);
        }
        cmb.valueProperty().bindBidirectional(viewModel.selectedItemProperty());
    }

    @Override
    protected void onShowProgress(String initialText) {
        pnlProgress.fadeIn(initialText);
    }

    @Override
    protected void onHideProgress() {
        pnlProgress.fadeOut();
    }

    @Override
    protected Item getSelection() {
        return viewModel.getSelectedItem();
    }

    @Override
    protected void setSelection(Item item) {
        viewModel.setSelectedItem(item);
        cmb.setValue(item);
    }
}
