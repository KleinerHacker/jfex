package org.pcsoft.framework.jfex.workflow.element;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;


public class WorkflowForkElementComponentView implements FxmlView<WorkflowForkElementComponentViewModel>, Initializable {

    @InjectViewModel
    private WorkflowForkElementComponentViewModel viewModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
