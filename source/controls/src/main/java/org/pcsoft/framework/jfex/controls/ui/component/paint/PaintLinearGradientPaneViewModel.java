package org.pcsoft.framework.jfex.controls.ui.component.paint;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.LinearGradient;
import org.pcsoft.framework.jfex.mvvm.FxmlViewModel;

public class PaintLinearGradientPaneViewModel implements FxmlViewModel {
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
