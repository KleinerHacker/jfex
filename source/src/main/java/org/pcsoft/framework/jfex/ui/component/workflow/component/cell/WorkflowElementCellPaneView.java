package org.pcsoft.framework.jfex.ui.component.workflow.component.cell;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;


public class WorkflowElementCellPaneView implements FxmlView<WorkflowElementCellPaneViewModel>, Initializable {

    @FXML
    private ImageView imgIcon;
    @FXML
    private Label lblTitle;
    @FXML
    private Label lblDescription;

    @InjectViewModel
    private WorkflowElementCellPaneViewModel viewModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        imgIcon.imageProperty().bind(viewModel.iconProperty());
        lblTitle.textProperty().bind(viewModel.titleProperty());
        lblDescription.textProperty().bind(viewModel.descriptionProperty());
    }
}
