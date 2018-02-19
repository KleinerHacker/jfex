//package org.pcsoft.framework.jfex.data;
//
//import org.pcsoft.framework.jfex.component.OverlayProgressIndicatorPane;
//import javafx.collections.ListChangeListener;
//import javafx.fxml.FXML;
//import javafx.scene.control.MultipleSelectionModel;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableRow;
//import javafx.scene.control.TableView;
//
//import java.net.URL;
//import java.util.List;
//import java.util.ResourceBundle;
//import java.util.stream.Collectors;
//
//public class TableViewExView<T, G> extends MultipleSelectionDataView<T, G, TableRow, TableViewExViewModel<T, G>> {
//
//    @FXML
//    private TableView<Item> tbl;
//    @FXML
//    private OverlayProgressIndicatorPane pnlProgress;
//
//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//        super.initialize(location, resources);
//
//        tbl.setRowFactory(param -> new TableRow<Item>() {
//            @Override
//            protected void updateItem(Item item, boolean empty) {
//                super.updateItem(item, empty);
//                renderCell(this, item, empty);
//            }
//        });
//
//        viewModel.getColumns().addListener((ListChangeListener<TableColumn<T, ?>>) c -> {
//            tbl.getColumns().setAll(
//                    viewModel.getColumns().stream()
//                            .map(item -> (TableColumn)new DataTableColumnWrapper(item))
//                            .collect(Collectors.toList())
//            );
//        });
//    }
//
//    @Override
//    protected MultipleSelectionModel<Item> getInnerSelectionModel() {
//        return tbl.getSelectionModel();
//    }
//
//    @Override
//    protected List<Item> getComponentList() {
//        return tbl.getItems();
//    }
//
//    @Override
//    protected void onShowProgress(String initialText) {
//        pnlProgress.fadeIn(initialText);
//    }
//
//    @Override
//    protected void onHideProgress() {
//        pnlProgress.fadeOut();
//    }
//}
