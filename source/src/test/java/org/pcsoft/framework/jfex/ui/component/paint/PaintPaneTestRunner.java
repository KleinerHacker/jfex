package org.pcsoft.framework.jfex.ui.component.paint;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class PaintPaneTestRunner extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        final PaintPane paintPane = new PaintPane();
        paintPane.setImageChooserCallback(() -> new Image(getClass().getResourceAsStream("/icons/ic_clear16.png")));

        primaryStage.setScene(new Scene(paintPane, 800, 600));
        primaryStage.show();
    }
}
