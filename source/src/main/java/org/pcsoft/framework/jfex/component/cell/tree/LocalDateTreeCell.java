package org.pcsoft.framework.jfex.component.cell.tree;


import org.pcsoft.framework.jfex.component.cell.LocalDateCellPane;

import java.time.LocalDate;


public class LocalDateTreeCell extends TreeCellBase<LocalDate, LocalDateCellPane> {

    public LocalDateTreeCell() {
        super(LocalDateCellPane.class);
    }
}
