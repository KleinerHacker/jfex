package org.pcsoft.framework.jfex.ui.component.paint;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ColorPicker;

import java.net.URL;
import java.util.ResourceBundle;

public class PaintColorPaneView implements FxmlView<PaintColorPaneViewModel>, Initializable {
    @FXML
    private ColorPicker cmbColor;

    @InjectViewModel
    private PaintColorPaneViewModel viewModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cmbColor.valueProperty().bindBidirectional(viewModel.selectedColorProperty());
    }
}
