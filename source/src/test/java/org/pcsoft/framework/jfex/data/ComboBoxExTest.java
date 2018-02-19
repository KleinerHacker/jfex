package org.pcsoft.framework.jfex.data;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.junit.Assert;
import org.junit.Test;
import org.pcsoft.framework.jftex.ExtendedApplicationTest;

import java.util.Arrays;


public class ComboBoxExTest extends ExtendedApplicationTest {
    private ComboBoxEx<String, String> comboBoxEx;
    private final StringProperty value = new SimpleStringProperty("Welt");

    @Override
    public void start(Stage stage) throws Exception {
        comboBoxEx = new ComboBoxEx<>();
        comboBoxEx.setItemLoader(() -> {
            Thread.sleep(1000);
            return Arrays.asList("Hallo", "Welt", "Bitte", "Nicht", "Stören", "Kanu", "Fahren", "Ist", "Schön", "Keiner", "Kann", "Was");
        });
        comboBoxEx.setHeaderKeyExtractor(s -> s.substring(0, 1));
        comboBoxEx.setValueComparator(String::compareTo);
        comboBoxEx.setHeaderComparator(String::compareTo);
        comboBoxEx.setValueCellRendererCallback((cell, item, empty) -> {
            cell.setStyle(null);
            if (!empty && item != null) {
                cell.setText(item);
            }
        });
        comboBoxEx.setHeaderCellRendererCallback((cell, item, empty) -> {
            cell.setStyle("-fx-font-weight: bold");
            if (!empty && item != null) {
                cell.setText(item);
            }
        });
        comboBoxEx.setValueButtonCellRendererCallback((cell, item, empty) -> {
            cell.setStyle(null);
            if (!empty && item != null) {
                cell.setText(item);
            }
        });
        comboBoxEx.valueProperty().bindBidirectional(value);
        comboBoxEx.refresh();

        try {
            stage.initStyle(StageStyle.UTILITY);
        } catch (Exception ignore) {
        }
        stage.setScene(new Scene(comboBoxEx, 500, 100));
        stage.setOpacity(0);
        stage.show();
    }

    @Test
    public void test() throws Exception {
        Thread.sleep(1500);

        Assert.assertEquals("Welt", value.get());
        Assert.assertEquals("Welt", comboBoxEx.getValue());

        Platform.runLater(() -> value.set("Hallo"));
        Thread.sleep(100);

        Assert.assertEquals("Hallo", value.get());
        Assert.assertEquals("Hallo", comboBoxEx.getValue());

        Platform.runLater(() -> comboBoxEx.refresh());
        Thread.sleep(1500);

        Assert.assertEquals("Hallo", value.get());
        Assert.assertEquals("Hallo", comboBoxEx.getValue());
    }
}