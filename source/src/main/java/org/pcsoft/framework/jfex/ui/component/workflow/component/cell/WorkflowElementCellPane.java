package org.pcsoft.framework.jfex.ui.component.workflow.component.cell;

import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.ViewTuple;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.layout.HBox;
import org.pcsoft.framework.jfex.ui.component.workflow.type.WorkflowElementInfoDescriptor;


public class WorkflowElementCellPane extends HBox {
    private final ObjectProperty<WorkflowElementInfoDescriptor> elementInfo = new SimpleObjectProperty<>();
    private final WorkflowElementCellPaneView controller;

    public WorkflowElementCellPane() {
        final ViewTuple<WorkflowElementCellPaneView, WorkflowElementCellPaneViewModel> viewTuple =
                FluentViewLoader.fxmlView(WorkflowElementCellPaneView.class).root(this).load();
        controller = viewTuple.getCodeBehind();

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
