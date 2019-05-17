package org.pcsoft.framework.jfex.controls.ui.component.paint;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.ListChangeListener;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.paint.Stop;
import org.pcsoft.framework.jfex.controls.ui.dialog.GradientStopDialog;
import org.pcsoft.framework.jfex.mvvm.FxmlView;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;

public class GradientStopTableView extends FxmlView<GradientStopTableViewModel> {
    @FXML
    private TableView<Stop> tblStop;
    @FXML
    private TableColumn<Stop, Double> tbcOffset;
    @FXML
    private TableColumn<Stop, Color> tbcColor;

    @FXML
    private Button btnAdd;
    @FXML
    private Button btnRemove;

    private final AtomicBoolean ignoreUpdate = new AtomicBoolean(false);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnRemove.disableProperty().bind(tblStop.getSelectionModel().selectedItemProperty().isNull());

        tbcOffset.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getOffset()).getReadOnlyProperty());
        tbcColor.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getColor()).getReadOnlyProperty());
        tbcColor.setCellFactory(param -> new TableCell<Stop, Color>() {
            @Override
            protected void updateItem(Color item, boolean empty) {
                super.updateItem(item, empty);

                setBackground(null);
                if (item != null && !empty) {
                    setBackground(new Background(new BackgroundFill(item, null, null)));
                }
            }
        });     

        tblStop.getSortOrder().add(tbcOffset);
        tblStop.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tblStop.getItems().addListener((ListChangeListener<Stop>) c -> {
            if (ignoreUpdate.get())
                return;

            ignoreUpdate.set(true);
            try {
                tblStop.sort();
            } finally {
                ignoreUpdate.set(false);
            }
        });
        Bindings.bindContent(tblStop.getItems(), viewModel.stopListProperty());
    }

    @FXML
    private void onActionAdd(Event e) {
        final Optional<GradientStopDialog.Data> data = new GradientStopDialog(tblStop.getScene().getWindow()).showAndWait();
        if (data.isPresent()) {
            viewModel.getStopList().add(new Stop(data.get().getOffset(), data.get().getColor()));
        }
    }

    @FXML
    private void onActionRemove(Event e) {
        final Optional<ButtonType> result = new Alert(Alert.AlertType.WARNING, "You are sure to delete stop?", ButtonType.YES, ButtonType.NO).showAndWait();
        if (result.isPresent() && result.get() == ButtonType.YES) {
            viewModel.getStopList().removeAll(tblStop.getSelectionModel().getSelectedItems());
        }
    }

    @FXML
    private void onItemClick(MouseEvent e) {
        if (e.getButton() == MouseButton.PRIMARY && e.getClickCount() == 2) {
            if (tblStop.getSelectionModel().getSelectedItem() == null)
                return;

            final Stop stop = tblStop.getSelectionModel().getSelectedItem();
            final GradientStopDialog.Data inputData = new GradientStopDialog.Data(stop.getOffset(), stop.getColor());
            final Optional<GradientStopDialog.Data> outputData = new GradientStopDialog(tblStop.getScene().getWindow(), inputData).showAndWait();
            if (outputData.isPresent()) {
                final int index = viewModel.getStopList().indexOf(stop);
                viewModel.getStopList().set(index, new Stop(outputData.get().getOffset(), outputData.get().getColor()));
            }
        }
    }
}
