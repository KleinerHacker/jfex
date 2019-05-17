package org.pcsoft.framework.jfex.controls.ui.component;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.StringConverter;
import org.pcsoft.framework.jfex.controls.util.EventHandlerUtils;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class TimeSpinner extends Spinner<LocalTime> {

    public static final class TimeSpinnerValueFactory extends SpinnerValueFactory<LocalTime> {
        private static final LocalTime MIN_VALUE = LocalTime.MIN;
        private static final LocalTime MAX_VALUE = LocalTime.MAX;
        private static final Duration STEP_VALUE = Duration.ofMinutes(1);
        private static final LocalTime DEFAULT_VALUE = LocalTime.MIN;

        private final ObjectProperty<LocalTime> minimumValue = new SimpleObjectProperty<>(MIN_VALUE), maximumValue = new SimpleObjectProperty<>(MAX_VALUE);
        private final ObjectProperty<Duration> stepValue = new SimpleObjectProperty<>(STEP_VALUE);
        private final ObjectProperty<LocalTime> defaultValue = new SimpleObjectProperty<>(DEFAULT_VALUE);
        private final ObjectProperty<DateTimeFormatter> dateTimeFormatter = new SimpleObjectProperty<>(DateTimeFormatter.ISO_LOCAL_TIME);

        public TimeSpinnerValueFactory() {
            this(MIN_VALUE, MAX_VALUE, STEP_VALUE);
        }

        public TimeSpinnerValueFactory(LocalTime minimumValue, LocalTime maximumValue, Duration stepValue) {
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
            setConverter(new StringConverter<LocalTime>() {
                @Override
                public String toString(LocalTime object) {
                    return object == null ? defaultValue.get().format(dateTimeFormatter.get()) : object.format(dateTimeFormatter.get());
                }

                @Override
                public LocalTime fromString(String string) {
                    try {
                        return string == null ? defaultValue.get() : LocalTime.parse(string, dateTimeFormatter.get());
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
            final LocalTime newValue = calculateStep(steps, getValue(), false);
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
            final LocalTime newValue = calculateStep(steps, getValue(), true);
            if (getMaximumValue().isBefore(newValue)) {
                return;
            }
            setValue(newValue);
        }

        private LocalTime calculateStep(int steps, LocalTime value, boolean add) {
            LocalTime result = value;
            for (int i = 0; i < steps; i++) {
                if (add) {
                    result = result.plus(stepValue.get());
                } else {
                    result = result.minus(stepValue.get());
                }
            }
            return result;
        }

        public LocalTime getMinimumValue() {
            return minimumValue.get();
        }

        public ObjectProperty<LocalTime> minimumValueProperty() {
            return minimumValue;
        }

        public void setMinimumValue(LocalTime minimumValue) {
            this.minimumValue.set(minimumValue);
        }

        public LocalTime getMaximumValue() {
            return maximumValue.get();
        }

        public ObjectProperty<LocalTime> maximumValueProperty() {
            return maximumValue;
        }

        public void setMaximumValue(LocalTime maximumValue) {
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

        public LocalTime getDefaultValue() {
            return defaultValue.get();
        }

        public ObjectProperty<LocalTime> defaultValueProperty() {
            return defaultValue;
        }

        public void setDefaultValue(LocalTime defaultValue) {
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

    public TimeSpinner() {
        valueFactory = new TimeSpinnerValueFactory();
        setValueFactory(valueFactory);
        addEventFilter(KeyEvent.KEY_TYPED, EventHandlerUtils.SpinnerHandlers.createNumericIntegerInputRestrictionHandler());
        getEditor().textProperty().addListener((v, o, n) -> {
            this.fireEvent(new KeyEvent(KeyEvent.KEY_PRESSED, null, null, KeyCode.ENTER, false, false, false, false));
        });
        setEditable(true);
    }

    public LocalTime getMinimumValue() {
        return valueFactory.getMinimumValue();
    }

    public ObjectProperty<LocalTime> minimumValueProperty() {
        return valueFactory.minimumValueProperty();
    }

    public void setMinimumValue(LocalTime minimumValue) {
        valueFactory.setMinimumValue(minimumValue);
    }

    public LocalTime getMaximumValue() {
        return valueFactory.getMaximumValue();
    }

    public ObjectProperty<LocalTime> maximumValueProperty() {
        return valueFactory.maximumValueProperty();
    }

    public void setMaximumValue(LocalTime maximumValue) {
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

    public LocalTime getDefaultValue() {
        return valueFactory.getDefaultValue();
    }

    public ObjectProperty<LocalTime> defaultValueProperty() {
        return valueFactory.defaultValueProperty();
    }

    public void setDefaultValue(LocalTime defaultValue) {
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
