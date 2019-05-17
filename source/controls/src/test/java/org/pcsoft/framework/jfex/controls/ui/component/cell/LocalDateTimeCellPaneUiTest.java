package org.pcsoft.framework.jfex.controls.ui.component.cell;

import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import java.time.LocalDateTime;


public class LocalDateTimeCellPaneUiTest extends ApplicationTest {

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(new LocalDateTimeCellPane(LocalDateTime.now())));
        stage.setOpacity(0);
        stage.show();
    }

    @Test
    public void test() {
        //Empty
    }
}
