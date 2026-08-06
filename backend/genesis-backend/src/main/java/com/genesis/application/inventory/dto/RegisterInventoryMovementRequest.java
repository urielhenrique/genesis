package com.genesis.application.inventory.dto;

import com.genesis.domain.inventory.InventoryMovementReason;
import com.genesis.domain.inventory.InventoryMovementType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.UUID;

public class RegisterInventoryMovementRequest {

    @NotNull(message = "Product is required.")
    private UUID productId;

    @NotNull(message = "Movement type is required.")
    private InventoryMovementType movementType;

    @NotNull(message = "Movement reason is required.")
    private InventoryMovementReason movementReason;

    @NotNull(message = "Quantity is required.")
    @Positive(message = "Quantity must be greater than zero.")
    private BigDecimal quantity;

    private String notes;

    public RegisterInventoryMovementRequest(
        UUID productId,
        InventoryMovementType movementType,
        InventoryMovementReason movementReason,
        BigDecimal quantity,
        String notes) {

        this.productId = productId;
        this.movementType = movementType;
        this.movementReason = movementReason;
        this.quantity = quantity;
        this.notes = notes;
    }

    public UUID getProductId() {
        return productId;
    }

    public InventoryMovementType getMovementType() {
        return movementType;
    }

    public InventoryMovementReason getMovementReason() {
        return movementReason;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public String getNotes() {
        return notes;
    }
}
