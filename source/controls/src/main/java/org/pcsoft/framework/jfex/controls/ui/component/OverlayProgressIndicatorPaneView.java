package org.pcsoft.framework.jfex.controls.ui.component;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;


public class OverlayProgressIndicatorPaneView extends OverlayProgressPaneView<OverlayProgressIndicatorPaneViewModel> {

    @FXML
    private VBox pnlProgress;

    @FXML
    private ProgressIndicator pbProgress;

    @FXML
    private Label lblProgress;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initialize(pbProgress, lblProgress, pnlProgress);
    }
}
