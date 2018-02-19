package org.pcsoft.framework.jfex.workflow.utils;

import javafx.scene.input.DataFormat;


public final class DraggingFormat {
    public static final DataFormat COMPONENT = new DataFormat("Workflow.Component");
    public static final DataFormat ELEMENT = new DataFormat("Workflow.Element");

    private DraggingFormat() {
    }
}
