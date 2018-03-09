package org.pcsoft.framework.jfex.ui.component.workflow.element;

import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.ViewTuple;
import javafx.scene.layout.GridPane;
import org.pcsoft.framework.jfex.ui.component.workflow.type.WorkflowElementComponent;


public class WorkflowTestElementComponent extends GridPane implements WorkflowElementComponent<WorkflowTestElement> {
    private final WorkflowTestElement testElement;
    private final WorkflowTestElementComponentView controller;

    public WorkflowTestElementComponent(WorkflowTestElement testElement) {
        final ViewTuple<WorkflowTestElementComponentView, WorkflowTestElementComponentViewModel> viewTuple =
                FluentViewLoader.fxmlView(WorkflowTestElementComponentView.class).root(this).load();
        controller = viewTuple.getCodeBehind();
        viewTuple.getViewModel().updateProperties(testElement);

        this.testElement = testElement;
    }

    @Override
    public WorkflowTestElement getWorkflowElement() {
        return testElement;
    }
}
