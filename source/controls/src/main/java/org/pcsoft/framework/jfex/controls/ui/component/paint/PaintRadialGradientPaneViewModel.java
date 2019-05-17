package org.pcsoft.framework.jfex.controls.ui.component.paint;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.RadialGradient;
import org.pcsoft.framework.jfex.mvvm.FxmlViewModel;

public class PaintRadialGradientPaneViewModel implements FxmlViewModel {
    private final ObjectProperty<RadialGradient> radialGradient = new SimpleObjectProperty<>();

    public RadialGradient getRadialGradient() {
        return radialGradient.get();
    }

    public ObjectProperty<RadialGradient> radialGradientProperty() {
        return radialGradient;
    }

    public void setRadialGradient(RadialGradient radialGradient) {
        this.radialGradient.set(radialGradient);
    }
}
