package org.pcsoft.framework.jfex.ui.dialog;

import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Color;

public class GradientStopDialogViewModel implements ViewModel {
    private final DoubleProperty offset = new SimpleDoubleProperty(0.5d);
    private final ObjectProperty<Color> color = new SimpleObjectProperty<>(Color.WHITE);

    private final BooleanBinding allowOk;

    public GradientStopDialogViewModel() {
        allowOk = color.isNotNull();
    }

    public double getOffset() {
        return offset.get();
    }

    public DoubleProperty offsetProperty() {
        return offset;
    }

    public void setOffset(double offset) {
        this.offset.set(offset);
    }

    public Color getColor() {
        return color.get();
    }

    public ObjectProperty<Color> colorProperty() {
        return color;
    }

    public void setColor(Color color) {
        this.color.set(color);
    }

    public Boolean getAllowOk() {
        return allowOk.get();
    }

    public BooleanBinding allowOkProperty() {
        return allowOk;
    }
}
