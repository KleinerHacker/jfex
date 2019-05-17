package org.pcsoft.framework.jfex.controls.ui.component;

import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.junit.Test;
import org.pcsoft.framework.jftex.ExtendedApplicationTest;
import org.testfx.api.FxAssert;
import org.testfx.matcher.base.NodeMatchers;
import org.testfx.matcher.control.LabeledMatchers;
import org.testfx.matcher.control.TextFlowMatchers;


public class TooltipDetailPaneTest extends ExtendedApplicationTest {

    @Override
    public void start(Stage stage) throws Exception {
        try {
            stage.initStyle(StageStyle.UTILITY);
        } catch (Exception e) {
            //Do nothing
        }
        stage.setTitle("Tooltip Detail Pane Test");
        stage.setResizable(false);
        stage.setOpacity(0);

        final TooltipDetailPane tooltipDetailPane = new TooltipDetailPane();
        tooltipDetailPane.setTitle("Test");
        tooltipDetailPane.setDescription("Description");

        stage.setScene(new Scene(tooltipDetailPane));
        stage.show();
    }

    @Test
    public void test() {
        FxAssert.verifyThat("#lblTitle", LabeledMatchers.hasText("Test"));
        FxAssert.verifyThat("#lblDescription", LabeledMatchers.hasText("Description"));
    }
}