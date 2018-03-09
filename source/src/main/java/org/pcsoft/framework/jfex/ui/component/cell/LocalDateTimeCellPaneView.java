package org.pcsoft.framework.jfex.ui.component.cell;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;


public class LocalDateTimeCellPaneView implements FxmlView<LocalDateTimeCellPaneViewModel>, Initializable {

    @FXML
    private Label lblDate;
    @FXML
    private Label lblTime;

    @InjectViewModel
    private LocalDateTimeCellPaneViewModel viewModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblDate.textProperty().bind(viewModel.dateProperty());
        lblTime.textProperty().bind(viewModel.timeProperty());
    }
}
