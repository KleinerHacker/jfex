package org.pcsoft.framework.jfex.controls.ui.component.paint;

import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import org.pcsoft.framework.jfex.mvvm.FxmlView;

import java.net.URL;
import java.util.ResourceBundle;

public class PaintColorPaneView extends FxmlView<PaintColorPaneViewModel> {
    @FXML
    private ColorPicker cmbColor;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cmbColor.valueProperty().bindBidirectional(viewModel.selectedColorProperty());
    }
}
