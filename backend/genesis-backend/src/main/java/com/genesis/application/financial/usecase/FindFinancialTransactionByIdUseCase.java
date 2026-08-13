/**
 * ============================================================================
 * CLASSE: FindFinancialTransactionByIdUseCase
 * ============================================================================
 *
 * CAMADA:
 * Application -> Financial -> UseCase
 *
 * RESPONSABILIDADE:
 *
 * Buscar uma movimentação financeira pelo seu identificador.
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
 * Use Case responsável por buscar uma movimentação pelo ID.
 */
@Service
public class FindFinancialTransactionByIdUseCase {

    /*
     * ============================================================================
     * ATRIBUTOS
     * ============================================================================
     */

    /*
     * Repository utilizado para localizar a movimentação.
     */
    private final FinancialTransactionRepository transactionRepository;

    /*
     * ============================================================================
     * CONSTRUTOR
     * ============================================================================
     */
    public FindFinancialTransactionByIdUseCase(
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
     * Localizar uma movimentação financeira pelo ID.
     *
     * Caso ela não exista, lança uma exceção.
     */
    public FinancialTransaction execute(UUID id) {

        return transactionRepository
            .findById(id)
            .orElseThrow(() ->
                new FinancialTransactionNotFoundException(id)
            );
    }

    /*
     * ============================================================================
     * RESUMO
     * ============================================================================
     *
     * Fluxo:
     *
     * Controller
     *      ↓
     * execute(id)
     *      ↓
     * Repository.findById()
     *      ↓
     * FinancialTransaction
     *
     * Se não encontrar:
     *
     * FinancialTransactionNotFoundException
     *
     * Conceitos:
     *
     * ✔ Use Case
     * ✔ Repository
     * ✔ Optional
     * ✔ orElseThrow()
     * ✔ Exception
     *
     * ============================================================================
     */
}
