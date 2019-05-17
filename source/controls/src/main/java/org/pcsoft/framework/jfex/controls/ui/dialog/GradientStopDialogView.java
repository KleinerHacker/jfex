package org.pcsoft.framework.jfex.controls.ui.dialog;

import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import org.pcsoft.framework.jfex.mvvm.FxmlView;

import java.net.URL;
import java.util.ResourceBundle;

public class GradientStopDialogView extends FxmlView<GradientStopDialogViewModel> {
    @FXML
    private Slider sldOffset;
    @FXML
    private ColorPicker cmbColor;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sldOffset.valueProperty().bindBidirectional(viewModel.offsetProperty());
        cmbColor.valueProperty().bindBidirectional(viewModel.colorProperty());
    }
}
