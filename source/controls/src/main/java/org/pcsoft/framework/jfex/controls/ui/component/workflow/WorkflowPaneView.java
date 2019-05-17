package org.pcsoft.framework.jfex.controls.ui.component.workflow;

import javafx.beans.InvalidationListener;
import javafx.beans.binding.DoubleExpression;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.MapChangeListener;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseButton;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import org.pcsoft.framework.jfex.controls.ui.component.workflow.component.ArrowLine;
import org.pcsoft.framework.jfex.controls.ui.component.workflow.component.WorkflowElementListView;
import org.pcsoft.framework.jfex.controls.ui.component.workflow.component.WorkflowElementPane;
import org.pcsoft.framework.jfex.controls.ui.component.workflow.component.WorkflowPropertyTable;
import org.pcsoft.framework.jfex.controls.ui.component.workflow.element.WorkflowForkElement;
import org.pcsoft.framework.jfex.controls.ui.component.workflow.element.WorkflowJoinElement;
import org.pcsoft.framework.jfex.controls.ui.component.workflow.history.Command;
import org.pcsoft.framework.jfex.controls.ui.component.workflow.listener.WorkflowChange;
import org.pcsoft.framework.jfex.controls.ui.component.workflow.listener.WorkflowChangedEvent;
import org.pcsoft.framework.jfex.controls.ui.component.workflow.type.*;
import org.pcsoft.framework.jfex.controls.ui.component.workflow.utils.CalculatorUtils;
import org.pcsoft.framework.jfex.controls.ui.component.workflow.utils.DraggingFormat;
import org.pcsoft.framework.jfex.mvvm.FxmlView;

import java.io.ByteArrayInputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.*;


public class WorkflowPaneView extends FxmlView<WorkflowPaneViewModel> {
    private static final int SPACE_X = 15;
    private static final int SPACE_Y = 25;

    @FXML
    private Accordion accProperties;
    @FXML
    private AnchorPane pnlArrow;
    @FXML
    private AnchorPane pnlDesigner;
    @FXML
    private Accordion accElements;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        viewModel.workflowComponentMapperMapProperty().addListener(
                (MapChangeListener<Class<? extends WorkflowElement>, WorkflowElementComponentData>) change -> updateElementPickerList()
        );
        viewModel.defaultExpandedElementGroupProperty().addListener(o -> expandElementPane());
        viewModel.getWorkflowElementList().addListener((ListChangeListener<WorkflowElement>) c -> updateElementDesignerList());
        viewModel.getSelectionModel().selectedItemProperty().addListener((v, o, n) -> {
            if (o != null) {
                pnlDesigner.getChildren().stream()
                        .filter(item -> item instanceof WorkflowElementPane)
                        .map(item -> (WorkflowElementPane) item)
                        .filter(item -> item.getWorkflowElement().equals(o))
                        .findFirst().ifPresent(item -> item.setSelected(false));
            }
            if (n != null) {
                pnlDesigner.getChildren().stream()
                        .filter(item -> item instanceof WorkflowElementPane)
                        .map(item -> (WorkflowElementPane) item)
                        .filter(item -> item.getWorkflowElement().equals(n))
                        .findFirst().ifPresent(item -> item.setSelected(true));
            }
            updateElementPropertyList(n);
        });

        pnlDesigner.setOnDragOver(e -> {
            if (e.getDragboard().hasContent(DraggingFormat.COMPONENT)) {
                e.acceptTransferModes(TransferMode.MOVE);
            }
        });
        pnlDesigner.setOnDragDropped(e -> {
            if (e.getDragboard().hasContent(DraggingFormat.COMPONENT)) {
                final WorkflowElementInfoDescriptor descriptor = (WorkflowElementInfoDescriptor) e.getDragboard().getContent(DraggingFormat.COMPONENT);
                try {
                    final WorkflowElement workflowElement = descriptor.getElementClass().getConstructor(double.class, double.class)
                            .newInstance(e.getX(), e.getY());
                    //History command execution
                    viewModel.getHistoryModel().execute(new Command() {
                        @Override
                        public void execute() {
                            viewModel.getWorkflowElementList().add(workflowElement);
                            fireWorkflowChanged(workflowElement, WorkflowChange.Add);
                        }

                        @Override
                        public void undo() {
                            viewModel.getWorkflowElementList().remove(workflowElement);
                            fireWorkflowChanged(workflowElement, WorkflowChange.Remove);
                        }
                    });
                } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e1) {
                    e1.printStackTrace();
                    throw new IllegalStateException("Unable to instantiate workflow element class: " + descriptor.getElementClass().getName(), e1);
                }
            }
        });

        updateElementPickerList();
        updateElementDesignerList();
    }

    private void updateElementDesignerList() {
        pnlDesigner.getChildren().clear();
        pnlArrow.getChildren().clear();

        viewModel.getWorkflowElementList().forEach(item -> buildWorkflowElementPane(item, null, new SimpleDoubleProperty(0), new SimpleDoubleProperty(0)));
    }

    private DoubleExpression buildWorkflowElementPane(final WorkflowElement workflowElement, final Pane parentWorkflowElementPane, final DoubleExpression x, final DoubleExpression y) {
        final WorkflowElementComponentData workflowElementComponentData = viewModel.getWorkflowComponentMapperMap().get(workflowElement.getClass());
        if (workflowElementComponentData == null)
            throw new IllegalStateException("Unable to find a component for workflow element class: " + workflowElement.getClass().getName());

        try {
            final WorkflowElementComponent workflowElementComponent = workflowElementComponentData.getComponentClass().getConstructor(workflowElement.getClass())
                    .newInstance(workflowElement);
            if (workflowElementComponentData.getComponentInitialization() != null) {
                workflowElementComponentData.getComponentInitialization().accept(workflowElementComponent);
            }
            final WorkflowElementPane workflowElementPane = new WorkflowElementPane(workflowElement, workflowElementComponent, pnlDesigner,
                    viewModel.getSelectionModel(), viewModel.getHistoryModel(), element -> viewModel.getWorkflowElementList().remove(element),
                    element -> viewModel.getWorkflowElementList().add(element), this::updateElementDesignerList);
            workflowElementPane.onWorkflowChangeListenerProperty().bind(viewModel.onWorkflowChangedListenerProperty());

            if (workflowElementPane.getLayoutX() < 0 && workflowElementPane.getLayoutY() < 0) {
                x.addListener((v, o, n) -> {
                    workflowElementPane.setLayoutX(n.doubleValue());
                });
                y.addListener((v, o, n) -> {
                    workflowElementPane.setLayoutY(n.doubleValue());
                });
            }

            pnlDesigner.getChildren().add(workflowElementPane);
            if (parentWorkflowElementPane != null) {
                final ArrowLine line = new ArrowLine();

                final InvalidationListener invalidationListener = o -> {
                    final Point2D[] points = CalculatorUtils.calculateStartEndPointBetweenPanes(
                            parentWorkflowElementPane.getBoundsInParent(), workflowElementPane.getBoundsInParent());

                    line.setStartX(points[0].getX());
                    line.setStartY(points[0].getY());
                    line.setEndX(points[1].getX());
                    line.setEndY(points[1].getY());
                };
                parentWorkflowElementPane.boundsInParentProperty().addListener(invalidationListener);
                workflowElementPane.boundsInParentProperty().addListener(invalidationListener);

                pnlArrow.getChildren().add(line);
            }

            if (workflowElement instanceof BasicWorkflowElement) {
                final BasicWorkflowElement simpleElement = (BasicWorkflowElement) workflowElement;
                if (simpleElement.getChildElement() != null) {
                    buildWorkflowElementPane(simpleElement.getChildElement(), workflowElementPane,
                            x, y.add(workflowElementPane.heightProperty()).add(SPACE_Y));
                }
            } else if (workflowElement instanceof WorkflowForkElement) {
                final WorkflowForkElement forkElement = (WorkflowForkElement) workflowElement;
                int counter = 0;
                DoubleExpression left = new SimpleDoubleProperty(0);
                for (final WorkflowElement childElement : forkElement.getChildElementList()) {
                    final DoubleExpression width = buildWorkflowElementPane(childElement, workflowElementPane,
                            x.add(left).add(counter * SPACE_X), y.add(workflowElementPane.heightProperty()).add(SPACE_Y));
                    left = left.add(width);
                    counter++;
                }
            } else if (workflowElement instanceof WorkflowJoinElement) {
                final WorkflowJoinElement joinElement = (WorkflowJoinElement) workflowElement;
                if (joinElement.getChildElement() != null) {
                    buildWorkflowElementPane(joinElement.getChildElement(), workflowElementPane,
                            x, y.add(workflowElementPane.heightProperty()).add(SPACE_Y));
                }
            } else
                throw new RuntimeException("Unknown workflow element: " + workflowElement.getClass().getName());

            return workflowElementPane.widthProperty();
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new IllegalStateException("Unable to instantiate workflow element component class: " + workflowElementComponentData.getComponentClass().getName(), e);
        }
    }

    private void updateElementPropertyList(final WorkflowElement workflowElement) {
        final Map<String, WorkflowPropertyTable> propertyListMap = new HashMap<>();

        accProperties.getPanes().clear();
        final List<TitledPane> titledPaneList = new ArrayList<>();
        if (workflowElement != null) {
            for (final WorkflowProperty property : (Collection<WorkflowProperty>) workflowElement.getElementPropertyList()) {
                if (!property.getEditorVisible())
                    continue;

                if (!propertyListMap.containsKey(property.getGroup())) {
                    final WorkflowPropertyTable workflowPropertyTable = new WorkflowPropertyTable();
                    workflowPropertyTable.historyModelProperty().bind(viewModel.historyModelProperty());
                    viewModel.workflowPropertyEditorMapProperty().addListener(
                            (MapChangeListener<Class<? extends WorkflowPropertyEditor>, WorkflowPropertyEditorData>) change -> syncPropertyEditorList(workflowPropertyTable)
                    );
                    syncPropertyEditorList(workflowPropertyTable);
                    final TitledPane titledPane = new TitledPane(property.getGroup(), workflowPropertyTable);
                    titledPaneList.add(titledPane);

                    propertyListMap.put(property.getGroup(), workflowPropertyTable);
                }

                final WorkflowPropertyTable workflowPropertyTable = propertyListMap.get(property.getGroup());
                workflowPropertyTable.getItems().add(property);
            }
        }

        titledPaneList.sort((o1, o2) -> o1.getText().compareTo(o2.getText()));
        accProperties.getPanes().setAll(titledPaneList);
        if (!accProperties.getPanes().isEmpty()) {
            final String defaultExpandedPropertyGroup = new WorkflowElementInfoDescriptor(workflowElement.getClass()).getDefaultExpandedPropertyGroup();
            if (defaultExpandedPropertyGroup == null || defaultExpandedPropertyGroup.trim().isEmpty()) {
                accProperties.setExpandedPane(accProperties.getPanes().get(0));
            } else {
                accProperties.setExpandedPane(
                        accProperties.getPanes().stream()
                                .filter(item -> item.getText().equals(defaultExpandedPropertyGroup))
                                .findFirst().orElse(accProperties.getPanes().get(0))
                );
            }
        }
    }

    private void syncPropertyEditorList(WorkflowPropertyTable workflowPropertyTable) {
        if (viewModel.getWorkflowPropertyEditorMap().entrySet().stream().anyMatch(item -> item == null))
            throw new IllegalStateException("Workflow Property Editor Map contains NULL value as data!");

        workflowPropertyTable.getWorkflowPropertyEditorMap().clear();
        for (final Map.Entry<Class<? extends WorkflowPropertyEditor>, WorkflowPropertyEditorData> entry : viewModel.getWorkflowPropertyEditorMap().entrySet()) {
            workflowPropertyTable.getWorkflowPropertyEditorMap().put(
                    new WorkflowPropertyEditorInfoDescriptor(entry.getKey()),
                    entry.getValue()
            );
        }
    }

    private void updateElementPickerList() {
        final Map<String, WorkflowElementListView> elementListMap = new HashMap<>();

        accElements.getPanes().clear();
        final List<TitledPane> titledPaneList = new ArrayList<>();
        for (final Class<? extends WorkflowElement> workflowElementClass : viewModel.getWorkflowComponentMapperMap().keySet()) {
            final WorkflowElementInfoDescriptor descriptor = new WorkflowElementInfoDescriptor(workflowElementClass);

            if (!elementListMap.containsKey(descriptor.getGroup())) {
                final WorkflowElementListView lstElement = createWorkflowElementListView();
                final TitledPane titledPane = new TitledPane(descriptor.getGroup(), lstElement);
                titledPaneList.add(titledPane);

                elementListMap.put(descriptor.getGroup(), lstElement);
            }

            elementListMap.get(descriptor.getGroup()).getItems().add(descriptor);
        }

        titledPaneList.sort((o1, o2) -> o1.getText().compareTo(o2.getText()));
        accElements.getPanes().setAll(titledPaneList);
        expandElementPane();
    }

    private void expandElementPane() {
        if (!accElements.getPanes().isEmpty()) {
            if (viewModel.getDefaultExpandedElementGroup() == null || viewModel.getDefaultExpandedElementGroup().trim().isEmpty()) {
                accElements.setExpandedPane(accElements.getPanes().get(0));
            } else {
                accElements.setExpandedPane(
                        accElements.getPanes().stream()
                                .filter(item -> item.getText().equals(viewModel.getDefaultExpandedElementGroup()))
                                .findFirst().orElse(accElements.getPanes().get(0))
                );
            }
        }
    }

    private WorkflowElementListView createWorkflowElementListView() {
        final WorkflowElementListView listView = new WorkflowElementListView();

        listView.setOnDragDetected(e -> {
            if (e.getButton() == MouseButton.PRIMARY && listView.getSelectionModel().getSelectedItem() != null) {
                final ClipboardContent content = new ClipboardContent();
                content.put(DraggingFormat.COMPONENT, listView.getSelectionModel().getSelectedItem());

                final Dragboard dragboard = listView.startDragAndDrop(TransferMode.MOVE);
                if (listView.getSelectionModel().getSelectedItem().getBigIcon() != null) {
                    dragboard.setDragView(new Image(new ByteArrayInputStream(listView.getSelectionModel().getSelectedItem().getBigIcon())));
                }
                dragboard.setContent(content);

                e.consume();
            }
        });

        return listView;
    }

    public void resetWorkflowElementPosition() {
        viewModel.workflowElementListProperty().stream().forEach(this::resetWorkflowElementPosition);
        updateElementDesignerList();
    }

    private void resetWorkflowElementPosition(final WorkflowElement workflowElement) {
        workflowElement.setX(-1);
        workflowElement.setY(-1);

        if (workflowElement instanceof BasicWorkflowElement) {
            final BasicWorkflowElement simpleElement = (BasicWorkflowElement) workflowElement;
            if (simpleElement.getChildElement() != null) {
                resetWorkflowElementPosition(simpleElement.getChildElement());
            }
        } else if (workflowElement instanceof WorkflowForkElement) {
            final WorkflowForkElement forkElement = (WorkflowForkElement) workflowElement;
            forkElement.getChildElementList().forEach(this::resetWorkflowElementPosition);
        } else if (workflowElement instanceof WorkflowJoinElement) {
            final WorkflowJoinElement joinElement = (WorkflowJoinElement) workflowElement;
            if (joinElement.getChildElement() != null) {
                resetWorkflowElementPosition(joinElement.getChildElement());
            }
        } else
            throw new RuntimeException("Unknown workflow element: " + workflowElement.getClass().getName());
    }

    protected void fireWorkflowChanged(final WorkflowElement workflowElement, final WorkflowChange workflowChange) {
        if (viewModel.getOnWorkflowChangedListener() != null) {
            viewModel.getOnWorkflowChangedListener().accept(
                    new WorkflowChangedEvent(WorkflowPane.EVENT_WORKFLOW_CHANGED, workflowElement, workflowChange)
            );
        }
    }
}
