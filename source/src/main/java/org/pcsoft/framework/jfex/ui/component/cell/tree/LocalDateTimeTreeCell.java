package org.pcsoft.framework.jfex.ui.component.cell.tree;


import org.pcsoft.framework.jfex.ui.component.cell.LocalDateTimeCellPane;

import java.time.LocalDateTime;


public class LocalDateTimeTreeCell extends TreeCellBase<LocalDateTime, LocalDateTimeCellPane> {

    public LocalDateTimeTreeCell() {
        super(LocalDateTimeCellPane.class);
    }
}
