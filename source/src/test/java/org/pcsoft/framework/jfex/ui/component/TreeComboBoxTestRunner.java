package org.pcsoft.framework.jfex.ui.component;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.stage.Stage;


public class TreeComboBoxTestRunner extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        final TreeComboBox<String> treeComboBox = new TreeComboBox<>();
        final TreeItem<String> root = new TreeItem<>("root");
        final TreeItem<String> child1 = new TreeItem<>("child1");
        final TreeItem<String> child2  = new TreeItem<>("child2");
        final TreeItem<String> subChild1 = new TreeItem<>("subChild1");

        root.getChildren().addAll(child1, child2);
        child1.getChildren().add(subChild1);

        treeComboBox.setRoot(root);

        primaryStage.setScene(new Scene(treeComboBox, 300, 50));
        primaryStage.show();
    }
}
