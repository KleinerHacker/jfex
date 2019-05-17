package org.pcsoft.framework.jfex.controls.ui.component.cell.tree_table;


import javafx.beans.property.ObjectProperty;
import org.pcsoft.framework.jfex.controls.ui.component.cell.LocalDateCellPane;

import java.time.LocalDate;
import java.time.format.FormatStyle;


public class LocalDateTreeTableCell<T> extends TreeTableCellBase<T, LocalDate, LocalDateCellPane> {
    public LocalDateTreeTableCell() {
        super(LocalDateCellPane.class);
    }

    public LocalDateTreeTableCell(FormatStyle dateFormatStyle) {
        this();
        setDateFormatStyle(dateFormatStyle);
    }

    public FormatStyle getDateFormatStyle() {
        return cellPane.getDateFormatStyle();
    }

    public ObjectProperty<FormatStyle> dateFormatStyleProperty() {
        return cellPane.dateFormatStyleProperty();
    }

    public void setDateFormatStyle(FormatStyle dateTimeFormatter) {
        cellPane.setDateFormatStyle(dateTimeFormatter);
    }
}
