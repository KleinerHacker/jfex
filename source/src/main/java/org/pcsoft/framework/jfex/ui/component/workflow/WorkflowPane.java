package org.pcsoft.framework.jfex.ui.component.workflow;

import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.ViewTuple;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyListProperty;
import javafx.beans.property.ReadOnlyMapProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.SplitPane;
import org.pcsoft.framework.jfex.ui.component.workflow.history.Command;
import org.pcsoft.framework.jfex.ui.component.workflow.history.HistoryModel;
import org.pcsoft.framework.jfex.ui.component.workflow.listener.WorkflowChangedEvent;
import org.pcsoft.framework.jfex.ui.component.workflow.type.WorkflowElement;
import org.pcsoft.framework.jfex.ui.component.workflow.type.WorkflowElementComponentData;
import org.pcsoft.framework.jfex.ui.component.workflow.type.WorkflowPropertyEditor;
import org.pcsoft.framework.jfex.ui.component.workflow.type.WorkflowPropertyEditorData;
import org.pcsoft.framework.jfex.ui.component.workflow.utils.WorkflowElementUtils;

import java.util.List;


public class WorkflowPane extends SplitPane {
    public static final EventType<WorkflowChangedEvent> EVENT_WORKFLOW_CHANGED = new EventType<>("EVENT_WORKFLOW_CHANGED");

    private final WorkflowPaneView controller;
    private final WorkflowPaneViewModel viewModel;

    public WorkflowPane() {
        final ViewTuple<WorkflowPaneView, WorkflowPaneViewModel> viewTuple = FluentViewLoader.fxmlView(WorkflowPaneView.class).root(this).load();
        controller = viewTuple.getCodeBehind();
        viewModel = viewTuple.getViewModel();

        viewTuple.getViewModel().setOnWorkflowChangedListener(this::fireEvent);
    }

    public void addWorkflowChangedListener(final EventHandler<WorkflowChangedEvent> listener) {
        addEventHandler(EVENT_WORKFLOW_CHANGED, listener);
    }

    public void removeWorkflowChangedListener(final EventHandler<WorkflowChangedEvent> listener) {
        removeEventHandler(EVENT_WORKFLOW_CHANGED, listener);
    }

    public ObservableMap<Class<? extends WorkflowElement>, WorkflowElementComponentData> getWorkflowComponentMapperMap() {
        return viewModel.getWorkflowComponentMapperMap();
    }

    public ReadOnlyMapProperty<Class<? extends WorkflowElement>, WorkflowElementComponentData> workflowComponentMapperMapProperty() {
        return viewModel.workflowComponentMapperMapProperty();
    }

    public ReadOnlyMapProperty<Class<? extends WorkflowPropertyEditor>, WorkflowPropertyEditorData> workflowPropertyEditorMapProperty() {
        return viewModel.workflowPropertyEditorMapProperty();
    }

    public ObservableMap<Class<? extends WorkflowPropertyEditor>, WorkflowPropertyEditorData> getWorkflowPropertyEditorMap() {
        return viewModel.getWorkflowPropertyEditorMap();
    }

    public ObservableList<WorkflowElement> getWorkflowElementList() {
        return viewModel.getWorkflowElementList();
    }

    public ReadOnlyListProperty<WorkflowElement> workflowElementListProperty() {
        return viewModel.workflowElementListProperty();
    }

    public SelectionModel<WorkflowElement> getSelectionModel() {
        return viewModel.getSelectionModel();
    }

    public ObjectProperty<SelectionModel<WorkflowElement>> selectionModelProperty() {
        return viewModel.selectionModelProperty();
    }

    public void setSelectionModel(SelectionModel<WorkflowElement> selectionModel) {
        viewModel.setSelectionModel(selectionModel);
    }

    public String getDefaultExpandedElementGroup() {
        return viewModel.getDefaultExpandedElementGroup();
    }

    /**
     * The default expended element group. If set to NULL or an empty string always the first group is selected. Default is NULL
     *
     * @return
     */
    public StringProperty defaultExpandedElementGroupProperty() {
        return viewModel.defaultExpandedElementGroupProperty();
    }

    public void setDefaultExpandedElementGroup(String defaultExpandedElementGroup) {
        viewModel.setDefaultExpandedElementGroup(defaultExpandedElementGroup);
    }

    public HistoryModel getHistoryModel() {
        return viewModel.getHistoryModel();
    }

    public ObjectProperty<HistoryModel> historyModelProperty() {
        return viewModel.historyModelProperty();
    }

    public void setHistoryModel(HistoryModel historyModel) {
        viewModel.setHistoryModel(historyModel);
    }

    public void resetWorkflowElementPosition() {
        controller.resetWorkflowElementPosition();
    }

    public void removeSelectedWorkflowElement() {
        if (getSelectionModel().getSelectedItem() == null)
            return;

        getHistoryModel().execute(new Command() {
            private WorkflowElement parentWorkflowElement = null;
            private WorkflowElement childWorkflowElement = null;

            @Override
            public void execute() {
                childWorkflowElement = getSelectionModel().getSelectedItem();
                parentWorkflowElement = removeOnNextLevel(getWorkflowElementList(), null, childWorkflowElement);
                getSelectionModel().select(null);
            }

            @Override
            public void undo() {
                if (childWorkflowElement == null) {
                    System.err.println("Unable to find removed workflow element! Do nothing");
                    return;
                }

                if (parentWorkflowElement == null) {
                    getWorkflowElementList().add(childWorkflowElement);
                } else {
                    WorkflowElementUtils.ChildUtils.setChildElement(parentWorkflowElement, childWorkflowElement, true);
                }
            }
        });
    }

    private static WorkflowElement removeOnNextLevel(final List<WorkflowElement> workflowElementList, final WorkflowElement originalWorkflowElement,
                                                     final WorkflowElement searchedWorkflowElement) {
        if (workflowElementList.contains(searchedWorkflowElement)) {
            if (originalWorkflowElement == null) {
                workflowElementList.remove(searchedWorkflowElement);
                return null;
            } else {
                WorkflowElementUtils.ChildUtils.removeChildElement(originalWorkflowElement, searchedWorkflowElement, true);
                return originalWorkflowElement;
            }
        }

        for (final WorkflowElement workflowElement : workflowElementList) {
            if (workflowElement == null)
                continue;

            final WorkflowElement parentWorkflowElement = removeOnNextLevel(WorkflowElementUtils.ChildUtils.getChildElementList(workflowElement), workflowElement, searchedWorkflowElement);
            if (parentWorkflowElement != null)
                return parentWorkflowElement;
        }

        return null;
    }
}
