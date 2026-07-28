package com.genesis.domain.operation;

import com.genesis.domain.shared.entity.BaseEntity;
import com.genesis.domain.shared.enums.OperationStatus;
import com.genesis.domain.shared.enums.OperationType;
import com.genesis.domain.shared.valueobject.Money;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Operation extends BaseEntity {

    private OperationType type;

    private OperationStatus status;

    private LocalDateTime operationDate;

    private String description;

    private final List<OperationItem> items;

    public Operation(
        OperationType type,
        LocalDateTime operationDate,
        String description) {

        if (type == null) {
            throw new IllegalArgumentException("Operation type is required.");
        }

        if (operationDate == null) {
            throw new IllegalArgumentException("Operation date is required.");
        }

        this.type = type;
        this.operationDate = operationDate;
        this.description = description;
        this.status = OperationStatus.DRAFT;
        this.items = new ArrayList<>();
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

    public List<OperationItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public Money getTotalAmount() {

        Money total = new Money(BigDecimal.ZERO);

        for (OperationItem item : items) {
            total = total.add(item.getTotalPrice());
        }

        return total;
    }

    public void changeDescription(String description) {

        validateDraft();

        this.description = description;
        touch();
    }

    public void changeOperationDate(LocalDateTime operationDate) {

        if (operationDate == null) {
            throw new IllegalArgumentException("Operation date is required.");
        }

        validateDraft();

        this.operationDate = operationDate;
        touch();
    }

    public void addItem(OperationItem item) {

        validateDraft();

        if (item == null) {
            throw new IllegalArgumentException("Operation item is required.");
        }

        this.items.add(item);
        touch();
    }

    public void removeItem(OperationItem item) {

        validateDraft();

        if (item == null) {
            throw new IllegalArgumentException("Operation item is required.");
        }

        this.items.remove(item);
        touch();
    }

    public void confirm() {

        validateDraft();

        this.status = OperationStatus.CONFIRMED;
        touch();
    }

    public void cancel() {

        validateDraft();

        this.status = OperationStatus.CANCELED;
        touch();
    }

    private void validateDraft() {

        if (status != OperationStatus.DRAFT) {
            throw new IllegalStateException(
                "Only operations in DRAFT status can be changed."
            );
        }
    }

}
