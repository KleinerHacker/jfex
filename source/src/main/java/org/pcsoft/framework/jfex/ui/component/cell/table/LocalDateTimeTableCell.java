package org.pcsoft.framework.jfex.ui.component.cell.table;


import org.pcsoft.framework.jfex.ui.component.cell.LocalDateTimeCellPane;

import java.time.LocalDateTime;


public class LocalDateTimeTableCell<T> extends TableCellBase<T, LocalDateTime, LocalDateTimeCellPane> {

    public LocalDateTimeTableCell() {
        super(LocalDateTimeCellPane.class);
    }
}
