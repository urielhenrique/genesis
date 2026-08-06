package com.genesis.infrastructure.persistence.entity;

import com.genesis.domain.inventory.InventoryMovementReason;
import com.genesis.domain.inventory.InventoryMovementType;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "inventory_movement")
public class InventoryMovementJpaEntity {

    @Id
    private UUID id;

    @Column(name = "inventory_id", nullable = false)
    private UUID inventoryId;

    @Enumerated(EnumType.STRING)
    @Column(name = "movement_type", nullable = false)
    private InventoryMovementType type;

    @Enumerated(EnumType.STRING)
    @Column(name = "movement_reason", nullable = false)
    private InventoryMovementReason reason;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal quantity;

    @Column(length = 500)
    private String notes;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    protected InventoryMovementJpaEntity() {
    }

    public InventoryMovementJpaEntity(
        UUID id,
        UUID inventoryId,
        InventoryMovementType type,
        InventoryMovementReason reason,
        BigDecimal quantity,
        String notes,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

        this.id = id;
        this.inventoryId = inventoryId;
        this.type = type;
        this.reason = reason;
        this.quantity = quantity;
        this.notes = notes;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() {
        return id;
    }

    public UUID getInventoryId() {
        return inventoryId;
    }

    public InventoryMovementType getType() {
        return type;
    }

    public InventoryMovementReason getReason() {
        return reason;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public String getNotes() {
        return notes;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
