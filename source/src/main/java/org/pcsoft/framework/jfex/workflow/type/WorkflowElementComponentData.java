package org.pcsoft.framework.jfex.workflow.type;

import java.util.function.Consumer;


public final class WorkflowElementComponentData {
    private final Class<? extends WorkflowElementComponent> componentClass;
    private final Consumer<WorkflowElementComponent> componentInitialization;

    public WorkflowElementComponentData(Class<? extends WorkflowElementComponent> componentClass) {
        this(componentClass, null);
    }

    public WorkflowElementComponentData(Class<? extends WorkflowElementComponent> componentClass, Consumer<WorkflowElementComponent> componentInitialization) {
        this.componentClass = componentClass;
        this.componentInitialization = componentInitialization;
    }

    public Class<? extends WorkflowElementComponent> getComponentClass() {
        return componentClass;
    }

    public Consumer<WorkflowElementComponent> getComponentInitialization() {
        return componentInitialization;
    }
}
