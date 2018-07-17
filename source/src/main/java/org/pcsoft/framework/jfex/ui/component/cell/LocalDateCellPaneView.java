package org.pcsoft.framework.jfex.ui.component.cell;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;


public class LocalDateCellPaneView implements FxmlView<LocalDateCellPaneViewModel>, Initializable {

    @FXML
    private Label lblDate;

    @InjectViewModel
    private LocalDateCellPaneViewModel viewModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblDate.textProperty().bind(viewModel.dateStringProperty());
    }
}
