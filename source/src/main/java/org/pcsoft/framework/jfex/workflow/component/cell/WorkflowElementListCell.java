package org.pcsoft.framework.jfex.workflow.component.cell;

import javafx.scene.control.ListCell;
import org.pcsoft.framework.jfex.workflow.type.WorkflowElementInfoDescriptor;


public class WorkflowElementListCell extends ListCell<WorkflowElementInfoDescriptor> {

    @Override
    protected void updateItem(WorkflowElementInfoDescriptor item, boolean empty) {
        super.updateItem(item, empty);

        setText(null);
        setGraphic(null);
        if (item != null && !empty) {
            setGraphic(new WorkflowElementCellPane(item));
        }
    }
}
