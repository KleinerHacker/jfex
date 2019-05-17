package org.pcsoft.framework.jfex.controls.ui.component.workflow.component;

import javafx.collections.ListChangeListener;
import javafx.scene.control.ListView;
import org.pcsoft.framework.jfex.controls.ui.component.workflow.component.cell.WorkflowElementListCell;
import org.pcsoft.framework.jfex.controls.ui.component.workflow.type.WorkflowElementInfoDescriptor;

import java.util.Collections;


public class WorkflowElementListView extends ListView<WorkflowElementInfoDescriptor> {
    private boolean ignoreListChange = false;

    public WorkflowElementListView() {
        setCellFactory(param -> new WorkflowElementListCell());
        getItems().addListener((ListChangeListener<WorkflowElementInfoDescriptor>) c -> {
            if (ignoreListChange)
                return;

            ignoreListChange = true;
            Collections.sort(getItems());
            ignoreListChange = false;
        });
    }
}
