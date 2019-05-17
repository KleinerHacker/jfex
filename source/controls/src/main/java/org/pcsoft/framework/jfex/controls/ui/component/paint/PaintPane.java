package org.pcsoft.framework.jfex.controls.ui.component.paint;

import javafx.beans.property.ObjectProperty;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Paint;
import org.pcsoft.framework.jfex.mvvm.Fxml;

import java.util.ResourceBundle;
import java.util.function.Supplier;

public class PaintPane extends BorderPane {
    private final PaintPaneViewModel viewModel;
    private final PaintPaneView controller;

    public PaintPane() {
        final Fxml.FxmlTuple<PaintPaneView, PaintPaneViewModel> viewTuple =
                Fxml.from(PaintPaneView.class).withResources(ResourceBundle.getBundle("lan/text")).withRoot(this).load();
        viewModel = viewTuple.getViewModel();
        controller = viewTuple.getViewController();
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
