package org.pcsoft.framework.jfex.ui.component.paint;

import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.ViewTuple;
import javafx.beans.property.ReadOnlyListProperty;
import javafx.collections.ObservableList;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Stop;

import java.util.ResourceBundle;

public class GradientStopTable extends HBox {

    private final GradientStopTableViewModel viewModel;
    private final GradientStopTableView controller;

    public GradientStopTable() {
        final ViewTuple<GradientStopTableView, GradientStopTableViewModel> viewTuple =
                FluentViewLoader.fxmlView(GradientStopTableView.class).resourceBundle(ResourceBundle.getBundle("lan/text")).root(this).load();
        viewModel = viewTuple.getViewModel();
        controller = viewTuple.getCodeBehind();
    }

    public ObservableList<Stop> getStopList() {
        return viewModel.getStopList();
    }

    public ReadOnlyListProperty<Stop> stopListProperty() {
        return viewModel.stopListProperty();
    }
}
