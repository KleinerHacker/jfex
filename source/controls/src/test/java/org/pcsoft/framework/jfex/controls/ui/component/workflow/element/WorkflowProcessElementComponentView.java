package org.pcsoft.framework.jfex.controls.ui.component.workflow.element;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import org.pcsoft.framework.jfex.mvvm.FxmlView;

import java.net.URL;
import java.util.ResourceBundle;


public class WorkflowProcessElementComponentView extends FxmlView<WorkflowProcessElementComponentViewModel> {

    @FXML
    private VBox pnlRoot;
    @FXML
    private Label lblName;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblName.textProperty().bind(viewModel.nameProperty());
        pnlRoot.backgroundProperty().bind(
                Bindings.createObjectBinding(() -> new Background(new BackgroundFill(viewModel.getBackground(), null, null)), viewModel.backgroundProperty())
        );
    }
}
