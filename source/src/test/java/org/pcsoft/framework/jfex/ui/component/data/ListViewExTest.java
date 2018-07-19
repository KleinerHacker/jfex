package org.pcsoft.framework.jfex.ui.component.data;

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
import java.util.Comparator;


public class ListViewExTest extends ExtendedApplicationTest {
    private ListViewEx<String, String> listViewEx;
    private final StringProperty value = new SimpleStringProperty();

    @Override
    public void start(Stage stage) throws Exception {
        listViewEx = new ListViewEx<>();
        listViewEx.setOnItemsLoaded(() -> listViewEx.getSelectionModel().select(6));
        listViewEx.setItemLoader(() -> {
            Thread.sleep(1000);
            return Arrays.asList("Hallo", "Welt", "Bitte", "Nicht", "Stören", "Kanu", "Fahren", "Ist", "Schön", "Keiner", "Kann", "Was", null, null);
        });
        listViewEx.setHeaderKeyExtractor(s -> s == null ? null : s.charAt(0) + "");
        listViewEx.setValueComparator(Comparator.nullsFirst(String::compareTo));
        listViewEx.setHeaderComparator(Comparator.nullsFirst(String::compareTo));
        listViewEx.setValueCellRendererCallback((cell, item, empty) -> {
            cell.setStyle(null);
            if (!empty) {
                if (item == null) {
                    cell.setText("<null>");
                } else {
                    cell.setText(item);
                }
            }
        });
        listViewEx.setHeaderCellRendererCallback((cell, item, empty) -> {
            cell.setStyle("-fx-font-weight: bold");
            if (!empty) {
                if (item == null) {
                    cell.setText("Undefined");
                } else {
                    cell.setText(item);
                }
            }
        });
        listViewEx.getSelectionModel().selectedItemProperty().addListener((v, o, n) -> value.set(n));
        value.addListener((v, o, n) -> listViewEx.getSelectionModel().select(n));
        listViewEx.refresh();

        try {
            stage.initStyle(StageStyle.UTILITY);
        } catch (Exception ignore) {
        }
        stage.setScene(new Scene(listViewEx, 500, 500));
        stage.setOpacity(0);
        stage.show();
    }

    @Test
    public void test() throws Exception {
        Thread.sleep(1500);

        Assert.assertEquals("Fahren", listViewEx.getSelectionModel().getSelectedItem());


        Platform.runLater(() -> value.set("Welt"));

        Thread.sleep(100);

        Assert.assertEquals("Welt", value.get());
        Assert.assertEquals("Welt", listViewEx.getSelectionModel().getSelectedItem());

        Platform.runLater(() -> value.set("Hallo"));
        Thread.sleep(100);

        Assert.assertEquals("Hallo", value.get());
        Assert.assertEquals("Hallo", listViewEx.getSelectionModel().getSelectedItem());

        Platform.runLater(() -> {
            listViewEx.setOnItemsLoaded(null);
            listViewEx.refresh();
        });
        Thread.sleep(1500);

        Assert.assertEquals("Hallo", value.get());
        Assert.assertEquals("Hallo", listViewEx.getSelectionModel().getSelectedItem());
    }
}