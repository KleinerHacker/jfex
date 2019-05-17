package org.pcsoft.framework.jfex.controls.ui.component.data;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.pcsoft.framework.jfex.controls.ui.component.SelectionMoveDownButton;
import org.pcsoft.framework.jfex.controls.ui.component.SelectionMoveUpButton;

import java.util.Arrays;


public class ListViewMovingTestRunner extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        final ListViewEx<String, String> listViewEx = new ListViewEx<>();
//        listViewEx.getItems().setAll("Aber", "Alter", "Schöner", "Sabuerer", "Dummer", "Hübscher", "Zebra", "Ziel", "Zaun");
        listViewEx.setItemLoader(() -> {
            Thread.sleep(1000);
            return Arrays.asList("Aber", "Alter", "Schöner", "Sabuerer", "Dummer", "Hübscher", "Zebra", "Ziel", "Zaun");
        });
        listViewEx.setEmptyText("Keine Daten Vorhanden");
        listViewEx.setEmptyTextStyle("-fx-text-fill: red");
        listViewEx.setValueCellRendererCallback((cell, item, empty) -> {
            cell.setText(null);
            cell.setGraphic(null);
            cell.setStyle(null);

            if (item != null && !empty) {
                cell.setText(item);
            }
        });

        listViewEx.getSelectionModel().selectedItemProperty().addListener((v, o, n) -> {
            System.out.println("Old: " + o);
            System.out.println("New: " + n);
        });
        listViewEx.setFilterCallback((String value, Object search) -> (value == null || search == null) || value.startsWith(search.toString()));

        listViewEx.setShowToolbar(true);
        listViewEx.setOrientation(ListViewEx.ToolbarOrientation.LEFT);
        final SelectionMoveUpButton selectionMoveUpButton = new SelectionMoveUpButton(listViewEx);
        final SelectionMoveDownButton selectionMoveDownButton = new SelectionMoveDownButton(listViewEx);
        final CheckBox ckbDoubleShift = new CheckBox(null);
        final NumberBinding shiftCountBinding = Bindings.when(ckbDoubleShift.selectedProperty()).then(3).otherwise(1);
        selectionMoveUpButton.shiftCountProperty().bind(shiftCountBinding);
        selectionMoveDownButton.shiftCountProperty().bind(shiftCountBinding);
        listViewEx.getToolbarItems().setAll(
                selectionMoveUpButton, selectionMoveDownButton, new Separator(Orientation.HORIZONTAL), ckbDoubleShift
        );

        final TextField textField = new TextField();
        listViewEx.filterValueProperty().bind(textField.textProperty());

        listViewEx.setMaxHeight(Double.MAX_VALUE);
        VBox.setVgrow(listViewEx, Priority.ALWAYS);

        primaryStage.initStyle(StageStyle.UTILITY);
        primaryStage.setScene(new Scene(new VBox(5, textField, listViewEx), 800, 600));
        primaryStage.show();
    }
}
