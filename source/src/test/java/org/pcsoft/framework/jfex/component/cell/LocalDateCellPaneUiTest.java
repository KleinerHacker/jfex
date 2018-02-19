package org.pcsoft.framework.jfex.component.cell;

import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import java.time.LocalDate;


public class LocalDateCellPaneUiTest extends ApplicationTest {

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(new LocalDateCellPane(LocalDate.now())));
        stage.setOpacity(0);
        stage.show();
    }

    @Test
    public void test() {
        //Empty
    }
}
