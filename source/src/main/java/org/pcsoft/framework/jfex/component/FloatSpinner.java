package org.pcsoft.framework.jfex.component;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.StringConverter;
import org.pcsoft.framework.jfex.util.EventHandlerUtils;


public class FloatSpinner extends Spinner<Float> {

    public static final class FloatSpinnerValueFactory extends SpinnerValueFactory<Float> {
        private static final float MIN_VALUE = 0f;
        private static final float MAX_VALUE = 100f;
        private static final float STEP_VALUE = .0001f;
        private static final float DEFAULT_VALUE = 0f;

        private final FloatProperty minimumValue = new SimpleFloatProperty(MIN_VALUE), maximumValue = new SimpleFloatProperty(MAX_VALUE),
                stepValue = new SimpleFloatProperty(STEP_VALUE);
        private final ObjectProperty<Float> defaultValue = new SimpleObjectProperty<>(DEFAULT_VALUE);

        public FloatSpinnerValueFactory() {
            this(MIN_VALUE, MAX_VALUE, STEP_VALUE);
        }

        public FloatSpinnerValueFactory(Float minimumValue, Float maximumValue, Float stepValue) {
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
            setConverter(new StringConverter<Float>() {
                @Override
                public String toString(Float object) {
                    return String.valueOf(object == null ? defaultValue.get() : object);
                }

                @Override
                public Float fromString(String string) {
                    try {
                        return string == null ? defaultValue.get() : Float.parseFloat(string);
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

        private float calculateStep(int steps) {
            return steps * stepValue.get();
        }

        public float getMinimumValue() {
            return minimumValue.get();
        }

        public FloatProperty minimumValueProperty() {
            return minimumValue;
        }

        public void setMinimumValue(float minimumValue) {
            this.minimumValue.set(minimumValue);
        }

        public float getMaximumValue() {
            return maximumValue.get();
        }

        public FloatProperty maximumValueProperty() {
            return maximumValue;
        }

        public void setMaximumValue(float maximumValue) {
            this.maximumValue.set(maximumValue);
        }

        public float getStepValue() {
            return stepValue.get();
        }

        public FloatProperty stepValueProperty() {
            return stepValue;
        }

        public void setStepValue(float stepValue) {
            this.stepValue.set(stepValue);
        }

        public Float getDefaultValue() {
            return defaultValue.get();
        }

        public ObjectProperty<Float> defaultValueProperty() {
            return defaultValue;
        }

        public void setDefaultValue(Float defaultValue) {
            this.defaultValue.set(defaultValue);
        }
    }

    private final FloatSpinnerValueFactory valueFactory;

    public FloatSpinner() {
        valueFactory = new FloatSpinnerValueFactory();
        setValueFactory(valueFactory);
        addEventFilter(KeyEvent.KEY_TYPED, EventHandlerUtils.SpinnerHandlers.createNumericDecimalInputRestrictionHandler());
        getEditor().textProperty().addListener((v, o, n) -> {
            this.fireEvent(new KeyEvent(KeyEvent.KEY_PRESSED, null, null, KeyCode.ENTER, false, false, false, false));
        });
        setEditable(true);
    }

    public float getMinimumValue() {
        return valueFactory.getMinimumValue();
    }

    public FloatProperty minimumValueProperty() {
        return valueFactory.minimumValueProperty();
    }

    public void setMinimumValue(float minimumValue) {
        valueFactory.setMinimumValue(minimumValue);
    }

    public float getMaximumValue() {
        return valueFactory.getMaximumValue();
    }

    public FloatProperty maximumValueProperty() {
        return valueFactory.maximumValueProperty();
    }

    public void setMaximumValue(float maximumValue) {
        valueFactory.setMaximumValue(maximumValue);
    }

    public float getStepValue() {
        return valueFactory.getStepValue();
    }

    public FloatProperty stepValueProperty() {
        return valueFactory.stepValueProperty();
    }

    public void setStepValue(float stepValue) {
        valueFactory.setStepValue(stepValue);
    }

    public Float getDefaultValue() {
        return valueFactory.getDefaultValue();
    }

    public ObjectProperty<Float> defaultValueProperty() {
        return valueFactory.defaultValueProperty();
    }

    public void setDefaultValue(Float defaultValue) {
        valueFactory.setDefaultValue(defaultValue);
    }
}
