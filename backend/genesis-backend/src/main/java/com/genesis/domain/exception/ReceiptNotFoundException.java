/**
 * ============================================================================
 * CLASSE: ReceiptNotFoundException
 * ============================================================================
 *
 * CAMADA:
 * Domain -> Exception
 *
 * RESPONSABILIDADE:
 *
 * Representar o erro quando um comprovante não é encontrado.
 *
 * ============================================================================
 */
package com.genesis.domain.exception;

import java.util.UUID;

/**
 * Exceção lançada quando um comprovante não existe.
 */
public class ReceiptNotFoundException extends RuntimeException {

    public ReceiptNotFoundException(UUID id) {

        super("Receipt not found: " + id);
    }
}
