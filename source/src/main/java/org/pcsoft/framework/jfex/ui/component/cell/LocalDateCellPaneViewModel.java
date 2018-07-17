package org.pcsoft.framework.jfex.ui.component.cell;

import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import org.apache.commons.lang.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;


public class LocalDateCellPaneViewModel implements ViewModel {

    private final ObjectProperty<LocalDate> date = new SimpleObjectProperty<>();
    private final ObjectProperty<FormatStyle> dateFormatStyle = new SimpleObjectProperty<>(FormatStyle.LONG);

    private final StringBinding dateString;

    public LocalDateCellPaneViewModel() {
        dateString = Bindings.createStringBinding(
                () -> date.get() == null || dateFormatStyle.get() == null ? StringUtils.EMPTY : date.get().format(DateTimeFormatter.ofLocalizedDate(dateFormatStyle.get())),
                date, dateFormatStyle
        );
    }

    public LocalDate getDate() {
        return date.get();
    }

    public ObjectProperty<LocalDate> dateProperty() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date.set(date);
    }

    public FormatStyle getDateFormatStyle() {
        return dateFormatStyle.get();
    }

    public ObjectProperty<FormatStyle> dateFormatStyleProperty() {
        return dateFormatStyle;
    }

    public void setDateFormatStyle(FormatStyle dateFormatStyle) {
        this.dateFormatStyle.set(dateFormatStyle);
    }

    public String getDateString() {
        return dateString.get();
    }

    public StringBinding dateStringProperty() {
        return dateString;
    }
}
