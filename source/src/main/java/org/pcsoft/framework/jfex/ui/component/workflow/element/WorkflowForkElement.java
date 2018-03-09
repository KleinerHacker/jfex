package org.pcsoft.framework.jfex.ui.component.workflow.element;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyListProperty;
import javafx.beans.property.ReadOnlyListWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.pcsoft.framework.jfex.ui.component.workflow.type.AbstractWorkflowElement;
import org.pcsoft.framework.jfex.ui.component.workflow.type.WorkflowElement;
import org.pcsoft.framework.jfex.ui.component.workflow.type.WorkflowElementInfo;
import org.pcsoft.framework.jfex.ui.component.workflow.utils.WorkflowElementConstants;
import org.pcsoft.framework.jfex.ui.component.workflow.utils.WorkflowElementUtils;

import java.util.stream.Collectors;


@WorkflowElementInfo(name = "Fork", description = "Element to fork flow in multiple threads.", group = WorkflowElementConstants.GROUP_THREADING,
        smallIconUrl = "/icons/ic_fork16.png", bigIconUrl = "/icons/ic_fork48.png")
public final class WorkflowForkElement extends AbstractWorkflowElement<WorkflowForkElement> {
    private final ObjectProperty<WorkflowElement> parentElement = new SimpleObjectProperty<>();
    private final ReadOnlyListProperty<WorkflowElement> childElementList =
            new ReadOnlyListWrapper<WorkflowElement>(FXCollections.observableArrayList()).getReadOnlyProperty();

    public WorkflowForkElement() {
    }

    private WorkflowForkElement(WorkflowForkElement element) {
        super(element);
        this.parentElement.set(element.getParentElement() == null ? null : element.getParentElement().copy());
        this.childElementList.setAll(
                element.childElementList.stream()
                        .map(WorkflowElement::copy)
                        .collect(Collectors.toList())
        );
    }

    public WorkflowForkElement(double x, double y) {
        super(x, y);
    }

    public ObservableList<WorkflowElement> getChildElementList() {
        return childElementList.get();
    }

    public ReadOnlyListProperty<WorkflowElement> childElementListProperty() {
        return childElementList;
    }

    public WorkflowElement getParentElement() {
        return parentElement.get();
    }

    public ObjectProperty<WorkflowElement> parentElementProperty() {
        return parentElement;
    }

    public WorkflowElement setParentElement(WorkflowElement parentElement) {
        return setParentElement(parentElement, true);
    }

    public WorkflowElement setParentElement(WorkflowElement parentElement, boolean checkDependencies) {
        return WorkflowElementUtils.setParentElement(this, this.getParentElement(), parentElement, checkDependencies, this.parentElement::set);
    }

    @Override
    public WorkflowForkElement copy() {
        return new WorkflowForkElement(this);
    }
}
