package com.genesis.domain.operation;

import com.genesis.domain.product.Product;
import com.genesis.domain.shared.entity.BaseEntity;
import com.genesis.domain.shared.valueobject.Money;
import com.genesis.domain.shared.valueobject.Quantity;


public class OperationItem extends BaseEntity {

    private Product product;
    private Quantity quantity;
    private Money unitPrice;

    public OperationItem(
        Product product,
        Quantity quantity,
        Money unitPrice) {

        if (product == null) {
            throw new IllegalArgumentException("Product is required.");
        }

        if (quantity == null) {
            throw new IllegalArgumentException("Quantity is required.");
        }

        if (unitPrice == null) {
            throw new IllegalArgumentException("Unit price is required.");
        }

        this.product = product;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public Product getProduct() {
        return product;
    }

    public Quantity getQuantity() {
        return quantity;
    }

    public Money getUnitPrice() {
        return unitPrice;
    }

    public Money getTotalPrice() {
        return unitPrice.multiply(quantity.getValue());
    }

}
