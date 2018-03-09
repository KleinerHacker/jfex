package org.pcsoft.framework.jfex.ui.component.paint;

import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.ViewTuple;
import javafx.beans.property.ObjectProperty;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Paint;

import java.util.ResourceBundle;
import java.util.function.Supplier;

public class PaintPane extends BorderPane {

    private final PaintPaneViewModel viewModel;
    private final PaintPaneView controller;

    public PaintPane() {
        final ViewTuple<PaintPaneView, PaintPaneViewModel> viewTuple =
                FluentViewLoader.fxmlView(PaintPaneView.class).resourceBundle(ResourceBundle.getBundle("lan/text")).root(this).load();
        viewModel = viewTuple.getViewModel();
        controller = viewTuple.getCodeBehind();
    }

    public Paint getSelectedPaint() {
        return viewModel.getSelectedPaint();
    }

    public ObjectProperty<Paint> selectedPaintProperty() {
        return viewModel.selectedPaintProperty();
    }

    public void setSelectedPaint(Paint selectedPaint) {
        viewModel.setSelectedPaint(selectedPaint);
    }

    public Supplier<Image> getImageChooserCallback() {
        return viewModel.getImageChooserCallback();
    }

    public ObjectProperty<Supplier<Image>> imageChooserCallbackProperty() {
        return viewModel.imageChooserCallbackProperty();
    }

    public void setImageChooserCallback(Supplier<Image> imageChooserCallback) {
        viewModel.setImageChooserCallback(imageChooserCallback);
    }
}
