package org.pcsoft.framework.jfex.ui.component.paint;

import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.ViewTuple;
import javafx.beans.property.ObjectProperty;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;

import java.util.ResourceBundle;
import java.util.function.Supplier;

public class PaintImagePane extends HBox {

    private final PaintImagePaneViewModel viewModel;
    private final PaintImagePaneView controller;

    public PaintImagePane() {
        final ViewTuple<PaintImagePaneView, PaintImagePaneViewModel> viewTuple =
                FluentViewLoader.fxmlView(PaintImagePaneView.class).resourceBundle(ResourceBundle.getBundle("lan/text")).root(this).load();
        viewModel = viewTuple.getViewModel();
        controller = viewTuple.getCodeBehind();
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
