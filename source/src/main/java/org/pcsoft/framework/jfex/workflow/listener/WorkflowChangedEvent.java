package org.pcsoft.framework.jfex.workflow.listener;

import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;
import org.pcsoft.framework.jfex.workflow.type.WorkflowElement;


public class WorkflowChangedEvent extends Event {
    private final WorkflowElement workflowElement;
    private final WorkflowChange workflowChange;

    public WorkflowChangedEvent(EventType<WorkflowChangedEvent> eventType, WorkflowElement workflowElement, WorkflowChange workflowChange) {
        super(eventType);
        this.workflowElement = workflowElement;
        this.workflowChange = workflowChange;
    }

    public WorkflowChangedEvent(Object source, EventTarget target, EventType<WorkflowChangedEvent> eventType, WorkflowElement workflowElement, WorkflowChange workflowChange) {
        super(source, target, eventType);
        this.workflowElement = workflowElement;
        this.workflowChange = workflowChange;
    }

    public WorkflowElement getWorkflowElement() {
        return workflowElement;
    }

    public WorkflowChange getWorkflowChange() {
        return workflowChange;
    }

    @Override
    public String toString() {
        return workflowChange.name() + ": " + workflowElement;
    }
}
