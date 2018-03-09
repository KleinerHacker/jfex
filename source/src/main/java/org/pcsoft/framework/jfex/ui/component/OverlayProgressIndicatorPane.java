package org.pcsoft.framework.jfex.ui.component;

import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.ViewTuple;
import javafx.scene.layout.BorderPane;


public class OverlayProgressIndicatorPane extends BorderPane {

    private final OverlayProgressIndicatorPaneViewModel viewModel;
    private final OverlayProgressIndicatorPaneView controller;

    public OverlayProgressIndicatorPane() {
        final ViewTuple<OverlayProgressIndicatorPaneView, OverlayProgressIndicatorPaneViewModel> viewTuple =
                FluentViewLoader.fxmlView(OverlayProgressIndicatorPaneView.class).load();
        setCenter(viewTuple.getView());
        controller = viewTuple.getCodeBehind();

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
