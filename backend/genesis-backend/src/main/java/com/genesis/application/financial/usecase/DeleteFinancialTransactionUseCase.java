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

/*
 * Contrato de persistência das movimentações.
 */
import com.genesis.domain.repository.FinancialTransactionRepository;

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

    /*
     * ============================================================================
     * CONSTRUTOR
     * ============================================================================
     */
    public DeleteFinancialTransactionUseCase(
        FinancialTransactionRepository transactionRepository) {

        this.transactionRepository = transactionRepository;
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

        /*
         * Procura a movimentação pelo ID.
         *
         * Se não existir, lança a exceção.
         */
        FinancialTransaction transaction =
            transactionRepository
                .findById(id)
                .orElseThrow(() ->
                    new FinancialTransactionNotFoundException(id)
                );

        /*
         * Remove a movimentação através do Repository.
         */
        transactionRepository.delete(transaction);
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
