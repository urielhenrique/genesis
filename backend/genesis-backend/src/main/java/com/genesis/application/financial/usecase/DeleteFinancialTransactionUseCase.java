/**
 * ============================================================================
 * CLASSE: DeleteFinancialTransactionUseCase
 * ============================================================================
 *
 * CAMADA:
 * Application -> Financial -> UseCase
 *
 * RESPONSABILIDADE:
 *
 * Remover uma movimentação financeira existente.
 *
 * ============================================================================
 */
package com.genesis.application.financial.usecase;

/*
 * ============================================================================
 * IMPORTS
 * ============================================================================
 */

/*
 * Entidade de domínio da movimentação financeira.
 */
import com.genesis.domain.financial.FinancialTransaction;

/*
 * Exceção lançada quando a movimentação não existe.
 */
import com.genesis.domain.exception.FinancialTransactionNotFoundException;
import com.genesis.domain.exception.FinancialTransactionHasReceiptsException;

/*
 * Contrato de persistência das movimentações.
 */
import com.genesis.domain.repository.FinancialTransactionRepository;
import com.genesis.domain.repository.ReceiptRepository;
import com.genesis.domain.receipt.Receipt;

import java.util.List;
/*
 * Permite que o Spring gerencie este Use Case.
 */
import org.springframework.stereotype.Service;

/*
 * Identificador único da movimentação.
 */
import java.util.UUID;

/**
 * Use Case responsável pela exclusão de movimentações financeiras.
 */
@Service
public class DeleteFinancialTransactionUseCase {

    /*
     * ============================================================================
     * ATRIBUTOS
     * ============================================================================
     */

    /*
     * Repository utilizado para localizar e excluir
     * a movimentação.
     */
    private final FinancialTransactionRepository transactionRepository;

    private final ReceiptRepository receiptRepository;

    /*
     * ============================================================================
     * CONSTRUTOR
     * ============================================================================
     */
    public DeleteFinancialTransactionUseCase(
        FinancialTransactionRepository transactionRepository,
        ReceiptRepository receiptRepository) {

        this.transactionRepository = transactionRepository;
        this.receiptRepository = receiptRepository;
    }

    /*
     * ============================================================================
     * MÉTODO: execute()
     * ============================================================================
     *
     * Responsabilidade:
     *
     * Localizar uma movimentação e removê-la.
     */
    public void execute(UUID id) {

        FinancialTransaction transaction =
            transactionRepository
                .findById(id)
                .orElseThrow(() ->
                    new FinancialTransactionNotFoundException(id)
                );

        List<Receipt> receipts =
            receiptRepository.findByFinancialTransactionId(id);

        if (!receipts.isEmpty()) {
            throw new FinancialTransactionHasReceiptsException();
        }

        /*
         * Exclusão lógica.
         *
         * O registro continua no banco, mas fica inativo.
         */
        transaction.deactivate();

        /*
         * Persiste active = false.
         */
        transactionRepository.save(transaction);
    }

    /*
     * ============================================================================
     * RESUMO
     * ============================================================================
     *
     * Fluxo:
     *
     * ID
     *  ↓
     * Repository.findById()
     *  ↓
     * FinancialTransaction
     *  ↓
     * Repository.delete()
     *
     * Conceitos:
     *
     * ✔ Use Case
     * ✔ Repository
     * ✔ Optional
     * ✔ orElseThrow()
     * ✔ Exclusão de entidade
     *
     * ============================================================================
     */
}
