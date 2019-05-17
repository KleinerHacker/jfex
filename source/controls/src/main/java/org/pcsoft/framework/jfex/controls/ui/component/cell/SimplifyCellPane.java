package org.pcsoft.framework.jfex.controls.ui.component.cell;

import javafx.beans.property.BooleanProperty;


public interface SimplifyCellPane<T> extends CellPane<T> {
    void setSimpleView(boolean simpleView);
    boolean getSimpleView();
    BooleanProperty simpleViewProperty();
}
