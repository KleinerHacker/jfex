package org.pcsoft.framework.jfex.ui.dialog;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;

import java.net.URL;
import java.util.ResourceBundle;

public class GradientStopDialogView implements FxmlView<GradientStopDialogViewModel>, Initializable {
    @FXML
    private Slider sldOffset;
    @FXML
    private ColorPicker cmbColor;

    @InjectViewModel
    private GradientStopDialogViewModel viewModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sldOffset.valueProperty().bindBidirectional(viewModel.offsetProperty());
        cmbColor.valueProperty().bindBidirectional(viewModel.colorProperty());
    }
}
