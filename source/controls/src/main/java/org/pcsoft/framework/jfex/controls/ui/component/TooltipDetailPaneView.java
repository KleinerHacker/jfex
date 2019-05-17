package org.pcsoft.framework.jfex.controls.ui.component;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.pcsoft.framework.jfex.mvvm.FxmlView;

import java.net.URL;
import java.util.ResourceBundle;


public class TooltipDetailPaneView extends FxmlView<TooltipDetailPaneViewModel> {

    @FXML
    private Label lblTitle;
    @FXML
    private Label lblDescription;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblTitle.textProperty().bind(viewModel.titleProperty());
        lblDescription.textProperty().bind(viewModel.descriptionProperty());
    }
}
