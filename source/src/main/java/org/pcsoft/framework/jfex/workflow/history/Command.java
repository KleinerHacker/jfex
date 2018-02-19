package org.pcsoft.framework.jfex.workflow.history;


public interface Command {
    void execute();
    void undo();
}
