package org.pcsoft.framework.jfex.component.cell.tree_table;


import org.pcsoft.framework.jfex.component.cell.LocalDateTimeCellPane;

import java.time.LocalDateTime;


public class LocalDateTimeTreeTableCell<T> extends TreeTableCellBase<T, LocalDateTime, LocalDateTimeCellPane> {

    public LocalDateTimeTreeTableCell() {
        super(LocalDateTimeCellPane.class);
    }
}
