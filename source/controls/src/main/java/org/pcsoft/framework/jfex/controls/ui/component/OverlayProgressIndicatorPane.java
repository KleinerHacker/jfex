package org.pcsoft.framework.jfex.controls.ui.component;

import javafx.scene.layout.BorderPane;
import org.pcsoft.framework.jfex.mvvm.Fxml;


public class OverlayProgressIndicatorPane extends BorderPane {

    private final OverlayProgressIndicatorPaneViewModel viewModel;
    private final OverlayProgressIndicatorPaneView controller;

    public OverlayProgressIndicatorPane() {
        final Fxml.FxmlTuple<OverlayProgressIndicatorPaneView, OverlayProgressIndicatorPaneViewModel> viewTuple =
                Fxml.from(OverlayProgressIndicatorPaneView.class).load();
        setCenter(viewTuple.getView());
        controller = viewTuple.getViewController();

        visibleProperty().bind(viewTuple.getView().visibleProperty());
        opacityProperty().bind(viewTuple.getView().opacityProperty());

        viewModel = viewTuple.getViewModel();
    }

    public void fadeIn(String action) {
        viewModel.setVisible(true);
        viewModel.setAction(action);
    }

    public void fadeOut() {
        viewModel.setVisible(false);
    }

    public void update(String action) {
        viewModel.setAction(action);
    }

    public void update(double progress) {
        viewModel.setProgress(progress);
    }

    public void update(String action, double progress) {
        update(action);
        update(progress);
    }
}
