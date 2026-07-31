package com.genesis.domain.shared.entity;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public abstract class BaseEntity {

    private final UUID id;

    private final LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    /**
     * Construtor utilizado para criar novas entidades.
     */
    protected BaseEntity() {
        this(
            UUID.randomUUID(),
            LocalDateTime.now(),
            LocalDateTime.now()
        );
    }

    /**
     * Construtor utilizado para reconstruir entidades vindas do banco.
     */
    protected BaseEntity(
        UUID id,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

        if (id == null) {
            throw new IllegalArgumentException("Id is required.");
        }

        if (createdAt == null) {
            throw new IllegalArgumentException("CreatedAt is required.");
        }

        if (updatedAt == null) {
            throw new IllegalArgumentException("UpdatedAt is required.");
        }

        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() {
        return id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    protected void touch() {
        updatedAt = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object object) {

        if (this == object) {
            return true;
        }

        if (!(object instanceof BaseEntity entity)) {
            return false;
        }

        return Objects.equals(id, entity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
