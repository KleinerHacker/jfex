package org.pcsoft.framework.jfex.component;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;

import java.net.URL;
import java.util.ResourceBundle;

public class StatusProgressIndicatorPaneView implements FxmlView<StatusProgressIndicatorPaneViewModel>, Initializable {
    @FXML
    private Label lblProgress;
    @FXML
    private ProgressIndicator pbProgress;

    @InjectViewModel
    private StatusProgressIndicatorPaneViewModel viewModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
