package com.genesis.domain.product;

import com.genesis.domain.shared.entity.BaseEntity;
import com.genesis.domain.shared.valueobject.Money;

public class Product extends BaseEntity {

    private String name;
    private String description;
    private Money unitPrice;
    private ProductType type;
    private boolean active;

    public Product(
        String name,
        String description,
        Money unitPrice,
        ProductType type) {

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Product name is required.");
        }

        if (unitPrice == null) {
            throw new IllegalArgumentException("Unit price is required.");
        }

        if (type == null) {
            throw new IllegalArgumentException("Product type is required.");
        }

        this.name = name.trim();
        this.description = description;
        this.unitPrice = unitPrice;
        this.type = type;
        this.active = true;

    }

    public Product(
        java.util.UUID id,
        java.time.LocalDateTime createdAt,
        java.time.LocalDateTime updatedAt,
        String name,
        String description,
        Money unitPrice,
        ProductType type,
        boolean active) {

        super(id, createdAt, updatedAt);

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Product name is required.");
        }

        if (unitPrice == null) {
            throw new IllegalArgumentException("Unit price is required.");
        }

        if (type == null) {
            throw new IllegalArgumentException("Product type is required.");
        }

        this.name = name.trim();
        this.description = description;
        this.unitPrice = unitPrice;
        this.type = type;
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Money getUnitPrice() {
        return unitPrice;
    }

    public ProductType getType() {
        return type;
    }

    public boolean isActive() {
        return active;
    }

    public void rename(String newName) {

        if (newName == null || newName.isBlank()) {
            throw new IllegalArgumentException("Product name is required.");
        }

        this.name = newName.trim();
        touch();
    }

    public void changeDescription(String newDescription) {
        this.description = newDescription;
        touch();
    }

    public void changePrice(Money newPrice) {

        if (newPrice == null) {
            throw new IllegalArgumentException("Unit price is required.");
        }

        this.unitPrice = newPrice;
        touch();
    }

    public void activate() {
        this.active = true;
        touch();
    }

    public void deactivate() {
        this.active = false;
        touch();
    }

    public void changeType(ProductType newType) {

        if (newType == null) {
            throw new IllegalArgumentException("Product type is required.");
        }

        this.type = newType;

        touch();
    }

}
