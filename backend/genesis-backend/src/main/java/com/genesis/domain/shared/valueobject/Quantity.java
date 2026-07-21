package com.genesis.domain.shared.valueobject;

import java.math.BigDecimal;
import java.util.Objects;

public final class Quantity {

    private final BigDecimal value;

    public Quantity(BigDecimal value) {

        if (value == null) {
            throw new IllegalArgumentException("Quantity cannot be null.");
        }

        if (value.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero.");
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
