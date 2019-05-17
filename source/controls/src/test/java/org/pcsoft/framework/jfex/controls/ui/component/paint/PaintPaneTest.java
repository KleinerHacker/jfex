package org.pcsoft.framework.jfex.controls.ui.component.paint;

import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.junit.Test;
import org.pcsoft.framework.jftex.ExtendedApplicationTest;


public class PaintPaneTest extends ExtendedApplicationTest {

    @Override
    public void start(Stage stage) throws Exception {
        try {
            stage.initStyle(StageStyle.UTILITY);
        } catch (Exception e) {
            //Do nothing
        }
        stage.setTitle("Paint Pane Test");
        stage.setResizable(false);
        stage.setOpacity(0);

        stage.setScene(new Scene(new PaintPane()));
        stage.show();
    }

    @Test
    public void test() {

    }
}