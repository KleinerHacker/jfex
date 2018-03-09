package org.pcsoft.framework.jfex.ui.component.workflow.type;

import javafx.beans.property.Property;


public interface WorkflowPropertyEditor<T,P extends Property<T>> {

    T getValue();
    void setValue(T value);
    P valueProperty();

}
