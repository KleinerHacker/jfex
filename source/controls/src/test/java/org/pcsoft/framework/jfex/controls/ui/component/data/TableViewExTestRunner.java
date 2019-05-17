//package org.pcsoft.framework.jfex.data;
//
//import javafx.application.Application;
//import javafx.beans.property.ReadOnlyStringWrapper;
//import javafx.scene.Scene;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TextField;
//import javafx.scene.layout.Priority;
//import javafx.scene.layout.VBox;
//import javafx.stage.Stage;
//import javafx.stage.StageStyle;
//
//import java.util.Arrays;
//
//public class TableViewExTestRunner extends Application {
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//
//    @Override
//    public void start(Stage primaryStage) throws Exception {
//        final TableViewEx<String, String> tableViewEx = new TableViewEx<>();
////        tableViewEx.getItems().setAll("Aber", "Alter", "Schöner", "Sabuerer", "Dummer", "Hübscher", "Zebra", "Ziel", "Zaun");
//        tableViewEx.setItemLoader(() -> {
//            Thread.sleep(1000);
//            return Arrays.asList("Aber", "Alter", "Schöner", "Sabuerer", "Dummer", "Hübscher", "Zebra", "Ziel", "Zaun");
//        });
//        tableViewEx.setHeaderKeyExtractor(value -> value.charAt(0) + "");
//        tableViewEx.setHeaderComparator(String::compareTo);
//        tableViewEx.setValueComparator(String::compareTo);
//        tableViewEx.setHeaderCellRendererCallback((cell, item, empty) -> {
//            cell.setText(null);
//            cell.setGraphic(null);
//            cell.setStyle(null);
//
//            if (item != null && !empty) {
//                cell.setText(item);
//                cell.setStyle("-fx-font-weight: bold");
//            }
//        });
//        tableViewEx.setValueCellRendererCallback((cell, item, empty) -> {
//            cell.setText(null);
//            cell.setGraphic(null);
//            cell.setStyle(null);
//
//            if (item != null && !empty) {
//                cell.setText(item);
//            }
//        });
//
//        tableViewEx.getSelectionModel().selectedItemProperty().addListener((v, o, n) -> {
//            System.out.println("Old: " + o);
//            System.out.println("New: " + n);
//        });
//        tableViewEx.setFilterCallback((String value, Object search) -> (value == null || search == null) || value.startsWith(search.toString()));
//
//        final TextField textField = new TextField();
//        tableViewEx.filterValueProperty().bind(textField.textProperty());
//
//        final TableColumn<String, String> value = new TableColumn<>("Value");
//        value.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue()).getReadOnlyProperty());
//        tableViewEx.getColumns().add(value);
//
//        tableViewEx.setMaxHeight(Double.MAX_VALUE);
//        VBox.setVgrow(tableViewEx, Priority.ALWAYS);
//
//        primaryStage.initStyle(StageStyle.UTILITY);
//        primaryStage.setScene(new Scene(new VBox(5, textField, tableViewEx), 800, 600));
//        primaryStage.show();
//    }
//}
