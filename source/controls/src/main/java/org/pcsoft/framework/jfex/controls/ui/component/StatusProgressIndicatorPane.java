package org.pcsoft.framework.jfex.controls.ui.component;

import javafx.scene.layout.HBox;
import org.pcsoft.framework.jfex.mvvm.Fxml;

public class StatusProgressIndicatorPane extends HBox {
    private final StatusProgressIndicatorPaneView controller;
    private final StatusProgressIndicatorPaneViewModel viewModel;

    public StatusProgressIndicatorPane() {
        final Fxml.FxmlTuple<StatusProgressIndicatorPaneView, StatusProgressIndicatorPaneViewModel> viewTuple =
                Fxml.from(StatusProgressIndicatorPaneView.class).withRoot(this).load();
        controller = viewTuple.getViewController();
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
