package org.pcsoft.framework.jfex.controls.ui.component.workflow.component.cell;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.layout.HBox;
import org.pcsoft.framework.jfex.controls.ui.component.workflow.type.WorkflowElementInfoDescriptor;
import org.pcsoft.framework.jfex.mvvm.Fxml;


public class WorkflowElementCellPane extends HBox {
    private final ObjectProperty<WorkflowElementInfoDescriptor> elementInfo = new SimpleObjectProperty<>();
    private final WorkflowElementCellPaneView controller;

    public WorkflowElementCellPane() {
        final Fxml.FxmlTuple<WorkflowElementCellPaneView, WorkflowElementCellPaneViewModel> viewTuple =
                Fxml.from(WorkflowElementCellPaneView.class).withRoot(this).load();
        controller = viewTuple.getViewController();

        elementInfo.addListener((v, o, n) -> viewTuple.getViewModel().updateProperties(n));
    }

    public WorkflowElementCellPane(final WorkflowElementInfoDescriptor elementInfo) {
        this();
        setElementInfo(elementInfo);
    }

    public WorkflowElementInfoDescriptor getElementInfo() {
        return elementInfo.get();
    }

    public ObjectProperty<WorkflowElementInfoDescriptor> elementInfoProperty() {
        return elementInfo;
    }

    public void setElementInfo(WorkflowElementInfoDescriptor elementInfo) {
        this.elementInfo.set(elementInfo);
    }
}
