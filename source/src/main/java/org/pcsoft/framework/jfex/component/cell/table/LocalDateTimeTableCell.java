package org.pcsoft.framework.jfex.component.cell.table;


import org.pcsoft.framework.jfex.component.cell.LocalDateTimeCellPane;

import java.time.LocalDateTime;


public class LocalDateTimeTableCell<T> extends TableCellBase<T, LocalDateTime, LocalDateTimeCellPane> {

    public LocalDateTimeTableCell() {
        super(LocalDateTimeCellPane.class);
    }
}
