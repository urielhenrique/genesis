package com.genesis.domain.exception;

public class InsufficientInventoryException extends RuntimeException {

    public InsufficientInventoryException(
        String productName,
        int currentQuantity,
        int requestedQuantity) {

        super(
            "Insufficient stock for product '" + productName +
                "'. Current quantity: " + currentQuantity +
                ". Requested quantity: " + requestedQuantity + "."
        );
    }

}
