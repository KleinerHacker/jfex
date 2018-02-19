package org.pcsoft.framework.jfex.workflow.type;

import java.util.function.Consumer;


public final class WorkflowPropertyEditorData {
    private final Consumer<WorkflowPropertyEditor> editorInitialization;

    public WorkflowPropertyEditorData() {
        this(null);
    }

    public WorkflowPropertyEditorData(Consumer<WorkflowPropertyEditor> editorInitialization) {
        this.editorInitialization = editorInitialization;
    }

    public Consumer<WorkflowPropertyEditor> getEditorInitialization() {
        return editorInitialization;
    }
}
