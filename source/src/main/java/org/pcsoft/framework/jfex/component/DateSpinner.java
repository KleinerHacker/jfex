package org.pcsoft.framework.jfex.component;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.StringConverter;
import org.pcsoft.framework.jfex.util.EventHandlerUtils;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class DateSpinner extends Spinner<LocalDate> {

    public static final class TimeSpinnerValueFactory extends SpinnerValueFactory<LocalDate> {
        private static final LocalDate MIN_VALUE = LocalDate.MIN;
        private static final LocalDate MAX_VALUE = LocalDate.MAX;
        private static final Duration STEP_VALUE = Duration.ofMinutes(1);
        private static final LocalDate DEFAULT_VALUE = LocalDate.MIN;

        private final ObjectProperty<LocalDate> minimumValue = new SimpleObjectProperty<>(MIN_VALUE), maximumValue = new SimpleObjectProperty<>(MAX_VALUE);
        private final ObjectProperty<Duration> stepValue = new SimpleObjectProperty<>(STEP_VALUE);
        private final ObjectProperty<LocalDate> defaultValue = new SimpleObjectProperty<>(DEFAULT_VALUE);
        private final ObjectProperty<DateTimeFormatter> dateTimeFormatter = new SimpleObjectProperty<>(DateTimeFormatter.ISO_LOCAL_DATE);

        public TimeSpinnerValueFactory() {
            this(MIN_VALUE, MAX_VALUE, STEP_VALUE);
        }

        public TimeSpinnerValueFactory(LocalDate minimumValue, LocalDate maximumValue, Duration stepValue) {
            this.minimumValue.addListener(o -> {
                if (this.minimumValue.get().isAfter(this.maximumValue.get())) {
                    this.minimumValue.setValue(this.maximumValue.get());
                }
                if (getValue() != null && getValue().isBefore(this.minimumValue.get())) {
                    setValue(this.minimumValue.get());
                }
            });
            this.maximumValue.addListener(o -> {
                if (this.maximumValue.get().isBefore(this.minimumValue.get())) {
                    this.maximumValue.set(this.minimumValue.get());
                }
                if (getValue() != null && getValue().isAfter(this.maximumValue.get())) {
                    setValue(this.maximumValue.get());
                }
            });
            this.valueProperty().addListener(o -> {
                if (getValue() == null)
                    return;

                if (getValue().isAfter(this.maximumValue.get())) {
                    setValue(this.maximumValue.get());
                } else if (getValue().isBefore(this.minimumValue.get())) {
                    setValue(this.minimumValue.get());
                }
            });

            setMinimumValue(minimumValue);
            setMaximumValue(maximumValue);
            setValue(minimumValue);
            setStepValue(stepValue);
            setConverter(new StringConverter<LocalDate>() {
                @Override
                public String toString(LocalDate object) {
                    return object == null ? defaultValue.get().format(dateTimeFormatter.get()) : object.format(dateTimeFormatter.get());
                }

                @Override
                public LocalDate fromString(String string) {
                    try {
                        return string == null ? defaultValue.get() : LocalDate.parse(string, dateTimeFormatter.get());
                    } catch (DateTimeParseException e) {
                        return defaultValue.get();
                    }
                }
            });
        }

        @Override
        public void decrement(int steps) {
            if (getValue() == null) {
                setValue(maximumValue.get());
                return;
            }
            final LocalDate newValue = calculateStep(steps, getValue(), false);
            if (getMinimumValue().isAfter(newValue)) {
                return;
            }
            setValue(newValue);
        }

        @Override
        public void increment(int steps) {
            if (getValue() == null) {
                setValue(minimumValue.get());
                return;
            }
            final LocalDate newValue = calculateStep(steps, getValue(), true);
            if (getMaximumValue().isBefore(newValue)) {
                return;
            }
            setValue(newValue);
        }

        private LocalDate calculateStep(int steps, LocalDate value, boolean add) {
            LocalDate result = value;
            for (int i = 0; i < steps; i++) {
                if (add) {
                    result = result.plus(stepValue.get());
                } else {
                    result = result.minus(stepValue.get());
                }
            }
            
            return result;
        }

        public LocalDate getMinimumValue() {
            return minimumValue.get();
        }

        public ObjectProperty<LocalDate> minimumValueProperty() {
            return minimumValue;
        }

        public void setMinimumValue(LocalDate minimumValue) {
            this.minimumValue.set(minimumValue);
        }

        public LocalDate getMaximumValue() {
            return maximumValue.get();
        }

        public ObjectProperty<LocalDate> maximumValueProperty() {
            return maximumValue;
        }

        public void setMaximumValue(LocalDate maximumValue) {
            this.maximumValue.set(maximumValue);
        }

        public Duration getStepValue() {
            return stepValue.get();
        }

        public ObjectProperty<Duration> stepValueProperty() {
            return stepValue;
        }

        public void setStepValue(Duration stepValue) {
            this.stepValue.set(stepValue);
        }

        public LocalDate getDefaultValue() {
            return defaultValue.get();
        }

        public ObjectProperty<LocalDate> defaultValueProperty() {
            return defaultValue;
        }

        public void setDefaultValue(LocalDate defaultValue) {
            this.defaultValue.set(defaultValue);
        }

        public DateTimeFormatter getDateTimeFormatter() {
            return dateTimeFormatter.get();
        }

        public ObjectProperty<DateTimeFormatter> dateTimeFormatterProperty() {
            return dateTimeFormatter;
        }

        public void setDateTimeFormatter(DateTimeFormatter dateTimeFormatter) {
            this.dateTimeFormatter.set(dateTimeFormatter);
        }
    }

    private final TimeSpinnerValueFactory valueFactory;

    public DateSpinner() {
        valueFactory = new TimeSpinnerValueFactory();
        setValueFactory(valueFactory);
        addEventFilter(KeyEvent.KEY_TYPED, EventHandlerUtils.SpinnerHandlers.createNumericIntegerInputRestrictionHandler());
        getEditor().textProperty().addListener((v, o, n) -> {
            this.fireEvent(new KeyEvent(KeyEvent.KEY_PRESSED, null, null, KeyCode.ENTER, false, false, false, false));
        });
        setEditable(true);
    }

    public LocalDate getMinimumValue() {
        return valueFactory.getMinimumValue();
    }

    public ObjectProperty<LocalDate> minimumValueProperty() {
        return valueFactory.minimumValueProperty();
    }

    public void setMinimumValue(LocalDate minimumValue) {
        valueFactory.setMinimumValue(minimumValue);
    }

    public LocalDate getMaximumValue() {
        return valueFactory.getMaximumValue();
    }

    public ObjectProperty<LocalDate> maximumValueProperty() {
        return valueFactory.maximumValueProperty();
    }

    public void setMaximumValue(LocalDate maximumValue) {
        valueFactory.setMaximumValue(maximumValue);
    }

    public Duration getStepValue() {
        return valueFactory.getStepValue();
    }

    public ObjectProperty<Duration> stepValueProperty() {
        return valueFactory.stepValueProperty();
    }

    public void setStepValue(Duration stepValue) {
        valueFactory.setStepValue(stepValue);
    }

    public LocalDate getDefaultValue() {
        return valueFactory.getDefaultValue();
    }

    public ObjectProperty<LocalDate> defaultValueProperty() {
        return valueFactory.defaultValueProperty();
    }

    public void setDefaultValue(LocalDate defaultValue) {
        valueFactory.setDefaultValue(defaultValue);
    }

    public DateTimeFormatter getDateTimeFormatter() {
        return valueFactory.getDateTimeFormatter();
    }

    public ObjectProperty<DateTimeFormatter> dateTimeFormatterProperty() {
        return valueFactory.dateTimeFormatterProperty();
    }

    public void setDateTimeFormatter(DateTimeFormatter dateTimeFormatter) {
        valueFactory.setDateTimeFormatter(dateTimeFormatter);
    }
}
