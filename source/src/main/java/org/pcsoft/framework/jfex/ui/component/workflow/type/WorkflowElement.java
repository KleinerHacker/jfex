package org.pcsoft.framework.jfex.ui.component.workflow.type;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ReadOnlyStringProperty;

import java.util.Collection;


public interface WorkflowElement<T extends WorkflowElement> {
    Collection<WorkflowProperty> getElementPropertyList();
    WorkflowProperty getElementProperty(final String name);

    DoubleProperty xProperty();
    DoubleProperty yProperty();
    double getX();
    double getY();
    void setX(final double x);
    void setY(final double y);

    ReadOnlyStringProperty uuidProperty();
    String getUuid();

    T copy();
}
