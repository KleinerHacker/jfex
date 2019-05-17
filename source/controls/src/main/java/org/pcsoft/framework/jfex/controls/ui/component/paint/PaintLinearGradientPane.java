package org.pcsoft.framework.jfex.controls.ui.component.paint;

import javafx.beans.property.ObjectProperty;
import javafx.scene.layout.VBox;
import javafx.scene.paint.LinearGradient;
import org.pcsoft.framework.jfex.mvvm.Fxml;

import java.util.ResourceBundle;

public class PaintLinearGradientPane extends VBox {
    private final PaintLinearGradientPaneViewModel viewModel;
    private final PaintLinearGradientPaneView controller;

    public PaintLinearGradientPane() {
        final Fxml.FxmlTuple<PaintLinearGradientPaneView, PaintLinearGradientPaneViewModel> viewTuple =
                Fxml.from(PaintLinearGradientPaneView.class).withResources(ResourceBundle.getBundle("lan/text")).withRoot(this).load();
        viewModel = viewTuple.getViewModel();
        controller = viewTuple.getViewController();
    }

    public LinearGradient getLinearGradient() {
        return viewModel.getLinearGradient();
    }

    public ObjectProperty<LinearGradient> linearGradientProperty() {
        return viewModel.linearGradientProperty();
    }

    public void setLinearGradient(LinearGradient linearGradient) {
        viewModel.setLinearGradient(linearGradient);
    }
}
