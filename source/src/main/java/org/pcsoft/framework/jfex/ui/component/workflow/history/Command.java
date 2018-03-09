package org.pcsoft.framework.jfex.ui.component.workflow.history;


public interface Command {
    void execute();
    void undo();
}
