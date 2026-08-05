package com.genesis.infrastructure.persistence.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

    @Entity
    @Table(name = "inventory")
    public class InventoryJpaEntity {

        @Id
        private UUID id;

        @Column(name = "product_id", nullable = false)
        private UUID productId;

        @Column(nullable = false, precision = 19, scale = 2)
        private BigDecimal quantity;

        @Column(nullable = false)
        private LocalDateTime createdAt;

        @Column(nullable = false)
        private LocalDateTime updatedAt;

        protected InventoryJpaEntity() {
        }

        public InventoryJpaEntity(
            UUID id,
            UUID productId,
            BigDecimal quantity,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {

            this.id = id;
            this.productId = productId;
            this.quantity = quantity;
            this.createdAt = createdAt;
            this.updatedAt = updatedAt;
        }

        public UUID getId() {
            return id;
        }

        public UUID getProductId() {
            return productId;
        }

        public BigDecimal getQuantity() {
            return quantity;
        }

        public LocalDateTime getCreatedAt() {
            return createdAt;
        }

        public LocalDateTime getUpdatedAt() {
            return updatedAt;
        }
    }
