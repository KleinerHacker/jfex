package org.pcsoft.framework.jfex.ui.component.paint;

import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.ViewTuple;
import javafx.beans.property.ObjectProperty;
import javafx.scene.layout.VBox;
import javafx.scene.paint.RadialGradient;

import java.util.ResourceBundle;

public class PaintRadialGradientPane extends VBox {

    private final PaintRadialGradientPaneViewModel viewModel;
    private final PaintRadialGradientPaneView controller;

    public PaintRadialGradientPane() {
        final ViewTuple<PaintRadialGradientPaneView, PaintRadialGradientPaneViewModel> viewTuple =
                FluentViewLoader.fxmlView(PaintRadialGradientPaneView.class).resourceBundle(ResourceBundle.getBundle("lan/text")).root(this).load();
        viewModel = viewTuple.getViewModel();
        controller = viewTuple.getCodeBehind();
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
