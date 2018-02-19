package org.pcsoft.framework.jfex.workflow.type;

import javafx.beans.property.Property;

import java.io.Serializable;


public final class WorkflowPropertyEditorInfoDescriptor implements Serializable {
    private final Class<? extends Property> propertyClass;
    private final Class innerPropertyClass;

    private final Class editorClass;

    public WorkflowPropertyEditorInfoDescriptor(final Class<?> editorClass) {
        final WorkflowPropertyEditorInfo editorClassAnnotation = editorClass.getAnnotation(WorkflowPropertyEditorInfo.class);
        if (editorClassAnnotation == null)
            throw new IllegalStateException("Unable to find annotation '" + WorkflowPropertyEditorInfo.class.getName() + "' on class: " + editorClass.getName());

        this.propertyClass = editorClassAnnotation.propertyType();
        this.innerPropertyClass = editorClassAnnotation.innerPropertyType() == Object.class ? null : editorClassAnnotation.innerPropertyType();

        this.editorClass = editorClass;
    }

    public Class<? extends Property> getPropertyClass() {
        return propertyClass;
    }

    public Class getInnerPropertyClass() {
        return innerPropertyClass;
    }

    public Class getEditorClass() {
        return editorClass;
    }
}
