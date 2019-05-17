package org.pcsoft.framework.jfex.controls.ui.component.workflow.component.cell;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import org.pcsoft.framework.jfex.mvvm.FxmlView;

import java.net.URL;
import java.util.ResourceBundle;


public class WorkflowElementCellPaneView extends FxmlView<WorkflowElementCellPaneViewModel> {

    @FXML
    private ImageView imgIcon;
    @FXML
    private Label lblTitle;
    @FXML
    private Label lblDescription;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        imgIcon.imageProperty().bind(viewModel.iconProperty());
        lblTitle.textProperty().bind(viewModel.titleProperty());
        lblDescription.textProperty().bind(viewModel.descriptionProperty());
    }
}
