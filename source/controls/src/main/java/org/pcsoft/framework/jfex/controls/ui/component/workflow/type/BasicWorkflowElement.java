package org.pcsoft.framework.jfex.controls.ui.component.workflow.type;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import org.pcsoft.framework.jfex.controls.ui.component.workflow.utils.WorkflowElementUtils;


public abstract class BasicWorkflowElement<T extends BasicWorkflowElement> extends AbstractWorkflowElement<T> {
    private final ObjectProperty<WorkflowElement> childElement = new SimpleObjectProperty<>();
    protected final ObjectProperty<WorkflowElement> parentElement = new SimpleObjectProperty<>();

    public BasicWorkflowElement() {
    }

    public BasicWorkflowElement(T element) {
        super(element);
        this.childElement.set(element.getChildElement() == null ? null : element.getChildElement().copy());
        this.parentElement.set(element.getParentElement() == null ? null : element.getParentElement().copy());
    }

    public BasicWorkflowElement(double x, double y) {
        super(x, y);
    }

    public WorkflowElement getChildElement() {
        return childElement.get();
    }

    public ObjectProperty<WorkflowElement> childElementProperty() {
        return childElement;
    }

    public WorkflowElement setChildElement(WorkflowElement childElement) {
        return setChildElement(childElement, true);
    }

    public WorkflowElement setChildElement(WorkflowElement childElement, boolean checkDependencies) {
        return WorkflowElementUtils.setChildElement(this, this.getChildElement(), childElement, checkDependencies, this.childElement::set);
    }

    public ObjectProperty<WorkflowElement> parentElementProperty() {
        return parentElement;
    }

    public WorkflowElement getParentElement() {
        return parentElement.get();
    }

    public WorkflowElement setParentElement(WorkflowElement parentElement) {
        return setParentElement(parentElement, true);
    }

    public WorkflowElement setParentElement(WorkflowElement parentElement, boolean checkDependencies) {
        return WorkflowElementUtils.setParentElement(this, this.getParentElement(), parentElement, checkDependencies, this.parentElement::set);
    }
}
