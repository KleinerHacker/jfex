package org.pcsoft.framework.jfex.ui.component.cell;

import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import org.apache.commons.lang.StringUtils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;


public class LocalDateTimeCellPaneViewModel implements ViewModel {

    private final ObjectProperty<LocalDateTime> dateTime = new SimpleObjectProperty<>();
    private final ObjectProperty<FormatStyle> dateFormatStyle = new SimpleObjectProperty<>(FormatStyle.LONG);
    private final ObjectProperty<FormatStyle> timeFormatStyle = new SimpleObjectProperty<>(FormatStyle.LONG);

    private final StringBinding dateString, timeString;

    public LocalDateTimeCellPaneViewModel() {
        dateString = Bindings.createStringBinding(
                () -> dateTime.get() == null || dateFormatStyle.get() == null ? StringUtils.EMPTY : dateTime.get().format(DateTimeFormatter.ofLocalizedDate(dateFormatStyle.get())),
                dateTime, dateFormatStyle
        );
        timeString = Bindings.createStringBinding(
                () -> dateTime.get() == null || timeFormatStyle.get() == null ? StringUtils.EMPTY : ZonedDateTime.of(dateTime.get(), ZoneId.of("Z")).format(DateTimeFormatter.ofLocalizedTime(timeFormatStyle.get())),
                dateTime, timeFormatStyle
        );
    }

    public LocalDateTime getDateTime() {
        return dateTime.get();
    }

    public ObjectProperty<LocalDateTime> dateTimeProperty() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime.set(dateTime);
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

    public FormatStyle getTimeFormatStyle() {
        return timeFormatStyle.get();
    }

    public ObjectProperty<FormatStyle> timeFormatStyleProperty() {
        return timeFormatStyle;
    }

    public void setTimeFormatStyle(FormatStyle timeFormatStyle) {
        this.timeFormatStyle.set(timeFormatStyle);
    }

    public String getDateString() {
        return dateString.get();
    }

    public StringBinding dateStringProperty() {
        return dateString;
    }

    public String getTimeString() {
        return timeString.get();
    }

    public StringBinding timeStringProperty() {
        return timeString;
    }
}
