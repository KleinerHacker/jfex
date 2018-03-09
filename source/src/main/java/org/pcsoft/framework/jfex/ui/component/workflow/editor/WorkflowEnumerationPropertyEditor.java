package org.pcsoft.framework.jfex.ui.component.workflow.editor;

import javafx.beans.property.ObjectProperty;
import javafx.scene.control.ChoiceBox;
import org.pcsoft.framework.jfex.ui.component.workflow.type.WorkflowPropertyEditor;
import org.pcsoft.framework.jfex.ui.component.workflow.type.WorkflowPropertyEditorInfo;

import java.lang.reflect.Modifier;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@WorkflowPropertyEditorInfo(propertyType = ObjectProperty.class, innerPropertyType = Enum.class)
public final class WorkflowEnumerationPropertyEditor extends ChoiceBox<Enum> implements WorkflowPropertyEditor<Enum, ObjectProperty<Enum>> {
    public WorkflowEnumerationPropertyEditor(Class<? extends Enum> clazz) {
        setMaxWidth(Double.MAX_VALUE);
        getItems().setAll(Stream.of(clazz.getDeclaredFields())
                .filter(v -> Modifier.isStatic(v.getModifiers()) && Modifier.isPublic(v.getModifiers()))
                .map(v -> {
                    try {
                        return (Enum) v.get(null);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                        return null;
                    }
                })
                .filter(v -> v != null)
                .collect(Collectors.toList()));
    }
}
