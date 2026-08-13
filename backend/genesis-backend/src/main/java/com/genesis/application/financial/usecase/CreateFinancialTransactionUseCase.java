/**
 * ============================================================================
 * CLASSE: CreateFinancialTransactionUseCase
 * ============================================================================
 *
 * CAMADA:
 * Application -> Financial -> UseCase
 *
 * RESPONSABILIDADE:
 *
 * Criar uma nova movimentação financeira.
 *
 * O Use Case:
 *
 * • Recebe o DTO da requisição.
 * • Localiza a categoria.
 * • Cria o Money.
 * • Cria a entidade FinancialTransaction.
 * • Persiste a movimentação.
 *
 * ============================================================================
 *
 * FLUXO:
 *
 * CreateFinancialTransactionRequest
 *              ↓
 * CreateFinancialTransactionUseCase
 *              ↓
 * FinancialCategoryRepository
 *              ↓
 * FinancialTransaction
 *              ↓
 * FinancialTransactionRepository
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
 * DTO que contém os dados enviados pelo cliente.
 */
import com.genesis.application.financial.dto.CreateFinancialTransactionRequest;

/*
 * Exceção lançada quando a categoria não existe.
 */
import com.genesis.domain.exception.FinancialCategoryNotFoundException;

/*
 * Entidade que representa a categoria financeira.
 */
import com.genesis.domain.financial.FinancialCategory;

/*
 * Entidade que representa a movimentação financeira.
 */
import com.genesis.domain.financial.FinancialTransaction;

/*
 * Repository utilizado para localizar a categoria.
 */
import com.genesis.domain.repository.FinancialCategoryRepository;

/*
 * Repository utilizado para salvar a movimentação.
 */
import com.genesis.domain.repository.FinancialTransactionRepository;

/*
 * Value Object utilizado para representar dinheiro.
 */
import com.genesis.domain.shared.valueobject.Money;

/*
 * Permite que o Spring gerencie este Use Case.
 */
import org.springframework.stereotype.Service;

/**
 * Use Case responsável pela criação de movimentações financeiras.
 */
@Service
public class CreateFinancialTransactionUseCase {

    /*
     * ============================================================================
     * ATRIBUTOS
     * ============================================================================
     */

    /*
     * Repository utilizado para localizar a categoria.
     */
    private final FinancialCategoryRepository categoryRepository;

    /*
     * Repository utilizado para salvar a movimentação.
     */
    private final FinancialTransactionRepository transactionRepository;

    /*
     * ============================================================================
     * CONSTRUTOR
     * ============================================================================
     *
     * O Spring injeta automaticamente os Repositories.
     */
    public CreateFinancialTransactionUseCase(
        FinancialCategoryRepository categoryRepository,
        FinancialTransactionRepository transactionRepository) {

        this.categoryRepository = categoryRepository;
        this.transactionRepository = transactionRepository;
    }

    /*
     * ============================================================================
     * MÉTODO: execute()
     * ============================================================================
     *
     * Responsabilidade:
     *
     * Executar o processo completo de criação
     * de uma movimentação financeira.
     */
    public FinancialTransaction execute(
        CreateFinancialTransactionRequest request) {

        /*
         * Procura a categoria informada na requisição.
         *
         * A movimentação precisa estar associada
         * a uma categoria existente.
         */
        FinancialCategory category =
            categoryRepository
                .findById(request.getCategoryId())
                .orElseThrow(() ->
                    new FinancialCategoryNotFoundException(
                        request.getCategoryId()
                    )
                );

        /*
         * Converte o BigDecimal recebido pelo DTO
         * para o Value Object Money do domínio.
         */
        Money amount =
            new Money(request.getAmount());

        /*
         * Cria a entidade de domínio.
         *
         * As regras da FinancialTransaction
         * são executadas pelo próprio domínio.
         */
        FinancialTransaction transaction =
            new FinancialTransaction(
                request.getDescription(),
                amount,
                request.getType(),
                category,
                request.getPaymentMethod(),
                request.getTransactionDate(),
                request.getNotes()
            );

        /*
         * Persiste a movimentação através do
         * contrato definido no domínio.
         */
        return transactionRepository.save(transaction);
    }

    /*
     * ============================================================================
     * RESUMO
     * ============================================================================
     *
     * Fluxo:
     *
     * Request
     *   ↓
     * Busca Category
     *   ↓
     * Cria Money
     *   ↓
     * Cria FinancialTransaction
     *   ↓
     * Repository.save()
     *   ↓
     * FinancialTransaction
     *
     * Conceitos:
     *
     * ✔ Use Case
     * ✔ DTO → Domain
     * ✔ Repository
     * ✔ Value Object
     * ✔ Money
     * ✔ Entidade de domínio
     * ✔ Exceção de domínio
     * ✔ Injeção de dependência
     *
     * ============================================================================
     */
}
