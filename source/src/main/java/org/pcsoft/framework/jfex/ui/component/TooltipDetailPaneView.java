package org.pcsoft.framework.jfex.ui.component;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;


public class TooltipDetailPaneView implements FxmlView<TooltipDetailPaneViewModel>, Initializable {

    @FXML
    private Label lblTitle;
    @FXML
    private Label lblDescription;

    @InjectViewModel
    private TooltipDetailPaneViewModel viewModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblTitle.textProperty().bind(viewModel.titleProperty());
        lblDescription.textProperty().bind(viewModel.descriptionProperty());
    }
}
