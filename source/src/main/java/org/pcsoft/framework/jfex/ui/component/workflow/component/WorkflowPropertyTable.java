package org.pcsoft.framework.jfex.ui.component.workflow.component;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import org.pcsoft.framework.jfex.ui.component.workflow.history.HistoryModel;
import org.pcsoft.framework.jfex.ui.component.workflow.type.WorkflowProperty;
import org.pcsoft.framework.jfex.ui.component.workflow.type.WorkflowPropertyEditor;
import org.pcsoft.framework.jfex.ui.component.workflow.type.WorkflowPropertyEditorData;
import org.pcsoft.framework.jfex.ui.component.workflow.type.WorkflowPropertyEditorInfoDescriptor;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.Collectors;


public class WorkflowPropertyTable extends TableView<WorkflowProperty> {
    private final ReadOnlyMapProperty<WorkflowPropertyEditorInfoDescriptor, WorkflowPropertyEditorData> workflowPropertyEditorMap =
            new ReadOnlyMapWrapper<WorkflowPropertyEditorInfoDescriptor, WorkflowPropertyEditorData>(FXCollections.observableHashMap()).getReadOnlyProperty();
    private final ObjectProperty<HistoryModel> historyModel = new SimpleObjectProperty<>(new HistoryModel());

    public WorkflowPropertyTable() {
        setEditable(true);

        final TableColumn<WorkflowProperty, Object> columnIcon = new TableColumn<>();
        columnIcon.setMinWidth(35);
        columnIcon.setPrefWidth(35);
        columnIcon.setMaxWidth(35);
        columnIcon.setResizable(false);
        columnIcon.setEditable(false);
        columnIcon.setSortable(false);
        columnIcon.setCellFactory(param1 -> new TableCell<WorkflowProperty, Object>() {
            @Override
            protected void updateItem(Object item, boolean empty) {
                super.updateItem(item, empty);

                setText(null);
                setGraphic(null);
                if (getTableRow() != null && getTableRow().getItem() != null && item != null && !empty) {
                    setGraphic(new ImageView(((WorkflowProperty) getTableRow().getItem()).getIcon()));
                }
            }
        });
        getColumns().add(columnIcon);

        final TableColumn<WorkflowProperty, String> columnName = new TableColumn<>("Name");
        columnName.setMinWidth(100);
        columnName.setPrefWidth(150);
        columnName.setMaxWidth(5000);
        columnName.setEditable(false);
        columnName.setSortable(true);
        columnName.setCellValueFactory(param -> param.getValue().nameProperty());
        getColumns().add(columnName);

        final TableColumn<WorkflowProperty, Property> columnValue = new TableColumn<>("Value");
        columnValue.setMinWidth(100);
        columnValue.setPrefWidth(150);
        columnValue.setMaxWidth(5000);
        columnValue.setEditable(true);
        columnValue.setSortable(false);
        columnValue.setCellValueFactory(param -> param.getValue().propertyProperty());
        columnValue.setCellFactory(param -> new TableCell<WorkflowProperty, Property>() {
            @Override
            protected void updateItem(Property item, boolean empty) {
                super.updateItem(item, empty);

                if (getGraphic() instanceof WorkflowPropertyEditor) {
                    historyModel.get().unbindProperty(((WorkflowPropertyEditor)getGraphic()).valueProperty());
                }

                setText(null);
                setGraphic(null);
                if (getTableRow() != null && getTableRow().getItem() != null && item != null && !empty) {
                    Node node;
                    //Find any editor for this property
                    if (workflowPropertyEditorMap.keySet().stream().anyMatch(v -> v.getPropertyClass().isAssignableFrom(item.getClass()))) {
                        final List<WorkflowPropertyEditorInfoDescriptor> filteredList = workflowPropertyEditorMap.keySet().stream()
                                .filter(v -> v.getPropertyClass().isAssignableFrom(item.getClass()))
                                .collect(Collectors.toList());

                        final WorkflowPropertyEditorInfoDescriptor descriptor;
                        if (filteredList.stream().anyMatch(v -> v.getInnerPropertyClass() != null && item.getValue() != null &&
                                v.getInnerPropertyClass().isAssignableFrom(item.getValue().getClass()))) {
                            //Use specified editor for property
                            descriptor = filteredList.stream().filter(v -> v.getInnerPropertyClass().isAssignableFrom(item.getValue().getClass())).findFirst().get();
                        } else if (filteredList.stream().anyMatch(v -> v.getInnerPropertyClass() == null)) {
                            //Use unspecified editor for property
                            descriptor = filteredList.stream().filter(v -> v.getInnerPropertyClass() == null).findFirst().get();
                        } else {
                            //Use no descriptor
                            descriptor = null;
                        }

                        if (descriptor == null) {
                            node = new Label("UNKNOWN");
                        } else {
                            try {
                                final WorkflowPropertyEditor editor = (WorkflowPropertyEditor) descriptor.getEditorClass().newInstance();
                                if (workflowPropertyEditorMap.get(descriptor).getEditorInitialization() != null) {
                                    workflowPropertyEditorMap.get(descriptor).getEditorInitialization().accept(editor);
                                }
                                editor.valueProperty().bindBidirectional(item);
                                historyModel.get().bindProperty(editor.valueProperty());

                                node = (Node) editor;
                            } catch (InstantiationException | IllegalAccessException e) {
                                try {
                                    final WorkflowPropertyEditor editor = (WorkflowPropertyEditor) descriptor.getEditorClass().getConstructor(Class.class)
                                            .newInstance(item.getValue() == null ? null : item.getValue().getClass());
                                    editor.valueProperty().bindBidirectional(item);
                                    historyModel.get().bindProperty(editor.valueProperty());

                                    node = (Node) editor;
                                } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e1) {
                                    throw new IllegalStateException("Unable to instantiate editor class: " + descriptor.getEditorClass().getName(), e1);
                                }
                            }
                        }
                    } else {
                        node = new Label("UNKNOWN");
                    }

                    node.setDisable(!((WorkflowProperty) getTableRow().getItem()).getEditorEditable());
                    setGraphic(node);
                }
            }
        });
        getColumns().add(columnValue);
    }

    public ObservableMap<WorkflowPropertyEditorInfoDescriptor, WorkflowPropertyEditorData> getWorkflowPropertyEditorMap() {
        return workflowPropertyEditorMap.get();
    }

    public ReadOnlyMapProperty<WorkflowPropertyEditorInfoDescriptor, WorkflowPropertyEditorData> workflowPropertyEditorMapProperty() {
        return workflowPropertyEditorMap;
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
}
