package org.pcsoft.framework.jfex.ui.component;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.pcsoft.framework.jfex.type.ChooserType;


public class FileChooserPaneStartup extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        final FileChooserPane fileChooserPane = new FileChooserPane();
        fileChooserPane.setStoreLastDirectory(true);
        fileChooserPane.setStoreLastDirectoryKey("file");

        final FileChooserPane directoryChooserPane = new FileChooserPane();
        directoryChooserPane.setStoreLastDirectory(true);
        directoryChooserPane.setStoreLastDirectoryKey("dir");
        directoryChooserPane.setDialogType(ChooserType.Directory);
        directoryChooserPane.setViewType(FileChooserPane.ViewType.WithLabel);
        directoryChooserPane.setClearFileAvailable(false);

        primaryStage.initStyle(StageStyle.UTILITY);
        primaryStage.setScene(new Scene(new VBox(5, fileChooserPane, directoryChooserPane), 800, 600));
        primaryStage.show();
    }
}
