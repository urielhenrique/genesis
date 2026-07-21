package com.genesis.domain.shared.valueobject;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public final class Money {

    private final BigDecimal value;

    public Money(BigDecimal value) {

        if (value == null) {
            throw new IllegalArgumentException("Money cannot be null.");
        }

        if (value.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Money cannot be negative.");
        }

        this.value = value.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getValue() {
        return value;
    }

    public Money add(Money other) {

        if (other == null) {
            throw new IllegalArgumentException("Money cannot be null.");
        }

        return new Money(this.value.add(other.value));
    }

    public Money subtract(Money other) {

        if (other == null) {
            throw new IllegalArgumentException("Money cannot be null.");
        }

        Money result = new Money(this.value.subtract(other.value));

        return result;
    }

    public Money multiply(BigDecimal quantity) {

        if (quantity == null) {
            throw new IllegalArgumentException("Quantity cannot be null.");
        }

        return new Money(this.value.multiply(quantity));
    }

    @Override
    public boolean equals(Object object) {

        if (this == object) {
            return true;
        }

        if (!(object instanceof Money money)) {
            return false;
        }

        return Objects.equals(value, money.value);
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
