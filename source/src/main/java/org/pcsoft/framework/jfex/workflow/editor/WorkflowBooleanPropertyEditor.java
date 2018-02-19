package org.pcsoft.framework.jfex.workflow.editor;

import javafx.beans.property.BooleanProperty;
import javafx.scene.control.CheckBox;
import org.pcsoft.framework.jfex.workflow.type.WorkflowPropertyEditor;
import org.pcsoft.framework.jfex.workflow.type.WorkflowPropertyEditorInfo;


@WorkflowPropertyEditorInfo(propertyType = BooleanProperty.class)
public final class WorkflowBooleanPropertyEditor extends CheckBox implements WorkflowPropertyEditor<Boolean, BooleanProperty> {

    @Override
    public Boolean getValue() {
        return isSelected();
    }

    @Override
    public void setValue(Boolean value) {
        setSelected(value);
    }

    @Override
    public BooleanProperty valueProperty() {
        return selectedProperty();
    }
}
