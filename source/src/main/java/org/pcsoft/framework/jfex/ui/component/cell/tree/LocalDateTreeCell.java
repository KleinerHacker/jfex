package org.pcsoft.framework.jfex.ui.component.cell.tree;


import org.pcsoft.framework.jfex.ui.component.cell.LocalDateCellPane;

import java.time.LocalDate;


public class LocalDateTreeCell extends TreeCellBase<LocalDate, LocalDateCellPane> {

    public LocalDateTreeCell() {
        super(LocalDateCellPane.class);
    }
}
