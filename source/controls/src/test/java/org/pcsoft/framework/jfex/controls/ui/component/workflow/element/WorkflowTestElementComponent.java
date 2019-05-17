package org.pcsoft.framework.jfex.controls.ui.component.workflow.element;

import javafx.scene.layout.GridPane;
import org.pcsoft.framework.jfex.controls.ui.component.workflow.type.WorkflowElementComponent;
import org.pcsoft.framework.jfex.mvvm.Fxml;


public class WorkflowTestElementComponent extends GridPane implements WorkflowElementComponent<WorkflowTestElement> {
    private final WorkflowTestElement testElement;
    private final WorkflowTestElementComponentView controller;

    public WorkflowTestElementComponent(WorkflowTestElement testElement) {
        final Fxml.FxmlTuple<WorkflowTestElementComponentView, WorkflowTestElementComponentViewModel> viewTuple =
                Fxml.from(WorkflowTestElementComponentView.class).withRoot(this).load();
        controller = viewTuple.getViewController();
        viewTuple.getViewModel().updateProperties(testElement);

        this.testElement = testElement;
    }

    @Override
    public WorkflowTestElement getWorkflowElement() {
        return testElement;
    }
}
