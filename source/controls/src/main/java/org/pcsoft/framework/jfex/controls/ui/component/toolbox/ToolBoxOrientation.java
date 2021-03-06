package org.pcsoft.framework.jfex.controls.ui.component.toolbox;

import javafx.geometry.Orientation;

public enum ToolBoxOrientation {
    LEFT(Orientation.VERTICAL),
    RIGHT(Orientation.VERTICAL),
    TOP(Orientation.HORIZONTAL),
    BOTTOM(Orientation.HORIZONTAL);

    private final Orientation orientation;

    private ToolBoxOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public Orientation getOrientation() {
        return orientation;
    }
}