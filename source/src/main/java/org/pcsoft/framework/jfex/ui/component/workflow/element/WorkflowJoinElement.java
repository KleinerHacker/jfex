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


@WorkflowElementInfo(name = "Join", description = "Join multiple workflow lines to one workflow line", group = WorkflowElementConstants.GROUP_THREADING,
        smallIconUrl = "/icons/ic_join16.png", bigIconUrl = "/icons/ic_join48.png")
public final class WorkflowJoinElement extends AbstractWorkflowElement<WorkflowJoinElement> {
    private final ReadOnlyListProperty<WorkflowElement> parentElementList =
            new ReadOnlyListWrapper<WorkflowElement>(FXCollections.observableArrayList()).getReadOnlyProperty();
    private final ObjectProperty<WorkflowElement> childElement = new SimpleObjectProperty<>();

    public WorkflowJoinElement() {
    }

    private WorkflowJoinElement(WorkflowJoinElement element) {
        super(element);
        this.parentElementList.setAll(
                element.parentElementList.stream()
                        .map(WorkflowElement::copy)
                        .collect(Collectors.toList())
        );
        this.childElement.set(element.getChildElement() == null ? null : element.getChildElement().copy());
    }

    public WorkflowJoinElement(double x, double y) {
        super(x, y);
    }

    public ObservableList<WorkflowElement> getParentElementList() {
        return parentElementList.get();
    }

    public ReadOnlyListProperty<WorkflowElement> parentElementListProperty() {
        return parentElementList;
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

    @Override
    public WorkflowJoinElement copy() {
        return new WorkflowJoinElement(this);
    }
}
