package org.pcsoft.framework.jfex.controls.ui.component.paint;

import javafx.beans.property.ObjectProperty;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import org.pcsoft.framework.jfex.mvvm.Fxml;

import java.util.ResourceBundle;
import java.util.function.Supplier;

public class PaintImagePane extends HBox {

    private final PaintImagePaneViewModel viewModel;
    private final PaintImagePaneView controller;

    public PaintImagePane() {
        final Fxml.FxmlTuple<PaintImagePaneView, PaintImagePaneViewModel> viewTuple =
                Fxml.from(PaintImagePaneView.class).withResources(ResourceBundle.getBundle("lan/text")).withRoot(this).load();
        viewModel = viewTuple.getViewModel();
        controller = viewTuple.getViewController();
    }

    public ImagePattern getImagePattern() {
        return viewModel.getImagePattern();
    }

    public ObjectProperty<ImagePattern> imagePatternProperty() {
        return viewModel.imagePatternProperty();
    }

    public void setImagePattern(ImagePattern imagePattern) {
        viewModel.setImagePattern(imagePattern);
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
