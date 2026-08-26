package com.genesis.infrastructure.persistence.entity;

import com.genesis.domain.shared.enums.OperationStatus;
import com.genesis.domain.shared.enums.OperationType;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "operations")
public class OperationJpaEntity {

    @Id
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private OperationType type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private OperationStatus status;

    @Column(name = "operation_date", nullable = false)
    private LocalDateTime operationDate;

    @Column(length = 600)
    private String description;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    protected OperationJpaEntity() {
    }

    public OperationJpaEntity(
        UUID id,
        OperationType type,
        OperationStatus status,
        LocalDateTime operationDate,
        String description,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

        this.id = id;
        this.type = type;
        this.status = status;
        this.operationDate = operationDate;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() {
        return id;
    }

    public OperationType getType() {
        return type;
    }

    public OperationStatus getStatus() {
        return status;
    }

    public LocalDateTime getOperationDate() {
        return operationDate;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
