package org.pcsoft.framework.jfex.controls.ui.component.workflow.editor;

import javafx.beans.property.ObjectProperty;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;
import org.pcsoft.framework.jfex.controls.ui.component.workflow.type.WorkflowPropertyEditor;
import org.pcsoft.framework.jfex.controls.ui.component.workflow.type.WorkflowPropertyEditorInfo;


@WorkflowPropertyEditorInfo(propertyType = ObjectProperty.class, innerPropertyType = Color.class)
public final class WorkflowColorPropertyEditor extends ColorPicker implements WorkflowPropertyEditor<Color, ObjectProperty<Color>> {

    public WorkflowColorPropertyEditor() {
        setMaxWidth(Double.MAX_VALUE);
    }
}
