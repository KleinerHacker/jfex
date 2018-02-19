package org.pcsoft.framework.jfex.workflow;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import org.pcsoft.framework.jfex.workflow.element.WorkflowProcessElement;
import org.pcsoft.framework.jfex.workflow.element.WorkflowProcessElementComponent;
import org.pcsoft.framework.jfex.workflow.element.WorkflowTestElement;
import org.pcsoft.framework.jfex.workflow.element.WorkflowTestElementComponent;
import org.pcsoft.framework.jfex.workflow.type.WorkflowElementComponentData;


public class TestRunner extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        final WorkflowPane workflowPane = new WorkflowPane();
        workflowPane.getWorkflowComponentMapperMap().put(WorkflowProcessElement.class, new WorkflowElementComponentData(WorkflowProcessElementComponent.class));
        workflowPane.getWorkflowComponentMapperMap().put(WorkflowTestElement.class, new WorkflowElementComponentData(WorkflowTestElementComponent.class));
        workflowPane.setDefaultExpandedElementGroup("Test");
        workflowPane.addWorkflowChangedListener(e -> System.out.println("Model has changed: " + e.toString()));

        stage.setScene(new Scene(workflowPane, 1280, 1024));
        stage.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
            if (e.getCode() == KeyCode.DELETE) {
                if (workflowPane.getSelectionModel().getSelectedItem() != null) {
                    workflowPane.removeSelectedWorkflowElement();
                }

                e.consume();
            } else if (e.getCode() == KeyCode.Z && e.isControlDown()) {
                if (!workflowPane.getHistoryModel().undo()) {
                    System.out.println("Unable to undo");
                }
                e.consume();
            } else if (e.getCode() == KeyCode.Y && e.isControlDown()) {
                if (!workflowPane.getHistoryModel().redo()) {
                    System.out.println("Unable to redo");
                }
                e.consume();
            } else if (e.getCode() == KeyCode.SPACE && e.isControlDown() && e.isAltDown() && e.isShiftDown()) {
                workflowPane.resetWorkflowElementPosition();
                e.consume();
            }
        });
        stage.show();
    }
}
