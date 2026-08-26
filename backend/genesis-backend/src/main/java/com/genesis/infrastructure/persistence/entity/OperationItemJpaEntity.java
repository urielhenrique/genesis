package com.genesis.infrastructure.persistence.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "operation_items")
public class OperationItemJpaEntity {

    @Id
    private UUID id;

    @Column(name = "operation_id", nullable = false)
    private UUID operationId;

    @Column(name = "product_id", nullable = false)
    private UUID productId;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal quantity;

    @Column(name = "unit_price", nullable = false, precision = 19, scale = 2)
    private BigDecimal unitPrice;

    @Column(name = "created_at", nullable = false)
    private java.time.LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private java.time.LocalDateTime updatedAt;

    protected OperationItemJpaEntity() {
    }

    public OperationItemJpaEntity(
        UUID id,
        UUID operationId,
        UUID productId,
        BigDecimal quantity,
        BigDecimal unitPrice,
        java.time.LocalDateTime createdAt,
        java.time.LocalDateTime updatedAt) {

        this.id = id;
        this.operationId = operationId;
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() {
        return id;
    }

    public UUID getOperationId() {
        return operationId;
    }

    public UUID getProductId() {
        return productId;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public java.time.LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public java.time.LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
