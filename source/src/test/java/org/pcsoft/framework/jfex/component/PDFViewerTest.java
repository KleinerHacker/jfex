package org.pcsoft.framework.jfex.component;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.junit.Assert;
import org.junit.Test;
import org.pcsoft.framework.jftex.ExtendedApplicationTest;
import org.testfx.api.FxAssert;
import org.testfx.matcher.base.NodeMatchers;

import java.io.IOException;


public class PDFViewerTest extends ExtendedApplicationTest {

    private PDFViewer pdfViewer;

    @Override
    public void start(Stage stage) throws Exception {
        try {
            stage.initStyle(StageStyle.UTILITY);
        } catch (Exception e) {
            //Do nothing
        }
        pdfViewer = new PDFViewer();
        stage.setScene(new Scene(pdfViewer, 800, 600));
        stage.setOpacity(0);
        stage.show();
    }

    @Test
    public void test() throws IOException {
        FxAssert.verifyThat("#btnSave", NodeMatchers.isDisabled());

        Platform.runLater(() -> {
            try {
                pdfViewer.loadDocument(getClass().getResourceAsStream("/test.pdf"));
            } catch (IOException e) {
                Assert.fail(e.getMessage());
            }
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        FxAssert.verifyThat("#btnSave", NodeMatchers.isEnabled());

        Platform.runLater(() -> pdfViewer.closeDocument());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        FxAssert.verifyThat("#btnSave", NodeMatchers.isDisabled());
    }
}