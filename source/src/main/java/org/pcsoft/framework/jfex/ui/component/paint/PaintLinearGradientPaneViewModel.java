package org.pcsoft.framework.jfex.ui.component.paint;

import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.LinearGradient;

public class PaintLinearGradientPaneViewModel implements ViewModel {
    private final ObjectProperty<LinearGradient> linearGradient = new SimpleObjectProperty<>();

    public LinearGradient getLinearGradient() {
        return linearGradient.get();
    }

    public ObjectProperty<LinearGradient> linearGradientProperty() {
        return linearGradient;
    }

    public void setLinearGradient(LinearGradient linearGradient) {
        this.linearGradient.set(linearGradient);
    }
}
