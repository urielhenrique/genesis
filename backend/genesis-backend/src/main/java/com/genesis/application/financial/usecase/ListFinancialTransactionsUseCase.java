/**
 * ============================================================================
 * CLASSE: ListFinancialTransactionsUseCase
 * ============================================================================
 *
 * CAMADA:
 * Application -> Financial -> UseCase
 *
 * RESPONSABILIDADE:
 *
 * Buscar todas as movimentações financeiras cadastradas.
 *
 * Este Use Case apenas coordena a consulta ao Repository.
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
 * Contrato de persistência das movimentações.
 */
import com.genesis.domain.repository.FinancialTransactionRepository;

/*
 * Permite que o Spring gerencie este Use Case.
 */
import org.springframework.stereotype.Service;

/*
 * Utilizado para representar a lista de movimentações.
 */
import java.util.List;

/**
 * Use Case responsável por listar movimentações financeiras.
 */
@Service
public class ListFinancialTransactionsUseCase {

    /*
     * ============================================================================
     * ATRIBUTOS
     * ============================================================================
     */

    /*
     * Repository utilizado para consultar as movimentações.
     */
    private final FinancialTransactionRepository transactionRepository;

    /*
     * ============================================================================
     * CONSTRUTOR
     * ============================================================================
     */
    public ListFinancialTransactionsUseCase(
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
     * Retornar todas as movimentações financeiras.
     */
    public List<FinancialTransaction> execute() {

        /*
         * Delega a consulta para o Repository.
         */
        return transactionRepository.findAll();
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
     * execute()
     *      ↓
     * transactionRepository.findAll()
     *      ↓
     * List<FinancialTransaction>
     *
     * Conceitos:
     *
     * ✔ Use Case
     * ✔ Repository
     * ✔ List
     * ✔ @Service
     *
     * ============================================================================
     */
}
