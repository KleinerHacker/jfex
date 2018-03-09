package org.pcsoft.framework.jfex.ui.component.workflow.component;

import javafx.beans.property.*;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.control.SelectionModel;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseButton;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import org.pcsoft.framework.jfex.ui.component.workflow.WorkflowPane;
import org.pcsoft.framework.jfex.ui.component.workflow.history.Command;
import org.pcsoft.framework.jfex.ui.component.workflow.history.HistoryModel;
import org.pcsoft.framework.jfex.ui.component.workflow.listener.WorkflowChange;
import org.pcsoft.framework.jfex.ui.component.workflow.listener.WorkflowChangedEvent;
import org.pcsoft.framework.jfex.ui.component.workflow.type.WorkflowElement;
import org.pcsoft.framework.jfex.ui.component.workflow.type.WorkflowElementComponent;
import org.pcsoft.framework.jfex.ui.component.workflow.utils.DraggingFormat;
import org.pcsoft.framework.jfex.ui.component.workflow.utils.MouseDragging;
import org.pcsoft.framework.jfex.ui.component.workflow.utils.WorkflowElementUtils;

import java.util.function.Consumer;


public class WorkflowElementPane extends BorderPane {
    private static final String STYLE_SELECTED_BORDER = "-fx-border-color: black; -fx-border-width: 1px; -fx-border-radius: 3px; -fx-border-style: dotted; -fx-background-insets: 1px; -fx-background-color: skyblue; -fx-background-radius: 3px";

    private final BooleanProperty selected = new SimpleBooleanProperty(false);

    private final ReadOnlyObjectProperty<WorkflowElement> workflowElement;

    private final ObjectProperty<Consumer<WorkflowChangedEvent>> onWorkflowChangeListener = new SimpleObjectProperty<>();

    private final Pane selectionPane;

    private MouseDragging mouseDragging = null;
    private static WorkflowElement draggedWorkflowElement = null;

    public WorkflowElementPane(final WorkflowElement workflowElement, final WorkflowElementComponent workflowElementComponent, final Pane pnlDesigner,
                               final SelectionModel<WorkflowElement> selectionModel, final HistoryModel historyModel,
                               final Consumer<WorkflowElement> elementRemoveConsumer, final Consumer<WorkflowElement> elementAddConsumer, final Runnable updater) {
        this.workflowElement = new ReadOnlyObjectWrapper<>(workflowElement).getReadOnlyProperty();

        selectionPane = new Pane();
        selectionPane.setStyle(STYLE_SELECTED_BORDER);
        selectionPane.setOpacity(0.3);
        selectionPane.visibleProperty().bind(selected);

        this.setCenter(new StackPane((Node) workflowElementComponent, selectionPane));
        this.getCenter().setMouseTransparent(true);
        this.layoutXProperty().bindBidirectional(workflowElement.xProperty());
        this.layoutYProperty().bindBidirectional(workflowElement.yProperty());
        this.setOnMousePressed(e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                if (e.isControlDown()) {
                    if (selectionModel.getSelectedItem().equals(workflowElement)) {
                        selectionModel.select(null);
                    }
                } else {
                    selectionModel.select(workflowElementComponent.getWorkflowElement());
                }
                if (e.getClickCount() == 1) {
                    mouseDragging = new MouseDragging(-e.getX(), -e.getY());
                }
            }
        });
        this.setOnMouseReleased(e -> {
            mouseDragging = null;
        });
        this.setOnMouseDragged(e -> {
            if (mouseDragging != null && e.getButton() == MouseButton.PRIMARY) {
                final Point2D p = pnlDesigner.sceneToLocal(e.getSceneX(), e.getSceneY(), true);
                this.setLayoutX(Math.max(0, mouseDragging.calculateX(p.getX())));
                this.setLayoutY(Math.max(0, mouseDragging.calculateY(p.getY())));
            }
        });
        this.setOnDragDetected(e -> {
            if (e.getButton() == MouseButton.SECONDARY) {
                final ClipboardContent content = new ClipboardContent();
                content.put(DraggingFormat.ELEMENT, "");

                final Dragboard dragboard = this.startDragAndDrop(TransferMode.LINK);
                dragboard.setContent(content);
                draggedWorkflowElement = workflowElement;

                e.consume();
            }
        });
        this.setOnDragOver(e -> {
            if (e.getDragboard().hasContent(DraggingFormat.ELEMENT) && !workflowElement.equals(draggedWorkflowElement)) {
                e.acceptTransferModes(TransferMode.LINK);
            }
        });
        this.setOnDragDropped(e -> {
            if (e.getDragboard().hasContent(DraggingFormat.ELEMENT) && !workflowElement.equals(draggedWorkflowElement)) {
                //History call
                historyModel.execute(new Command() {
                    private final WorkflowElement draggedWorkflowElement = WorkflowElementPane.draggedWorkflowElement;

                    private WorkflowElement oldParentElement = null;
                    private WorkflowElement oldChildElement = null;

                    @Override
                    public void execute() {
                        //Element is in main list (new link)?
                        if (!WorkflowElementUtils.ParentUtils.hasParentElement(workflowElement)) {
                            //remove from top list
                            elementRemoveConsumer.accept(workflowElement);
                        } else {
                            oldParentElement = WorkflowElementUtils.ParentUtils.getParentElement(workflowElement);
                        }

                        //set new child
                        final WorkflowElement oldChild = WorkflowElementUtils.ChildUtils.setChildElement(draggedWorkflowElement, workflowElement, true);

                        //Old child was set (kill link)?
                        if (oldChild != null) {
                            //Set back to top list
                            elementAddConsumer.accept(oldChild);
                            oldChildElement = oldChild;
                            fireWorkflowChanged(oldChild, WorkflowChange.ParentChanged);
                        }

                        //Events
                        fireWorkflowChanged(draggedWorkflowElement, WorkflowChange.ChildChanged);
                        fireWorkflowChanged(workflowElement, WorkflowChange.ParentChanged);

                        updater.run();
                    }

                    @Override
                    public void undo() {
                        //Old child was set (relink)
                        if (oldChildElement != null) {
                            //Remove from top level list
                            elementRemoveConsumer.accept(oldChildElement);
                            //Set to parent
                            WorkflowElementUtils.ChildUtils.setChildElement(draggedWorkflowElement, oldChildElement, true);
                            //Event
                            fireWorkflowChanged(oldChildElement, WorkflowChange.ParentChanged);
                        } else {
                            //Remove child
                            WorkflowElementUtils.ChildUtils.removeChildElement(draggedWorkflowElement, workflowElement, true);
                        }

                        //Element was on in top level list
                        if (oldParentElement != null) {
                            WorkflowElementUtils.ParentUtils.setParentElement(workflowElement, oldParentElement, true);
                        } else {
                            elementAddConsumer.accept(workflowElement);
                        }

                        //Events
                        fireWorkflowChanged(draggedWorkflowElement, WorkflowChange.ChildChanged);
                        fireWorkflowChanged(workflowElement, WorkflowChange.ParentChanged);

                        updater.run();
                    }
                });
                draggedWorkflowElement = null;
            }
        });
    }

    public Consumer<WorkflowChangedEvent> getOnWorkflowChangeListener() {
        return onWorkflowChangeListener.get();
    }

    public ObjectProperty<Consumer<WorkflowChangedEvent>> onWorkflowChangeListenerProperty() {
        return onWorkflowChangeListener;
    }

    public void setOnWorkflowChangeListener(Consumer<WorkflowChangedEvent> onWorkflowChangeListener) {
        this.onWorkflowChangeListener.set(onWorkflowChangeListener);
    }

    public WorkflowElement getWorkflowElement() {
        return workflowElement.get();
    }

    public ReadOnlyObjectProperty<WorkflowElement> workflowElementProperty() {
        return workflowElement;
    }

    public boolean getSelected() {
        return selected.get();
    }

    public BooleanProperty selectedProperty() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected.set(selected);
    }

    protected void fireWorkflowChanged(final WorkflowElement workflowElement, final WorkflowChange workflowChange) {
        if (onWorkflowChangeListener.get() != null) {
            onWorkflowChangeListener.get().accept(new WorkflowChangedEvent(WorkflowPane.EVENT_WORKFLOW_CHANGED, workflowElement, workflowChange));
        }
    }
}
