package org.pcsoft.framework.jfex.ui.component.workflow;

import com.sun.javafx.collections.ObservableListWrapper;
import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.Observable;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.SingleSelectionModel;
import org.pcsoft.framework.jfex.ui.component.workflow.editor.*;
import org.pcsoft.framework.jfex.ui.component.workflow.element.WorkflowForkElement;
import org.pcsoft.framework.jfex.ui.component.workflow.element.WorkflowForkElementComponent;
import org.pcsoft.framework.jfex.ui.component.workflow.element.WorkflowJoinElement;
import org.pcsoft.framework.jfex.ui.component.workflow.element.WorkflowJoinElementComponent;
import org.pcsoft.framework.jfex.ui.component.workflow.history.HistoryModel;
import org.pcsoft.framework.jfex.ui.component.workflow.listener.WorkflowChangedEvent;
import org.pcsoft.framework.jfex.ui.component.workflow.type.*;

import java.util.ArrayList;
import java.util.function.Consumer;


public class WorkflowPaneViewModel implements ViewModel {
    private final ObjectProperty<SelectionModel<WorkflowElement>> selectionModel = new SimpleObjectProperty<>(new SingleSelectionModel<WorkflowElement>() {
        @Override
        protected WorkflowElement getModelItem(int index) {
            return index < 0 || index >= workflowElementList.size() ? null : workflowElementList.get(index);
        }

        @Override
        protected int getItemCount() {
            return workflowElementList.getSize();
        }
    });
    private final ObjectProperty<HistoryModel> historyModel = new SimpleObjectProperty<>(new HistoryModel());

    private final ReadOnlyMapProperty<Class<? extends WorkflowElement>, WorkflowElementComponentData> workflowComponentMapperMap =
            new ReadOnlyMapWrapper<Class<? extends WorkflowElement>, WorkflowElementComponentData>(FXCollections.observableHashMap())
                    .getReadOnlyProperty();
    private final ReadOnlyMapProperty<Class<? extends WorkflowPropertyEditor>, WorkflowPropertyEditorData> workflowPropertyEditorMap =
            new ReadOnlyMapWrapper<Class<? extends WorkflowPropertyEditor>, WorkflowPropertyEditorData>(FXCollections.observableHashMap()).getReadOnlyProperty();
    private final ReadOnlyListProperty<WorkflowElement> workflowElementList =
            new ReadOnlyListWrapper<>(buildWorkflowElementList()).getReadOnlyProperty();

    private final StringProperty defaultExpandedElementGroup = new SimpleStringProperty();
    private final ObjectProperty<Consumer<WorkflowChangedEvent>> onWorkflowChangedListener = new SimpleObjectProperty<>();

    public WorkflowPaneViewModel() {
        //Add default workflow elements
        workflowComponentMapperMap.put(WorkflowForkElement.class, new WorkflowElementComponentData(WorkflowForkElementComponent.class));
        workflowComponentMapperMap.put(WorkflowJoinElement.class, new WorkflowElementComponentData(WorkflowJoinElementComponent.class));

        //Add default workflow property editors
        workflowPropertyEditorMap.put(WorkflowStringPropertyEditor.class, new WorkflowPropertyEditorData());
        workflowPropertyEditorMap.put(WorkflowIntegerPropertyEditor.class, new WorkflowPropertyEditorData());
        workflowPropertyEditorMap.put(WorkflowDoublePropertyEditor.class, new WorkflowPropertyEditorData());
        workflowPropertyEditorMap.put(WorkflowBooleanPropertyEditor.class, new WorkflowPropertyEditorData());
        workflowPropertyEditorMap.put(WorkflowCharacterPropertyEditor.class, new WorkflowPropertyEditorData());
        workflowPropertyEditorMap.put(WorkflowColorPropertyEditor.class, new WorkflowPropertyEditorData());
        workflowPropertyEditorMap.put(WorkflowByteArrayPropertyEditor.class, new WorkflowPropertyEditorData());
        workflowPropertyEditorMap.put(WorkflowEnumerationPropertyEditor.class, new WorkflowPropertyEditorData());
    }

    public Consumer<WorkflowChangedEvent> getOnWorkflowChangedListener() {
        return onWorkflowChangedListener.get();
    }

    public ObjectProperty<Consumer<WorkflowChangedEvent>> onWorkflowChangedListenerProperty() {
        return onWorkflowChangedListener;
    }

    public void setOnWorkflowChangedListener(Consumer<WorkflowChangedEvent> onWorkflowChangedListener) {
        this.onWorkflowChangedListener.set(onWorkflowChangedListener);
    }

    public ObservableMap<Class<? extends WorkflowElement>, WorkflowElementComponentData> getWorkflowComponentMapperMap() {
        return workflowComponentMapperMap.get();
    }

    public ReadOnlyMapProperty<Class<? extends WorkflowElement>, WorkflowElementComponentData> workflowComponentMapperMapProperty() {
        return workflowComponentMapperMap;
    }

    public ObservableList<WorkflowElement> getWorkflowElementList() {
        return workflowElementList.get();
    }

    public ReadOnlyListProperty<WorkflowElement> workflowElementListProperty() {
        return workflowElementList;
    }

    public ObservableMap<Class<? extends WorkflowPropertyEditor>, WorkflowPropertyEditorData> getWorkflowPropertyEditorMap() {
        return workflowPropertyEditorMap.get();
    }

    public ReadOnlyMapProperty<Class<? extends WorkflowPropertyEditor>, WorkflowPropertyEditorData> workflowPropertyEditorMapProperty() {
        return workflowPropertyEditorMap;
    }

    public SelectionModel<WorkflowElement> getSelectionModel() {
        return selectionModel.get();
    }

    public ObjectProperty<SelectionModel<WorkflowElement>> selectionModelProperty() {
        return selectionModel;
    }

    public void setSelectionModel(SelectionModel<WorkflowElement> selectionModel) {
        this.selectionModel.set(selectionModel);
    }

    public HistoryModel getHistoryModel() {
        return historyModel.get();
    }

    public ObjectProperty<HistoryModel> historyModelProperty() {
        return historyModel;
    }

    public void setHistoryModel(HistoryModel historyModel) {
        this.historyModel.set(historyModel);
    }

    public String getDefaultExpandedElementGroup() {
        return defaultExpandedElementGroup.get();
    }

    public StringProperty defaultExpandedElementGroupProperty() {
        return defaultExpandedElementGroup;
    }

    public void setDefaultExpandedElementGroup(String defaultExpandedElementGroup) {
        this.defaultExpandedElementGroup.set(defaultExpandedElementGroup);
    }

    private static ObservableList<WorkflowElement> buildWorkflowElementList() {
        return new ObservableListWrapper<>(new ArrayList<>(), o -> {
            if (o instanceof BasicWorkflowElement) {
                final BasicWorkflowElement simpleElement = (BasicWorkflowElement) o;
                return new Observable[]{simpleElement.childElementProperty(), simpleElement.parentElementProperty()};
            }

            return new Observable[]{};
        });
    }
}
