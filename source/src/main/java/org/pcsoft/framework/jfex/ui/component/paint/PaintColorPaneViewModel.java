package org.pcsoft.framework.jfex.ui.component.paint;

import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Color;

public class PaintColorPaneViewModel implements ViewModel {
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
