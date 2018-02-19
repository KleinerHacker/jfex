package org.pcsoft.framework.jfex.component.cell;

import javafx.beans.property.BooleanProperty;


public interface SimplifyCellPane<T> extends CellPane<T> {
    void setSimpleView(boolean simpleView);
    boolean getSimpleView();
    BooleanProperty simpleViewProperty();
}
