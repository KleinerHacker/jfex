package org.pcsoft.framework.jfex.component;

import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;


public class OverlayProgressBarPaneView extends OverlayProgressPaneView<OverlayProgressBarPaneViewModel> {

    @FXML
    private Pane pnlProgress;

    @FXML
    private ProgressIndicator pbProgress;

    @FXML
    private Label lblProgress;

    @InjectViewModel
    private OverlayProgressBarPaneViewModel viewModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initialize(pbProgress, lblProgress, pnlProgress, viewModel);
    }
}
