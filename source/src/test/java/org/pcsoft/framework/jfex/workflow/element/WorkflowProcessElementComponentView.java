package org.pcsoft.framework.jfex.workflow.element;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;


public class WorkflowProcessElementComponentView implements FxmlView<WorkflowProcessElementComponentViewModel>, Initializable {

    @FXML
    private VBox pnlRoot;
    @FXML
    private Label lblName;

    @InjectViewModel
    private WorkflowProcessElementComponentViewModel viewModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblName.textProperty().bind(viewModel.nameProperty());
        pnlRoot.backgroundProperty().bind(
                Bindings.createObjectBinding(() -> new Background(new BackgroundFill(viewModel.getBackground(), null, null)), viewModel.backgroundProperty())
        );
    }
}
