package org.pcsoft.framework.jfex.controls.ui.component.paint;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import org.pcsoft.framework.jfex.mvvm.FxmlViewModel;

import java.util.function.Supplier;

public class PaintImagePaneViewModel implements FxmlViewModel {
    private final ObjectProperty<ImagePattern> imagePattern = new SimpleObjectProperty<>();
    private final ObjectProperty<Supplier<Image>> imageChooserCallback = new SimpleObjectProperty<>();

    public Supplier<Image> getImageChooserCallback() {
        return imageChooserCallback.get();
    }

    public ObjectProperty<Supplier<Image>> imageChooserCallbackProperty() {
        return imageChooserCallback;
    }

    public void setImageChooserCallback(Supplier<Image> imageChooserCallback) {
        this.imageChooserCallback.set(imageChooserCallback);
    }

    public ImagePattern getImagePattern() {
        return imagePattern.get();
    }

    public ObjectProperty<ImagePattern> imagePatternProperty() {
        return imagePattern;
    }

    public void setImagePattern(ImagePattern imagePattern) {
        this.imagePattern.set(imagePattern);
    }
}
