package org.pcsoft.framework.jfex.controls.ui.component.paint;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Color;
import org.pcsoft.framework.jfex.mvvm.FxmlViewModel;

public class PaintColorPaneViewModel implements FxmlViewModel {
    private final ObjectProperty<Color> selectedColor = new SimpleObjectProperty<>(Color.WHITE);

    public Color getSelectedColor() {
        return selectedColor.get();
    }

    public ObjectProperty<Color> selectedColorProperty() {
        return selectedColor;
    }

    public void setSelectedColor(Color selectedColor) {
        this.selectedColor.set(selectedColor);
    }
}
