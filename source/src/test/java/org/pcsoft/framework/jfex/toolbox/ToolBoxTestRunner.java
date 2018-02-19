package org.pcsoft.framework.jfex.toolbox;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class ToolBoxTestRunner extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        final FXMLLoader loader = new FXMLLoader();
        final Pane pane = loader.load(getClass().getResourceAsStream("/ToolBoxTest.fxml"));

        primaryStage.initStyle(StageStyle.UTILITY);
        primaryStage.setScene(new Scene(pane, 800, 600));
        primaryStage.show();
    }
}
