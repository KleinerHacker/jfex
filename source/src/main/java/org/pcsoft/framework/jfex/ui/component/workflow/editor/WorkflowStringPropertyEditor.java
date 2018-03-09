package org.pcsoft.framework.jfex.ui.component.workflow.editor;

import javafx.beans.property.StringProperty;
import javafx.scene.control.TextField;
import org.pcsoft.framework.jfex.ui.component.workflow.type.WorkflowPropertyEditor;
import org.pcsoft.framework.jfex.ui.component.workflow.type.WorkflowPropertyEditorInfo;


@WorkflowPropertyEditorInfo(propertyType = StringProperty.class)
public final class WorkflowStringPropertyEditor extends TextField implements WorkflowPropertyEditor<String, StringProperty> {

    @Override
    public String getValue() {
        return getText();
    }

    @Override
    public void setValue(String value) {
        setText(value);
    }

    @Override
    public StringProperty valueProperty() {
        return textProperty();
    }
}
