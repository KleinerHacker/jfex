package org.pcsoft.framework.jfex.controls.ui.component.workflow.element;

import javafx.scene.layout.VBox;
import org.pcsoft.framework.jfex.controls.ui.component.workflow.type.WorkflowElementComponent;
import org.pcsoft.framework.jfex.mvvm.Fxml;


public class WorkflowProcessElementComponent extends VBox implements WorkflowElementComponent<WorkflowProcessElement> {
    private final WorkflowProcessElement processElement;
    private final WorkflowProcessElementComponentView controller;

    public WorkflowProcessElementComponent(final WorkflowProcessElement processElement) {
        final Fxml.FxmlTuple<WorkflowProcessElementComponentView, WorkflowProcessElementComponentViewModel> viewTuple =
                Fxml.from(WorkflowProcessElementComponentView.class).withRoot(this).load();
        controller = viewTuple.getViewController();
        viewTuple.getViewModel().updateProperties(processElement);

        this.processElement = processElement;
    }

    @Override
    public WorkflowProcessElement getWorkflowElement() {
        return processElement;
    }
}
