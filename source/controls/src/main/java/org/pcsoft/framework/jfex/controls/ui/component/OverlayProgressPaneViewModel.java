package org.pcsoft.framework.jfex.controls.ui.component;

import javafx.beans.property.*;
import javafx.scene.control.ProgressBar;
import org.pcsoft.framework.jfex.mvvm.FxmlViewModel;


public abstract class OverlayProgressPaneViewModel implements FxmlViewModel {
    private final DoubleProperty progress = new SimpleDoubleProperty(ProgressBar.INDETERMINATE_PROGRESS);
    private final StringProperty action = new SimpleStringProperty("Please wait...");
    private final BooleanProperty visible = new SimpleBooleanProperty(false);

    public double getProgress() {
        return progress.get();
    }

    public DoubleProperty progressProperty() {
        return progress;
    }

    public void setProgress(double progress) {
        this.progress.set(progress);
    }

    public String getAction() {
        return action.get();
    }

    public StringProperty actionProperty() {
        return action;
    }

    public void setAction(String action) {
        this.action.set(action);
    }

    public boolean getVisible() {
        return visible.get();
    }

    public BooleanProperty visibleProperty() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible.set(visible);
    }
}
