package org.pcsoft.framework.jfex.mvvm;

import javafx.beans.binding.*;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.commons.lang.ObjectUtils;
import org.pcsoft.framework.jfex.commons.util.Properties;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

public abstract class FxmlTypedViewModel<T> implements FxmlViewModel {
    private final ObjectProperty<T> value = new SimpleObjectProperty<>();
    private final boolean invalidateOnValueUpdate;

    public FxmlTypedViewModel() {
        this(true);
    }

    public FxmlTypedViewModel(boolean invalidateOnValueUpdate) {
        this.invalidateOnValueUpdate = invalidateOnValueUpdate;
    }

    public T getValue() {
        return value.get();
    }

    public ObjectProperty<T> valueProperty() {
        return value;
    }

    public void setValue(T value) {
        this.value.set(value);
    }

    //<editor-fold desc="Bindings">
    protected final StringBinding createStringBinding(Function<T, String> function) {
        return createStringBinding("", function);
    }

    protected final StringBinding createStringBinding(String defaultValue, Function<T, String> function) {
        return Bindings.createStringBinding(() -> value.get() == null ? defaultValue : function.apply(value.get()), value);
    }

    protected final IntegerBinding createIntegerBinding(Function<T, Integer> function) {
        return createIntegerBinding(0, function);
    }

    protected final IntegerBinding createIntegerBinding(int defaultValue, Function<T, Integer> function) {
        return Bindings.createIntegerBinding(() -> value.get() == null ? defaultValue : (int) ObjectUtils.defaultIfNull(function.apply(value.get()), defaultValue), value);
    }

    protected final BooleanBinding createBooleanBinding(Function<T, Boolean> function) {
        return createBooleanBinding(false, function);
    }

    protected final BooleanBinding createBooleanBinding(boolean defaultValue, Function<T, Boolean> function) {
        return Bindings.createBooleanBinding(() -> value.get() == null ? defaultValue : (boolean) ObjectUtils.defaultIfNull(function.apply(value.get()), defaultValue), value);
    }

    protected final LongBinding createLongBinding(Function<T, Long> function) {
        return createLongBinding(0, function);
    }

    protected final LongBinding createLongBinding(long defaultValue, Function<T, Long> function) {
        return Bindings.createLongBinding(() -> value.get() == null ? defaultValue : (long) ObjectUtils.defaultIfNull(function.apply(value.get()), defaultValue), value);
    }

    protected final DoubleBinding createDoubleBinding(Function<T, Double> function) {
        return createDoubleBinding(0, function);
    }

    protected final DoubleBinding createDoubleBinding(double defaultValue, Function<T, Double> function) {
        return Bindings.createDoubleBinding(() -> value.get() == null ? defaultValue : (double) ObjectUtils.defaultIfNull(function.apply(value.get()), defaultValue), value);
    }

    protected final <O> ObjectBinding<O> createObjectBinding(Function<T, O> function) {
        return createObjectBinding(null, function);
    }

    protected final <O> ObjectBinding<O> createObjectBinding(O defaultValue, Function<T, O> function) {
        return Bindings.createObjectBinding(() -> value.get() == null ? defaultValue : function.apply(value.get()), value);
    }

    protected final <O> ListBinding<O> createListBinding(Function<T, List<O>> function) {
        return createListBinding(FXCollections.emptyObservableList(), function);
    }

    protected final <O> ListBinding<O> createListBinding(List<O> defaultValue, Function<T, List<O>> function) {
        return new ListBinding<>() {
            {
                invalidate();
                value.addListener(o -> invalidate());
            }

            @Override
            protected ObservableList<O> computeValue() {
                return FXCollections.observableArrayList(value.get() == null ? defaultValue : function.apply(value.get()));
            }
        };
    }
    //</editor-fold>

    //<editor-fold desc="Properties">
    protected final StringProperty createStringProperty(Function<T, String> getter, BiConsumer<T, String> setter) {
        return createStringProperty(null, getter, setter);
    }

    protected final StringProperty createStringProperty(String defaultValue, Function<T, String> getter, BiConsumer<T, String> setter) {
        return new SimpleStringProperty(defaultValue) {
            {
                invalidated();
                value.addListener(o -> invalidated());
            }

            @Override
            public String get() {
                return getValue();
            }

            @Override
            public void set(String newValue) {
                setValue(newValue);
            }

            @Override
            public void setValue(String v) {
                if (value.get() == null)
                    return;

                setter.accept(value.get(), v);
                if (invalidateOnValueUpdate) {
                    invalidated();
                }
            }

            @Override
            public String getValue() {
                if (value.get() == null)
                    return defaultValue;

                return getter.apply(value.get());
            }
        };
    }

    protected final IntegerProperty createIntegerProperty(Function<T, Integer> getter, BiConsumer<T, Integer> setter) {
        return createIntegerProperty(0, getter, setter);
    }

    protected final IntegerProperty createIntegerProperty(int defaultValue, Function<T, Integer> getter, BiConsumer<T, Integer> setter) {
        return new SimpleIntegerProperty(defaultValue) {
            {
                invalidated();
                value.addListener(o -> invalidated());
            }

            @Override
            public int get() {
                return (int) ObjectUtils.defaultIfNull(getValue(), defaultValue);
            }

            @Override
            public void set(int newValue) {
                setValue(newValue);
            }

            @Override
            public void setValue(Number v) {
                if (value.get() == null)
                    return;

                setter.accept(value.get(), v == null ? defaultValue : v.intValue());
                if (invalidateOnValueUpdate) {
                    invalidated();
                }
            }

            @Override
            public Integer getValue() {
                if (value.get() == null)
                    return defaultValue;

                return getter.apply(value.get());
            }
        };
    }

    protected final LongProperty createLongProperty(Function<T, Long> getter, BiConsumer<T, Long> setter) {
        return createLongProperty(0, getter, setter);
    }

    protected final LongProperty createLongProperty(long defaultValue, Function<T, Long> getter, BiConsumer<T, Long> setter) {
        return new SimpleLongProperty(defaultValue) {
            {
                invalidated();
                value.addListener(o -> invalidated());
            }

            @Override
            public long get() {
                return (long) ObjectUtils.defaultIfNull(getValue(), defaultValue);
            }

            @Override
            public void set(long newValue) {
                setValue(newValue);
            }

            @Override
            public void setValue(Number v) {
                if (value.get() == null)
                    return;

                setter.accept(value.get(), v == null ? defaultValue : v.longValue());
                if (invalidateOnValueUpdate) {
                    invalidated();
                }
            }

            @Override
            public Long getValue() {
                if (value.get() == null)
                    return defaultValue;

                return getter.apply(value.get());
            }
        };
    }

    protected final DoubleProperty createDoubleProperty(Function<T, Double> getter, BiConsumer<T, Double> setter) {
        return createDoubleProperty(0, getter, setter);
    }

    protected final DoubleProperty createDoubleProperty(double defaultValue, Function<T, Double> getter, BiConsumer<T, Double> setter) {
        return new SimpleDoubleProperty(defaultValue) {
            {
                invalidated();
                value.addListener(o -> invalidated());
            }

            @Override
            public double get() {
                return (double) ObjectUtils.defaultIfNull(getValue(), defaultValue);
            }

            @Override
            public void set(double newValue) {
                setValue(newValue);
            }

            @Override
            public void setValue(Number v) {
                if (value.get() == null)
                    return;

                setter.accept(value.get(), v == null ? defaultValue : v.doubleValue());
                if (invalidateOnValueUpdate) {
                    invalidated();
                }
            }

            @Override
            public Double getValue() {
                if (value.get() == null)
                    return defaultValue;

                return getter.apply(value.get());
            }
        };
    }

    protected final BooleanProperty createBooleanProperty(Function<T, Boolean> getter, BiConsumer<T, Boolean> setter) {
        return createBooleanProperty(false, getter, setter);
    }

    protected final BooleanProperty createBooleanProperty(boolean defaultValue, Function<T, Boolean> getter, BiConsumer<T, Boolean> setter) {
        return new SimpleBooleanProperty(defaultValue) {
            {
                invalidated();
                value.addListener(o -> invalidated());
            }

            @Override
            public boolean get() {
                return (boolean) ObjectUtils.defaultIfNull(getValue(), defaultValue);
            }

            @Override
            public void set(boolean newValue) {
                setValue(newValue);
            }

            @Override
            public void setValue(Boolean v) {
                if (value.get() == null)
                    return;

                setter.accept(value.get(), v == null ? defaultValue : v);
                if (invalidateOnValueUpdate) {
                    invalidated();
                }
            }

            @Override
            public Boolean getValue() {
                if (value.get() == null)
                    return defaultValue;

                return getter.apply(value.get());
            }
        };
    }

    protected final <O> ObjectProperty<O> createObjectProperty(Function<T, O> getter, BiConsumer<T, O> setter) {
        return createObjectProperty(null, getter, setter);
    }

    protected final <O> ObjectProperty<O> createObjectProperty(O defaultValue, Function<T, O> getter, BiConsumer<T, O> setter) {
        return new SimpleObjectProperty<O>(defaultValue) {
            {
                invalidated();
                value.addListener(o -> invalidated());
            }

            @Override
            public O get() {
                return getValue();
            }

            @Override
            public void set(Object newValue) {
                setValue(newValue);
            }

            @Override
            public void setValue(Object v) {
                if (value.get() == null)
                    return;

                setter.accept(value.get(), (O) v);
                if (invalidateOnValueUpdate) {
                    invalidated();
                }
            }

            @Override
            public O getValue() {
                if (value.get() == null)
                    return defaultValue;

                return getter.apply(value.get());
            }
        };
    }
    //</editor-fold>
}
