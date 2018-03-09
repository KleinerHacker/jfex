package org.pcsoft.framework.jfex.ui.component.paint;

import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;
import javafx.scene.paint.Paint;

import java.util.ResourceBundle;
import java.util.function.Supplier;

public class PaintPaneViewModel implements ViewModel {
    enum PaintType {
        Color("paint.color"),
        LinearGradient("paint.gradient.linear"),
        RadialGradient("paint.gradient.radial"),
        Image("paint.image");

        private final String langaugeKey;

        PaintType(String langaugeKey) {
            this.langaugeKey = langaugeKey;
        }

        public String getLangaugeKey() {
            return langaugeKey;
        }

        public String getName() {
            return ResourceBundle.getBundle("lan/text").getString(langaugeKey);
        }
    }

    private final ObjectProperty<Paint> selectedPaint = new SimpleObjectProperty<>();
    private final ObjectProperty<Supplier<Image>> imageChooserCallback = new SimpleObjectProperty<>();

    public Paint getSelectedPaint() {
        return selectedPaint.get();
    }

    public ObjectProperty<Paint> selectedPaintProperty() {
        return selectedPaint;
    }

    public void setSelectedPaint(Paint selectedPaint) {
        this.selectedPaint.set(selectedPaint);
    }

    public Supplier<Image> getImageChooserCallback() {
        return imageChooserCallback.get();
    }

    public ObjectProperty<Supplier<Image>> imageChooserCallbackProperty() {
        return imageChooserCallback;
    }

    public void setImageChooserCallback(Supplier<Image> imageChooserCallback) {
        this.imageChooserCallback.set(imageChooserCallback);
    }
}
