package org.pcsoft.framework.jfex.ui.component.workflow.element;

import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.ViewTuple;
import javafx.scene.layout.StackPane;
import org.pcsoft.framework.jfex.ui.component.workflow.type.WorkflowElementComponent;


public class WorkflowForkElementComponent extends StackPane implements WorkflowElementComponent<WorkflowForkElement> {
    private final WorkflowForkElementComponentView controller;
    private final WorkflowForkElement forkElement;

    public WorkflowForkElementComponent(final WorkflowForkElement forkElement) {
        final ViewTuple<WorkflowForkElementComponentView, WorkflowForkElementComponentViewModel> viewTuple =
                FluentViewLoader.fxmlView(WorkflowForkElementComponentView.class).root(this).load();
        controller = viewTuple.getCodeBehind();

        this.forkElement = forkElement;
    }

    @Override
    public WorkflowForkElement getWorkflowElement() {
        return forkElement;
    }
}
