package org.pcsoft.framework.jfex.controls.ui.component;

import javafx.scene.layout.BorderPane;
import org.pcsoft.framework.jfex.mvvm.Fxml;


public class OverlayProgressBarPane extends BorderPane {
    private final OverlayProgressBarPaneViewModel viewModel;
    private final OverlayProgressBarPaneView controller;

    public OverlayProgressBarPane() {
        final Fxml.FxmlTuple<OverlayProgressBarPaneView, OverlayProgressBarPaneViewModel> viewTuple =
                Fxml.from(OverlayProgressBarPaneView.class).load();
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
