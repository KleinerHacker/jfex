package org.pcsoft.framework.jfex.controls.ui.component;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.StringConverter;
import org.pcsoft.framework.jfex.controls.util.EventHandlerUtils;


public class DoubleSpinner extends Spinner<Double> {

    public static final class DoubleSpinnerValueFactory extends SpinnerValueFactory<Double> {
        private static final double MIN_VALUE = 0d;
        private static final double MAX_VALUE = 100d;
        private static final double STEP_VALUE = .0001d;
        private static final double DEFAULT_VALUE = 0d;

        private final DoubleProperty minimumValue = new SimpleDoubleProperty(MIN_VALUE), maximumValue = new SimpleDoubleProperty(MAX_VALUE),
                stepValue = new SimpleDoubleProperty(STEP_VALUE);
        private final ObjectProperty<Double> defaultValue = new SimpleObjectProperty<>(DEFAULT_VALUE);

        public DoubleSpinnerValueFactory() {
            this(MIN_VALUE, MAX_VALUE, STEP_VALUE);
        }

        public DoubleSpinnerValueFactory(Double minimumValue, Double maximumValue, Double stepValue) {
            this.minimumValue.addListener(o -> {
                if (this.minimumValue.get() > this.maximumValue.get()) {
                    this.minimumValue.setValue(this.maximumValue.get());
                }
                if (getValue() != null && getValue() < this.minimumValue.get()) {
                    setValue(this.minimumValue.get());
                }
            });
            this.maximumValue.addListener(o -> {
                if (this.maximumValue.get() < this.minimumValue.get()) {
                    this.maximumValue.set(this.minimumValue.get());
                }
                if (getValue() != null && getValue() > this.maximumValue.get()) {
                    setValue(this.maximumValue.get());
                }
            });
            this.valueProperty().addListener(o -> {
                if (getValue() == null)
                    return;

                if (getValue() > this.maximumValue.get()) {
                    setValue(this.maximumValue.get());
                } else if (getValue() < this.minimumValue.get()) {
                    setValue(this.minimumValue.get());
                }
            });

            setMinimumValue(minimumValue);
            setMaximumValue(maximumValue);
            setValue(minimumValue);
            setStepValue(stepValue);
            setConverter(new StringConverter<Double>() {
                @Override
                public String toString(Double object) {
                    return String.valueOf(object == null ? defaultValue.get() : object);
                }

                @Override
                public Double fromString(String string) {
                    try {
                        return string == null ? defaultValue.get() : Double.parseDouble(string);
                    } catch (NumberFormatException e) {
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
            if (getMinimumValue() > getValue() - calculateStep(steps)) {
                return;
            }
            setValue(getValue() - calculateStep(steps));
        }

        @Override
        public void increment(int steps) {
            if (getValue() == null) {
                setValue(minimumValue.get());
                return;
            }
            if (getMaximumValue() < getValue() + calculateStep(steps)) {
                return;
            }
            setValue(getValue() + calculateStep(steps));
        }

        private double calculateStep(int steps) {
            return steps * stepValue.get();
        }

        public double getMinimumValue() {
            return minimumValue.get();
        }

        public DoubleProperty minimumValueProperty() {
            return minimumValue;
        }

        public void setMinimumValue(double minimumValue) {
            this.minimumValue.set(minimumValue);
        }

        public double getMaximumValue() {
            return maximumValue.get();
        }

        public DoubleProperty maximumValueProperty() {
            return maximumValue;
        }

        public void setMaximumValue(double maximumValue) {
            this.maximumValue.set(maximumValue);
        }

        public double getStepValue() {
            return stepValue.get();
        }

        public DoubleProperty stepValueProperty() {
            return stepValue;
        }

        public void setStepValue(double stepValue) {
            this.stepValue.set(stepValue);
        }

        public Double getDefaultValue() {
            return defaultValue.get();
        }

        public ObjectProperty<Double> defaultValueProperty() {
            return defaultValue;
        }

        public void setDefaultValue(Double defaultValue) {
            this.defaultValue.set(defaultValue);
        }
    }

    private final DoubleSpinnerValueFactory valueFactory;

    public DoubleSpinner() {
        valueFactory = new DoubleSpinnerValueFactory();
        setValueFactory(valueFactory);
        addEventFilter(KeyEvent.KEY_TYPED, EventHandlerUtils.SpinnerHandlers.createNumericDecimalInputRestrictionHandler());
        getEditor().textProperty().addListener((v, o, n) -> {
            this.fireEvent(new KeyEvent(KeyEvent.KEY_PRESSED, null, null, KeyCode.ENTER, false, false, false, false));
        });
        setEditable(true);
    }

    public double getMinimumValue() {
        return valueFactory.getMinimumValue();
    }

    public DoubleProperty minimumValueProperty() {
        return valueFactory.minimumValueProperty();
    }

    public void setMinimumValue(double minimumValue) {
        valueFactory.setMinimumValue(minimumValue);
    }

    public double getMaximumValue() {
        return valueFactory.getMaximumValue();
    }

    public DoubleProperty maximumValueProperty() {
        return valueFactory.maximumValueProperty();
    }

    public void setMaximumValue(double maximumValue) {
        valueFactory.setMaximumValue(maximumValue);
    }

    public double getStepValue() {
        return valueFactory.getStepValue();
    }

    public DoubleProperty stepValueProperty() {
        return valueFactory.stepValueProperty();
    }

    public void setStepValue(double stepValue) {
        valueFactory.setStepValue(stepValue);
    }

    public Double getDefaultValue() {
        return valueFactory.getDefaultValue();
    }

    public ObjectProperty<Double> defaultValueProperty() {
        return valueFactory.defaultValueProperty();
    }

    public void setDefaultValue(Double defaultValue) {
        valueFactory.setDefaultValue(defaultValue);
    }
}
