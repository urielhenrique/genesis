/**
 * ============================================================================
 * ENUM: PaymentMethod
 * ============================================================================
 *
 * CAMADA:
 * Domain -> Financial
 *
 * RESPONSABILIDADE:
 *
 * Representar a forma utilizada para realizar
 * um pagamento ou recebimento.
 *
 * ============================================================================
 */
package com.genesis.domain.financial;

/*
 * ============================================================================
 * VALORES POSSÍVEIS
 * ============================================================================
 *
 * Cada constante representa uma forma de pagamento
 * ou recebimento utilizada pelo Genesis.
 */
public enum PaymentMethod {

    /*
     * Dinheiro em espécie.
     */
    CASH,

    /*
     * Pagamento ou recebimento via PIX.
     */
    PIX,

    /*
     * Transferência bancária.
     */
    BANK_TRANSFER,

    /*
     * Cartão de crédito.
     */
    CREDIT_CARD,

    /*
     * Cartão de débito.
     */
    DEBIT_CARD

}
