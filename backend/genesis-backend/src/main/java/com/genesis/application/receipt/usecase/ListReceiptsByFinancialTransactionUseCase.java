/**
 * ============================================================================
 * CLASSE: ListReceiptsByFinancialTransactionUseCase
 * ============================================================================
 *
 * CAMADA:
 * Application -> Receipt -> UseCase
 *
 * RESPONSABILIDADE:
 *
 * Listar todos os comprovantes associados a uma movimentação financeira.
 *
 * ============================================================================
 */
package com.genesis.application.receipt.usecase;

import com.genesis.domain.receipt.Receipt;
import com.genesis.domain.repository.ReceiptRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Use Case responsável pela listagem de comprovantes
 * de uma movimentação financeira.
 */
@Service
public class ListReceiptsByFinancialTransactionUseCase {

    /*
     * Repository utilizado para buscar os comprovantes.
     */
    private final ReceiptRepository receiptRepository;

    /*
     * ============================================================================
     * CONSTRUTOR
     * ============================================================================
     */
    public ListReceiptsByFinancialTransactionUseCase(
        ReceiptRepository receiptRepository) {

        this.receiptRepository = receiptRepository;
    }

    /*
     * ============================================================================
     * MÉTODO: execute()
     * ============================================================================
     *
     * Busca todos os comprovantes associados
     * à movimentação financeira informada.
     */
    public List<Receipt> execute(
        UUID financialTransactionId) {

        return receiptRepository
            .findByFinancialTransactionId(
                financialTransactionId
            );
    }
}
