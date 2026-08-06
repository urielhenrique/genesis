package com.genesis.application.inventory.dto;

import com.genesis.domain.product.ProductType;

import java.math.BigDecimal;
import java.util.UUID;

public class InventoryResponse {

    private final UUID productId;
    private final String productName;
    private final ProductType productType;
    private final BigDecimal quantity;

    public InventoryResponse(
        UUID productId,
        String productName,
        ProductType productType,
        BigDecimal quantity) {

        this.productId = productId;
        this.productName = productName;
        this.productType = productType;
        this.quantity = quantity;
    }

    public UUID getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public ProductType getProductType() {
        return productType;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }
}
