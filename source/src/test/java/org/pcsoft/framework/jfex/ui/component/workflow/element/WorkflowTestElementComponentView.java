package org.pcsoft.framework.jfex.ui.component.workflow.element;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;


public class WorkflowTestElementComponentView implements FxmlView<WorkflowTestElementComponentViewModel>, Initializable {
    @FXML
    private Circle circleColor;

    @FXML
    private Label lblName;

    @FXML
    private Label lblInteger;

    @FXML
    private Label lblDouble;

    @FXML
    private Label lblBlobLength;

    @FXML
    private Label lblBlobHash;

    @FXML
    private Label lblChecked;

    @FXML
    private Label lblEnumeration;

    @FXML
    private Label lblCharacter;

    @InjectViewModel
    private WorkflowTestElementComponentViewModel viewModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblName.textProperty().bind(viewModel.nameProperty());
        lblInteger.textProperty().bind(viewModel.intNumberProperty());
        lblDouble.textProperty().bind(viewModel.doubleNumberProperty());
        lblChecked.textProperty().bind(viewModel.checkedProperty());
        lblCharacter.textProperty().bind(viewModel.characterProperty());
        lblBlobLength.textProperty().bind(viewModel.blobLengthProperty());
        lblBlobHash.textProperty().bind(viewModel.blobHashProperty());
        circleColor.fillProperty().bind(viewModel.colorProperty());
        lblEnumeration.textProperty().bind(viewModel.enumerationProperty());
    }
}
