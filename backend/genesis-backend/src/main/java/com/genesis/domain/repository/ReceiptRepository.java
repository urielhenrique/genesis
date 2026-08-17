/**
 * ============================================================================
 * INTERFACE: ReceiptRepository
 * ============================================================================
 *
 * CAMADA:
 * Domain -> Repository
 *
 * RESPONSABILIDADE:
 *
 * Definir o contrato de persistência dos comprovantes.
 *
 * O Domain conhece apenas esta interface.
 * A implementação ficará na Infrastructure.
 *
 * ============================================================================
 */
package com.genesis.domain.repository;

import com.genesis.domain.receipt.Receipt;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Contrato de persistência dos comprovantes.
 */
public interface ReceiptRepository {

    /*
     * ============================================================================
     * MÉTODO: save()
     * ============================================================================
     *
     * Salva ou atualiza um comprovante.
     */
    Receipt save(Receipt receipt);

    /*
     * ============================================================================
     * MÉTODO: findById()
     * ============================================================================
     *
     * Busca um comprovante pelo ID.
     */
    Optional<Receipt> findById(UUID id);

    /*
     * ============================================================================
     * MÉTODO: findByFinancialTransactionId()
     * ============================================================================
     *
     * Busca todos os comprovantes associados
     * a uma movimentação financeira.
     */
    List<Receipt> findByFinancialTransactionId(
        UUID financialTransactionId
    );

    /*
     * ============================================================================
     * MÉTODO: delete()
     * ============================================================================
     *
     * Remove um comprovante.
     */
    void delete(Receipt receipt);
}
