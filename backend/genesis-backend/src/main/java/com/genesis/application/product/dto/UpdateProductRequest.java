package com.genesis.application.product.dto;

import com.genesis.domain.product.ProductType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public class UpdateProductRequest {

    @NotBlank(message = "Product name is required.")
    @Size(max = 120)
    private final String name;
    @Size(max = 500)
    private final String description;
    @NotNull(message = "Unit price is required.")
    @Positive(message = "Unit price must be greater than zero.")
    private final BigDecimal unitPrice;
    @NotNull(message = "Product type is required.")
    private final ProductType type;

    public UpdateProductRequest(
        String name,
        String description,
        BigDecimal unitPrice,
        ProductType type) {

        this.name = name == null ? null : name.trim();
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

