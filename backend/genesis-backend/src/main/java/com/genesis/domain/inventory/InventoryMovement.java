package com.genesis.domain.inventory;

import com.genesis.domain.shared.entity.BaseEntity;
import com.genesis.domain.shared.valueobject.Money;
import com.genesis.domain.shared.valueobject.Quantity;

import java.time.LocalDateTime;

public class InventoryMovement extends BaseEntity {

    private final Inventory inventory;

    private final InventoryMovementType type;

    private final InventoryMovementReason reason;

    private final Quantity quantity;

    private final String observation;

    private final LocalDateTime movementDate;

    public InventoryMovement(
            Inventory inventory,
            InventoryMovementType type,
            InventoryMovementReason reason,
            Quantity quantity,
            String observation,
            LocalDateTime movementDate) {

        if (inventory == null) {
            throw new IllegalArgumentException("Inventory is required.");
        }

        if (type == null) {
            throw new IllegalArgumentException("Movement type is required.");
        }

        if (reason == null) {
            throw new IllegalArgumentException("Movement reason is required.");
        }

        if (quantity == null) {
            throw new IllegalArgumentException("Quantity is required.");
        }

        if (movementDate == null) {
            throw new IllegalArgumentException("Movement date is required.");
        }

        this.inventory = inventory;
        this.type = type;
        this.reason = reason;
        this.quantity = quantity;
        this.observation = observation;
        this.movementDate = movementDate;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public InventoryMovementType getType() {
        return type;
    }

    public InventoryMovementReason getReason() {
        return reason;
    }

    public Quantity getQuantity() {
        return quantity;
    }

    public String getObservation() {
        return observation;
    }

    public LocalDateTime getMovementDate() {
        return movementDate;
    }


}
