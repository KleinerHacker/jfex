package org.pcsoft.framework.jfex.controls.ui.component.cell;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.pcsoft.framework.jfex.mvvm.FxmlView;

import java.net.URL;
import java.util.ResourceBundle;


public class LocalDateTimeCellPaneView extends FxmlView<LocalDateTimeCellPaneViewModel> {

    @FXML
    private Label lblDate;
    @FXML
    private Label lblTime;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblDate.textProperty().bind(viewModel.dateStringProperty());
        lblTime.textProperty().bind(viewModel.timeStringProperty());
    }
}
