package com.genesis.application.product.dto;

import com.genesis.domain.product.ProductType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public class CreateProductRequest {

    @NotBlank(message = "Product name is required.")
    @Size(max = 120)
    private String name;
    @Size(max = 500)
    private String description;
    @NotNull(message = "Unit price is required.")
    @Positive(message = "Unit price must be greater than zero.")
    private BigDecimal unitPrice;
    @NotNull(message = "Product type is required.")
    private ProductType type;

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
