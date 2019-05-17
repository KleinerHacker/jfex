package org.pcsoft.framework.jfex.commons.property;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;


public class ConstraintNumberProperty<T extends Number> extends ConstraintProperty<T> {
    public static abstract class NumberConstraint<N extends Number> {
        public final Function<N, N> buildBasicConstraint() {
            return v -> v == null ? null : buildConstraint().apply(v);
        }

        protected abstract Function<N, N> buildConstraint();
    }

    public static final class MinimumConstraint<N extends Number> extends NumberConstraint<N> {
        private final N minValue;

        public MinimumConstraint(N minValue) {
            this.minValue = minValue;
        }

        @Override
        protected Function<N, N> buildConstraint() {
            return v -> v.doubleValue() < minValue.doubleValue() ? minValue : v;
        }
    }

    public static final class MaximumConstraint<N extends Number> extends NumberConstraint<N> {
        private final N maxValue;

        public MaximumConstraint(N maxValue) {
            this.maxValue = maxValue;
        }

        @Override
        protected Function<N, N> buildConstraint() {
            return v -> v.doubleValue() > maxValue.doubleValue() ? maxValue : v;
        }
    }

    public static class MultiConstraint<N extends Number> extends NumberConstraint<N> {
        private final List<NumberConstraint<N>> numberConstraintList = new ArrayList<>();

        @SafeVarargs
        public MultiConstraint(NumberConstraint<N>... constraints) {
            this(Arrays.asList(constraints));
        }

        public MultiConstraint(Collection<NumberConstraint<N>> collection) {
            numberConstraintList.addAll(collection);
        }

        @Override
        protected Function<N, N> buildConstraint() {
            return v -> {
                N current = v;
                for (final NumberConstraint<N> numberConstraint : numberConstraintList) {
                    current = numberConstraint.buildConstraint().apply(current);
                }
                return current;
            };
        }
    }

    public static final class RangeConstraint<N extends Number> extends MultiConstraint<N> {
        public RangeConstraint(final N minValue, final N maxValue) {
            super(new MinimumConstraint<>(minValue), new MaximumConstraint<>(maxValue));
        }
    }

    public static final class NearestNumberConstraint<N extends Number> extends NumberConstraint<N> {
        private final List<N> numberList = new ArrayList<N>();

        @SafeVarargs
        public NearestNumberConstraint(N... numbers) {
            this(Arrays.asList(numbers));
        }

        public NearestNumberConstraint(final Collection<N> numbers) {
            numberList.addAll(numbers);
        }

        @Override
        protected Function<N, N> buildConstraint() {
            return v -> {
                int nearestIndex = -1;
                double difference = Double.MAX_VALUE;
                for (int i=0; i<numberList.size(); i++) {
                    final double currentDifference = Math.abs(v.doubleValue() - numberList.get(i).doubleValue());
                    if (currentDifference < difference) {
                        difference = currentDifference;
                        nearestIndex = i;
                    }
                }

                return nearestIndex < 0 ? v : numberList.get(nearestIndex);
            };
        }
    }

    public ConstraintNumberProperty(Function<T, T> constraint) {
        super(constraint);
    }

    public ConstraintNumberProperty(Function<T, T> constraint, T value) {
        super(constraint, value);
    }

    public ConstraintNumberProperty(NumberConstraint<T> numberConstraint) {
        super(numberConstraint.buildBasicConstraint());
    }

    public ConstraintNumberProperty(NumberConstraint<T> numberConstraint, T value) {
        super(numberConstraint.buildBasicConstraint(), value);
    }
}
