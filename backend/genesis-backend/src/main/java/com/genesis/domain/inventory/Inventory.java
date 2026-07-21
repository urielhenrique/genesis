package com.genesis.domain.inventory;

import com.genesis.domain.product.Product;
import com.genesis.domain.shared.entity.BaseEntity;
import com.genesis.domain.shared.valueobject.Quantity;

public class Inventory extends BaseEntity {

    private Product product;
    private Quantity quantity;

    public Inventory(Product product, Quantity quantity) {

        if (product == null) {
            throw new IllegalArgumentException("Product is required.");
        }

        if (quantity == null) {
            throw new IllegalArgumentException("Quantity is required.");
        }

        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public Quantity getQuantity() {
        return quantity;
    }

    public void increase(Quantity quantity) {

        if (quantity == null) {
            throw new IllegalArgumentException("Quantity is required.");
        }

        this.quantity = this.quantity.add(quantity);
        touch();
    }

    public void decrease(Quantity quantity) {

        if (quantity == null) {
            throw new IllegalArgumentException("Quantity is required.");
        }

        this.quantity = this.quantity.subtract(quantity);
        touch();
    }
}
