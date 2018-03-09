package org.pcsoft.framework.jfex.ui.component.data;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Arrays;


public class ComboBoxExTestRunner extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        final ComboBoxEx<String, String> comboBoxEx = new ComboBoxEx<>();
//        comboBoxEx.getItems().setAll("Aber", "Alter", "Schöner", "Sabuerer", "Dummer", "Hübscher", "Zebra", "Ziel", "Zaun");
        comboBoxEx.setItemLoader(() -> {
            Thread.sleep(1000);
            return Arrays.asList("Aber", "Alter", "Schöner", "Sabuerer", "Dummer", "Hübscher", "Zebra", "Ziel", "Zaun");
        });
        comboBoxEx.setHeaderKeyExtractor(value -> value.charAt(0) + "");
        comboBoxEx.setHeaderComparator(String::compareTo);
        comboBoxEx.setValueComparator(String::compareTo);
        comboBoxEx.setHeaderCellRendererCallback((cell, item, empty) -> {
            cell.setText(null);
            cell.setGraphic(null);
            cell.setStyle(null);

            if (item != null && !empty) {
                cell.setText(item);
                cell.setStyle("-fx-font-weight: bold");
            }
        });
        comboBoxEx.setValueCellRendererCallback((cell, item, empty) -> {
            cell.setText(null);
            cell.setGraphic(null);
            cell.setStyle(null);

            if (item != null && !empty) {
                cell.setText(item);
            }
        });
        comboBoxEx.setValueButtonCellRendererCallback((cell, item, empty) -> {
            cell.setText(null);
            cell.setGraphic(null);
            cell.setStyle(null);

            if (item != null && !empty) {
                cell.setText(item);
                cell.setStyle("-fx-font-weight: bold");
            }
        });

        comboBoxEx.valueProperty().addListener((v, o, n) -> {
            System.out.println("Old: " + o);
            System.out.println("New: " + n);
        });
        comboBoxEx.setFilterCallback((String value, Object search) -> (value == null || search == null) || value.startsWith(search.toString()));

        final TextField textField = new TextField();
        comboBoxEx.filterValueProperty().bind(textField.textProperty());

        primaryStage.initStyle(StageStyle.UTILITY);
        primaryStage.setScene(new Scene(new VBox(5, textField, comboBoxEx), 800, 600));
        primaryStage.show();
    }
}
