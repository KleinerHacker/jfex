package org.pcsoft.framework.jfex.controls.ui.component.workflow.element;

import javafx.scene.layout.StackPane;
import org.pcsoft.framework.jfex.controls.ui.component.workflow.type.WorkflowElementComponent;
import org.pcsoft.framework.jfex.mvvm.Fxml;


public class WorkflowJoinElementComponent extends StackPane implements WorkflowElementComponent<WorkflowJoinElement> {
    private final WorkflowJoinElement joinElement;
    private final WorkflowJoinElementComponentView controller;

    public WorkflowJoinElementComponent(WorkflowJoinElement joinElement) {
        final Fxml.FxmlTuple<WorkflowJoinElementComponentView, WorkflowJoinElementComponentViewModel> viewTuple =
                Fxml.from(WorkflowJoinElementComponentView.class).withRoot(this).load();
        controller = viewTuple.getViewController();

        this.joinElement = joinElement;
    }

    @Override
    public WorkflowJoinElement getWorkflowElement() {
        return joinElement;
    }
}
