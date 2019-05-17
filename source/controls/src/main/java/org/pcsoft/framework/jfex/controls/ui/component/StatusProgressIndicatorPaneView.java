package org.pcsoft.framework.jfex.controls.ui.component;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import org.pcsoft.framework.jfex.mvvm.FxmlView;

import java.net.URL;
import java.util.ResourceBundle;

public class StatusProgressIndicatorPaneView extends FxmlView<StatusProgressIndicatorPaneViewModel> {
    @FXML
    private Label lblProgress;
    @FXML
    private ProgressIndicator pbProgress;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
