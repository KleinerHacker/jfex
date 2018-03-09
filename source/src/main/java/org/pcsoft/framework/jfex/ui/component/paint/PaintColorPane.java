package org.pcsoft.framework.jfex.ui.component.paint;

import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.ViewTuple;
import javafx.beans.property.ObjectProperty;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.ResourceBundle;

public class PaintColorPane extends VBox {

    private final PaintColorPaneViewModel viewModel;
    private final PaintColorPaneView controller;

    public PaintColorPane() {
        final ViewTuple<PaintColorPaneView, PaintColorPaneViewModel> viewTuple =
                FluentViewLoader.fxmlView(PaintColorPaneView.class).resourceBundle(ResourceBundle.getBundle("lan/text")).root(this).load();
        viewModel = viewTuple.getViewModel();
        controller = viewTuple.getCodeBehind();
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
