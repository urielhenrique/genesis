package com.genesis.domain.exception;

public class ReceiptFileNotFoundException extends RuntimeException {

    public ReceiptFileNotFoundException(String fileName) {
        super("Receipt file not found: " + fileName);
    }
}
