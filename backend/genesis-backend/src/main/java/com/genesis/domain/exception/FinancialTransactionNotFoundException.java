/**
 * ============================================================================
 * CLASSE: FinancialTransactionNotFoundException
 * ============================================================================
 *
 * CAMADA:
 * Domain -> Exception
 *
 * RESPONSABILIDADE:
 *
 * Representar o erro que ocorre quando uma movimentação
 * financeira não é encontrada pelo seu identificador.
 *
 * ============================================================================
 */
package com.genesis.domain.exception;

/*
 * Identificador único da movimentação.
 */
import java.util.UUID;

/**
 * Exceção lançada quando uma movimentação financeira
 * não existe.
 */
public class FinancialTransactionNotFoundException
    extends RuntimeException {

    /*
     * ============================================================================
     * CONSTRUTOR
     * ============================================================================
     *
     * Recebe o ID da movimentação que não foi encontrada.
     */
    public FinancialTransactionNotFoundException(UUID id) {

        /*
         * Define a mensagem da exceção.
         */
        super("Financial transaction not found: " + id);
    }
}
