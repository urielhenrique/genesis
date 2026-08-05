package com.genesis.domain.shared.valueobject;

import java.math.BigDecimal;
import java.util.Objects;

public final class Quantity {

    public static final Quantity ZERO = new Quantity(BigDecimal.ZERO);

    private final BigDecimal value;

    public Quantity(BigDecimal value) {

        if (value == null) {
            throw new IllegalArgumentException("Quantity cannot be null.");
        }

        if (value.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative.");
        }

        this.value = value;
    }

    public BigDecimal getValue() {
        return value;
    }

    public Quantity add(Quantity other) {

        if (other == null) {
            throw new IllegalArgumentException("Quantity cannot be null.");
        }

        return new Quantity(this.value.add(other.value));
    }

    public Quantity subtract(Quantity other) {

        if (other == null) {
            throw new IllegalArgumentException("Quantity cannot be null.");
        }

        return new Quantity(this.value.subtract(other.value));
    }

    public boolean isZero() {
        return value.compareTo(BigDecimal.ZERO) == 0;
    }

    public boolean isPositive() {
        return value.compareTo(BigDecimal.ZERO) > 0;
    }

    public boolean isGreaterThan(Quantity other) {

        if (other == null) {
            throw new IllegalArgumentException("Quantity cannot be null.");
        }

        return value.compareTo(other.value) > 0;
    }

    public boolean isGreaterThanOrEqual(Quantity other) {

        if (other == null) {
            throw new IllegalArgumentException("Quantity cannot be null.");
        }

        return value.compareTo(other.value) >= 0;
    }

    public boolean isLessThan(Quantity other) {

        if (other == null) {
            throw new IllegalArgumentException("Quantity cannot be null.");
        }

        return value.compareTo(other.value) < 0;
    }

    @Override
    public boolean equals(Object object) {

        if (this == object) {
            return true;
        }

        if (!(object instanceof Quantity quantity)) {
            return false;
        }

        return Objects.equals(value, quantity.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
