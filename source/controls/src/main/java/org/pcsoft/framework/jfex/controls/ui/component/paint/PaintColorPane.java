package org.pcsoft.framework.jfex.controls.ui.component.paint;

import javafx.beans.property.ObjectProperty;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import org.pcsoft.framework.jfex.mvvm.Fxml;

import java.util.ResourceBundle;

public class PaintColorPane extends VBox {
    private final PaintColorPaneViewModel viewModel;
    private final PaintColorPaneView controller;

    public PaintColorPane() {
        final Fxml.FxmlTuple<PaintColorPaneView, PaintColorPaneViewModel> viewTuple =
                Fxml.from(PaintColorPaneView.class).withResources(ResourceBundle.getBundle("lan/text")).withRoot(this).load();
        viewModel = viewTuple.getViewModel();
        controller = viewTuple.getViewController();
    }

    public Color getSelectedColor() {
        return viewModel.getSelectedColor();
    }

    public ObjectProperty<Color> selectedColorProperty() {
        return viewModel.selectedColorProperty();
    }

    public void setSelectedColor(Color selectedColor) {
        viewModel.setSelectedColor(selectedColor);
    }
}
