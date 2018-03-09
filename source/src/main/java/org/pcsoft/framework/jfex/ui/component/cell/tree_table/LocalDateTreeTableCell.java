package org.pcsoft.framework.jfex.ui.component.cell.tree_table;


import org.pcsoft.framework.jfex.ui.component.cell.LocalDateCellPane;

import java.time.LocalDate;


public class LocalDateTreeTableCell<T> extends TreeTableCellBase<T, LocalDate, LocalDateCellPane> {
    public LocalDateTreeTableCell() {
        super(LocalDateCellPane.class);
    }
}
