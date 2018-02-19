package org.pcsoft.framework.jfex.toolbox;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.junit.Assert;
import org.junit.Test;
import org.pcsoft.framework.jftex.ExtendedApplicationTest;


public class ToolBoxTest extends ExtendedApplicationTest {
    private ToolBox toolBox;
    private Label pane1;
    private Label pane3;
    private Label pane2;

    @Override
    public void start(Stage stage) throws Exception {
        pane1 = buildLabel("Pane 1 Label");
        pane3 = buildLabel("Pane 3 Label");
        pane2 = buildLabel("Pane 2 Label");
        toolBox = new ToolBox(
                new ToolBoxDescriptor(1, "Pane 1", pane1),
                new ToolBoxDescriptor(3, "Pane 3", pane3),
                new ToolBoxDescriptor(2, "Pane 2", pane2)
        );
        toolBox.setSelectionMode(SelectionMode.MULTIPLE);
        toolBox.setOrientation(ToolBoxOrientation.LEFT);

        try {
            stage.initStyle(StageStyle.UTILITY);
        } catch (Exception e) {
            //Do nothing
        }
        stage.setScene(new Scene(toolBox, 800, 600));
        stage.setOpacity(0);
        stage.show();
    }

    private Label buildLabel(final String text) {
        final Label label = new Label(text);
        label.setAlignment(Pos.CENTER);
        label.setMaxWidth(Double.MAX_VALUE);
        label.setMaxHeight(Double.MAX_VALUE);

        return label;
    }

    @Test
    public void test() {
        Assert.assertNull(pane1.getParent());
        Assert.assertNull(pane2.getParent());
        Assert.assertNull(pane3.getParent());
        Assert.assertNull(toolBox.getSelectionModel().getSelectedItem());
        Assert.assertEquals(-1, toolBox.getSelectionModel().getSelectedIndex());

        changeCheckStateFX("#tool-button-1");
        Assert.assertNotNull(pane1.getParent());
        Assert.assertNull(pane2.getParent());
        Assert.assertNull(pane3.getParent());
        Assert.assertNotNull(toolBox.getSelectionModel().getSelectedItem());
        Assert.assertEquals(pane1, toolBox.getSelectionModel().getSelectedItem().getContent());
        Assert.assertEquals(0, toolBox.getSelectionModel().getSelectedIndex());
        Assert.assertEquals(1, toolBox.getSelectionModel().getSelectedIndices().size());
        Assert.assertEquals(1, toolBox.getSelectionModel().getSelectedItems().size());
        Assert.assertEquals(0, toolBox.getSelectionModel().getSelectedIndices().get(0).intValue());
        Assert.assertEquals(pane1, toolBox.getSelectionModel().getSelectedItems().get(0).getContent());

        changeCheckStateFX("#tool-button-3");
        Assert.assertNotNull(pane1.getParent());
        Assert.assertNull(pane2.getParent());
        Assert.assertNotNull(pane3.getParent());
        Assert.assertNotNull(toolBox.getSelectionModel().getSelectedItem());
        Assert.assertEquals(pane3, toolBox.getSelectionModel().getSelectedItem().getContent());
        Assert.assertEquals(2, toolBox.getSelectionModel().getSelectedIndex());
        Assert.assertEquals(2, toolBox.getSelectionModel().getSelectedIndices().size());
        Assert.assertEquals(2, toolBox.getSelectionModel().getSelectedItems().size());
        Assert.assertEquals(0, toolBox.getSelectionModel().getSelectedIndices().get(0).intValue());
        Assert.assertEquals(2, toolBox.getSelectionModel().getSelectedIndices().get(1).intValue());
        Assert.assertEquals(pane1, toolBox.getSelectionModel().getSelectedItems().get(0).getContent());
        Assert.assertEquals(pane3, toolBox.getSelectionModel().getSelectedItems().get(1).getContent());

        changeCheckStateFX("#tool-button-1");
        Assert.assertNotNull(pane1.getParent());//???
        Assert.assertNull(pane2.getParent());
        Assert.assertNotNull(pane3.getParent());
        Assert.assertNotNull(toolBox.getSelectionModel().getSelectedItem());
        Assert.assertEquals(pane3, toolBox.getSelectionModel().getSelectedItem().getContent());
        Assert.assertEquals(2, toolBox.getSelectionModel().getSelectedIndex());
        Assert.assertEquals(1, toolBox.getSelectionModel().getSelectedIndices().size());
        Assert.assertEquals(1, toolBox.getSelectionModel().getSelectedItems().size());
        Assert.assertEquals(2, toolBox.getSelectionModel().getSelectedIndices().get(0).intValue());
        Assert.assertEquals(pane3, toolBox.getSelectionModel().getSelectedItems().get(0).getContent());
    }
}
