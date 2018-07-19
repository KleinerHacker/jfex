package org.pcsoft.framework.jfex.ui.component.data;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Arrays;
import java.util.Comparator;


public class ListViewExTestRunner extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        final ListViewEx<String, String> listViewEx = new ListViewEx<>();
//        listViewEx.getItems().setAll("Aber", "Alter", "Schöner", "Sabuerer", "Dummer", "Hübscher", "Zebra", "Ziel", "Zaun");
        listViewEx.setOnItemsLoaded(() -> listViewEx.getSelectionModel().select(5));
        listViewEx.setItemLoader(() -> {
            Thread.sleep(1000);
            return Arrays.asList("Aber", "Alter", "Schöner", "Sabuerer", "Dummer", "Hübscher", "Zebra", "Ziel", "Zaun", null, null);
        });
        listViewEx.setHeaderKeyExtractor(value -> value == null ? null : value.charAt(0) + "");
        listViewEx.setHeaderComparator(Comparator.nullsFirst(String::compareTo));
        listViewEx.setValueComparator(Comparator.nullsFirst(String::compareTo));
        listViewEx.setEmptyText("Keine Daten Vorhanden");
        listViewEx.setEmptyTextStyle("-fx-text-fill: red");
        listViewEx.setHeaderCellRendererCallback((cell, item, empty) -> {
            cell.setText(null);
            cell.setGraphic(null);
            cell.setStyle(null);

            if (!empty) {
                if (item == null) {
                    cell.setText("Undefined");
                } else {
                    cell.setText(item);
                }
                cell.setStyle("-fx-font-weight: bold");
            }
        });
        listViewEx.setValueCellRendererCallback((cell, item, empty) -> {
            cell.setText(null);
            cell.setGraphic(null);
            cell.setStyle(null);

            if (!empty) {
                if (item == null) {
                    cell.setText("<null>");
                } else {
                    cell.setText(item);
                }
            }
        });

        listViewEx.getSelectionModel().selectedItemProperty().addListener((v, o, n) -> {
            System.out.println("Old: " + o);
            System.out.println("New: " + n);
        });
        listViewEx.setFilterCallback((String value, Object search) -> (value == null || search == null) || value.startsWith(search.toString()));

        final TextField textField = new TextField();
        listViewEx.filterValueProperty().bind(textField.textProperty());

        listViewEx.setMaxHeight(Double.MAX_VALUE);
        VBox.setVgrow(listViewEx, Priority.ALWAYS);

        primaryStage.initStyle(StageStyle.UTILITY);
        primaryStage.setScene(new Scene(new VBox(5, textField, listViewEx), 800, 600));
        primaryStage.show();
    }
}
