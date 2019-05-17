package org.pcsoft.framework.jfex.controls.ui.component.workflow.element;

import javafx.scene.layout.StackPane;
import org.pcsoft.framework.jfex.controls.ui.component.workflow.type.WorkflowElementComponent;
import org.pcsoft.framework.jfex.mvvm.Fxml;


public class WorkflowForkElementComponent extends StackPane implements WorkflowElementComponent<WorkflowForkElement> {
    private final WorkflowForkElementComponentView controller;
    private final WorkflowForkElement forkElement;

    public WorkflowForkElementComponent(final WorkflowForkElement forkElement) {
        final Fxml.FxmlTuple<WorkflowForkElementComponentView, WorkflowForkElementComponentViewModel> viewTuple =
                Fxml.from(WorkflowForkElementComponentView.class).withRoot(this).load();
        controller = viewTuple.getViewController();

        this.forkElement = forkElement;
    }

    @Override
    public WorkflowForkElement getWorkflowElement() {
        return forkElement;
    }
}
