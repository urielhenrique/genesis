package com.genesis.application.product.dto;

import com.genesis.domain.product.ProductType;

import java.math.BigDecimal;

public class CreateProductRequest {

    private final String name;
    private final String description;
    private final BigDecimal unitPrice;
    private final ProductType type;

    public CreateProductRequest(
        String name,
        String description,
        BigDecimal unitPrice,
        ProductType type) {

        this.name = name;
        this.description = description;
        this.unitPrice = unitPrice;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public ProductType getType() {
        return type;
    }
}
