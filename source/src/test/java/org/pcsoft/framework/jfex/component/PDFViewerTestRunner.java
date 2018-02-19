package org.pcsoft.framework.jfex.component;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class PDFViewerTestRunner extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        final PDFViewer pdfViewer = new PDFViewer();
        pdfViewer.loadDocument(getClass().getResourceAsStream("/test.pdf"));
        pdfViewer.setFilename("internal.pdf");

        primaryStage.setScene(new Scene(pdfViewer, 800, 600));
        primaryStage.show();
    }
}
