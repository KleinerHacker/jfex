package org.pcsoft.framework.jfex.ui.component.paint;

import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.ViewTuple;
import javafx.beans.property.ObjectProperty;
import javafx.scene.layout.VBox;
import javafx.scene.paint.LinearGradient;

import java.util.ResourceBundle;

public class PaintLinearGradientPane extends VBox {

    private final PaintLinearGradientPaneViewModel viewModel;
    private final PaintLinearGradientPaneView controller;

    public PaintLinearGradientPane() {
        final ViewTuple<PaintLinearGradientPaneView, PaintLinearGradientPaneViewModel> viewTuple =
                FluentViewLoader.fxmlView(PaintLinearGradientPaneView.class).resourceBundle(ResourceBundle.getBundle("lan/text")).root(this).load();
        viewModel = viewTuple.getViewModel();
        controller = viewTuple.getCodeBehind();
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
