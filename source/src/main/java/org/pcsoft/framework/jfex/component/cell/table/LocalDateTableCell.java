package org.pcsoft.framework.jfex.component.cell.table;


import org.pcsoft.framework.jfex.component.cell.LocalDateCellPane;

import java.time.LocalDate;


public class LocalDateTableCell<T> extends TableCellBase<T, LocalDate, LocalDateCellPane> {
    public LocalDateTableCell() {
        super(LocalDateCellPane.class);
    }
}
