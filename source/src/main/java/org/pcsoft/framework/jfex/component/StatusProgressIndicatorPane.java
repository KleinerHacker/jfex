package org.pcsoft.framework.jfex.component;

import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.ViewTuple;
import javafx.scene.layout.HBox;

public class StatusProgressIndicatorPane extends HBox {
    private final StatusProgressIndicatorPaneView controller;
    private final StatusProgressIndicatorPaneViewModel viewModel;

    public StatusProgressIndicatorPane() {
        final ViewTuple<StatusProgressIndicatorPaneView, StatusProgressIndicatorPaneViewModel> viewTuple =
                FluentViewLoader.fxmlView(StatusProgressIndicatorPaneView.class).root(this).load();
        controller = viewTuple.getCodeBehind();
        viewModel = viewTuple.getViewModel();
    }

    public void show(final String action) {
        viewModel.setText(action);
        viewModel.setVisible(true);
    }

    public void updateMessage(final String action) {
        viewModel.setText(action);
    }

    public void hide() {
        viewModel.setVisible(false);
    }
}
