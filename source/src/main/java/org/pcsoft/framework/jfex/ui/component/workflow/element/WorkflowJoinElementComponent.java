package org.pcsoft.framework.jfex.ui.component.workflow.element;

import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.ViewTuple;
import javafx.scene.layout.StackPane;
import org.pcsoft.framework.jfex.ui.component.workflow.type.WorkflowElementComponent;


public class WorkflowJoinElementComponent extends StackPane implements WorkflowElementComponent<WorkflowJoinElement> {
    private final WorkflowJoinElement joinElement;
    private final WorkflowJoinElementComponentView controller;

    public WorkflowJoinElementComponent(WorkflowJoinElement joinElement) {
        final ViewTuple<WorkflowJoinElementComponentView, WorkflowJoinElementComponentViewModel> viewTuple =
                FluentViewLoader.fxmlView(WorkflowJoinElementComponentView.class).root(this).load();
        controller = viewTuple.getCodeBehind();

        this.joinElement = joinElement;
    }

    @Override
    public WorkflowJoinElement getWorkflowElement() {
        return joinElement;
    }
}
