package org.pcsoft.framework.jfex.controls.ui.component.paint;

import javafx.beans.property.ReadOnlyListProperty;
import javafx.collections.ObservableList;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Stop;
import org.pcsoft.framework.jfex.mvvm.Fxml;

import java.util.ResourceBundle;

public class GradientStopTable extends HBox {
    private final GradientStopTableViewModel viewModel;
    private final GradientStopTableView controller;

    public GradientStopTable() {
        final Fxml.FxmlTuple<GradientStopTableView, GradientStopTableViewModel> viewTuple =
                Fxml.from(GradientStopTableView.class).withResources(ResourceBundle.getBundle("lan/text")).withRoot(this).load();
        viewModel = viewTuple.getViewModel();
        controller = viewTuple.getViewController();
    }

    public ObservableList<Stop> getStopList() {
        return viewModel.getStopList();
    }

    public ReadOnlyListProperty<Stop> stopListProperty() {
        return viewModel.stopListProperty();
    }
}
