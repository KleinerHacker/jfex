package org.pcsoft.framework.jfex.ui.component.workflow.element;

import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.ViewTuple;
import javafx.scene.layout.VBox;
import org.pcsoft.framework.jfex.ui.component.workflow.type.WorkflowElementComponent;


public class WorkflowProcessElementComponent extends VBox implements WorkflowElementComponent<WorkflowProcessElement> {
    private final WorkflowProcessElement processElement;
    private final WorkflowProcessElementComponentView controller;

    public WorkflowProcessElementComponent(final WorkflowProcessElement processElement) {
        final ViewTuple<WorkflowProcessElementComponentView, WorkflowProcessElementComponentViewModel> viewTuple =
                FluentViewLoader.fxmlView(WorkflowProcessElementComponentView.class).root(this).load();
        controller = viewTuple.getCodeBehind();
        viewTuple.getViewModel().updateProperties(processElement);

        this.processElement = processElement;
    }

    @Override
    public WorkflowProcessElement getWorkflowElement() {
        return processElement;
    }
}
