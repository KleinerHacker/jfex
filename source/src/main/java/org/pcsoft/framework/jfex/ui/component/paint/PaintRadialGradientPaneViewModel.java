package org.pcsoft.framework.jfex.ui.component.paint;

import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.RadialGradient;

public class PaintRadialGradientPaneViewModel implements ViewModel {
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
