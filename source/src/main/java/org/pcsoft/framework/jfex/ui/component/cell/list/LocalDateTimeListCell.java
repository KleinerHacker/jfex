package org.pcsoft.framework.jfex.ui.component.cell.list;


import org.pcsoft.framework.jfex.ui.component.cell.LocalDateTimeCellPane;

import java.time.LocalDateTime;


public class LocalDateTimeListCell extends ListCellBase<LocalDateTime, LocalDateTimeCellPane> {

    public LocalDateTimeListCell() {
        super(LocalDateTimeCellPane.class);
    }
}
