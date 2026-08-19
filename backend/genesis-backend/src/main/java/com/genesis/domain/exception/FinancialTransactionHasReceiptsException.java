package com.genesis.domain.exception;

public class FinancialTransactionHasReceiptsException
    extends RuntimeException {

    public FinancialTransactionHasReceiptsException() {
        super(
            "Financial transaction cannot be deleted because it has receipts associated with it."
        );
    }
}
