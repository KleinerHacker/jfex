package org.pcsoft.framework.jfex.component.cell.list;


import org.pcsoft.framework.jfex.component.cell.LocalDateCellPane;

import java.time.LocalDate;


public class LocalDateListCell extends ListCellBase<LocalDate, LocalDateCellPane> {

    public LocalDateListCell() {
        super(LocalDateCellPane.class);
    }
}
