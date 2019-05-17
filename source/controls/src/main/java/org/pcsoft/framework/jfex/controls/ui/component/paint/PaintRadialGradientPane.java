package org.pcsoft.framework.jfex.controls.ui.component.paint;

import javafx.beans.property.ObjectProperty;
import javafx.scene.layout.VBox;
import javafx.scene.paint.RadialGradient;
import org.pcsoft.framework.jfex.mvvm.Fxml;

import java.util.ResourceBundle;

public class PaintRadialGradientPane extends VBox {
    private final PaintRadialGradientPaneViewModel viewModel;
    private final PaintRadialGradientPaneView controller;

    public PaintRadialGradientPane() {
        final Fxml.FxmlTuple<PaintRadialGradientPaneView, PaintRadialGradientPaneViewModel> viewTuple =
                Fxml.from(PaintRadialGradientPaneView.class).withResources(ResourceBundle.getBundle("lan/text")).withRoot(this).load();
        viewModel = viewTuple.getViewModel();
        controller = viewTuple.getViewController();
    }

    public RadialGradient getRadialGradient() {
        return viewModel.getRadialGradient();
    }

    public ObjectProperty<RadialGradient> radialGradientProperty() {
        return viewModel.radialGradientProperty();
    }

    public void setRadialGradient(RadialGradient radialGradient) {
        viewModel.setRadialGradient(radialGradient);
    }
}
