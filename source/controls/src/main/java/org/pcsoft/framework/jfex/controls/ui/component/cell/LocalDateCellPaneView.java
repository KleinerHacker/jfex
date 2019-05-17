package org.pcsoft.framework.jfex.controls.ui.component.cell;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.pcsoft.framework.jfex.mvvm.FxmlView;

import java.net.URL;
import java.util.ResourceBundle;


public class LocalDateCellPaneView extends FxmlView<LocalDateCellPaneViewModel> {

    @FXML
    private Label lblDate;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblDate.textProperty().bind(viewModel.dateStringProperty());
    }
}
