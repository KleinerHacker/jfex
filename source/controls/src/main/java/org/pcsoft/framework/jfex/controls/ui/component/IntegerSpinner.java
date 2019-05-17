package org.pcsoft.framework.jfex.controls.ui.component;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.StringConverter;
import org.pcsoft.framework.jfex.controls.util.EventHandlerUtils;


public class IntegerSpinner extends Spinner<Integer> {

    public static final class IntegerSpinnerValueFactory extends SpinnerValueFactory<Integer> {
        private static final int MIN_VALUE = 0;
        private static final int MAX_VALUE = 100;
        private static final int STEP_VALUE = 1;
        private static final int DEFAULT_VALUE = 0;

        private final IntegerProperty minimumValue = new SimpleIntegerProperty(MIN_VALUE), maximumValue = new SimpleIntegerProperty(MAX_VALUE),
                stepValue = new SimpleIntegerProperty(STEP_VALUE);
        private final ObjectProperty<Integer> defaultValue = new SimpleObjectProperty<>(DEFAULT_VALUE);

        public IntegerSpinnerValueFactory() {
            this(MIN_VALUE, MAX_VALUE, STEP_VALUE);
        }

        public IntegerSpinnerValueFactory(Integer minimumValue, Integer maximumValue, Integer stepValue) {
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
            setConverter(new StringConverter<Integer>() {
                @Override
                public String toString(Integer object) {
                    return String.valueOf(object == null ? defaultValue.get() : object);
                }

                @Override
                public Integer fromString(String string) {
                    try {
                        return string == null ? defaultValue.get() : Integer.parseInt(string);
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

        private int calculateStep(int steps) {
            return steps * stepValue.get();
        }

        public int getMinimumValue() {
            return minimumValue.get();
        }

        public IntegerProperty minimumValueProperty() {
            return minimumValue;
        }

        public void setMinimumValue(int minimumValue) {
            this.minimumValue.set(minimumValue);
        }

        public int getMaximumValue() {
            return maximumValue.get();
        }

        public IntegerProperty maximumValueProperty() {
            return maximumValue;
        }

        public void setMaximumValue(int maximumValue) {
            this.maximumValue.set(maximumValue);
        }

        public int getStepValue() {
            return stepValue.get();
        }

        public IntegerProperty stepValueProperty() {
            return stepValue;
        }

        public void setStepValue(int stepValue) {
            this.stepValue.set(stepValue);
        }

        public Integer getDefaultValue() {
            return defaultValue.get();
        }

        public ObjectProperty<Integer> defaultValueProperty() {
            return defaultValue;
        }

        public void setDefaultValue(Integer defaultValue) {
            this.defaultValue.set(defaultValue);
        }
    }

    private final IntegerSpinnerValueFactory valueFactory;

    public IntegerSpinner() {
        valueFactory = new IntegerSpinnerValueFactory();
        setValueFactory(valueFactory);
        addEventFilter(KeyEvent.KEY_TYPED, EventHandlerUtils.SpinnerHandlers.createNumericIntegerInputRestrictionHandler());
        getEditor().textProperty().addListener((v, o, n) -> {
            this.fireEvent(new KeyEvent(KeyEvent.KEY_PRESSED, null, null, KeyCode.ENTER, false, false, false, false));
        });
        setEditable(true);
    }

    public int getMinimumValue() {
        return valueFactory.getMinimumValue();
    }

    public IntegerProperty minimumValueProperty() {
        return valueFactory.minimumValueProperty();
    }

    public void setMinimumValue(int minimumValue) {
        valueFactory.setMinimumValue(minimumValue);
    }

    public int getMaximumValue() {
        return valueFactory.getMaximumValue();
    }

    public IntegerProperty maximumValueProperty() {
        return valueFactory.maximumValueProperty();
    }

    public void setMaximumValue(int maximumValue) {
        valueFactory.setMaximumValue(maximumValue);
    }

    public int getStepValue() {
        return valueFactory.getStepValue();
    }

    public IntegerProperty stepValueProperty() {
        return valueFactory.stepValueProperty();
    }

    public void setStepValue(int stepValue) {
        valueFactory.setStepValue(stepValue);
    }

    public Integer getDefaultValue() {
        return valueFactory.getDefaultValue();
    }

    public ObjectProperty<Integer> defaultValueProperty() {
        return valueFactory.defaultValueProperty();
    }

    public void setDefaultValue(Integer defaultValue) {
        valueFactory.setDefaultValue(defaultValue);
    }
}
