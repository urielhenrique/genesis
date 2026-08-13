/**
 * ============================================================================
 * CLASSE: UpdateFinancialTransactionUseCase
 * ============================================================================
 *
 * CAMADA:
 * Application -> Financial -> UseCase
 *
 * RESPONSABILIDADE:
 *
 * Atualizar uma movimentação financeira existente.
 *
 * O Use Case:
 *
 * • Localiza a movimentação.
 * • Localiza a nova categoria.
 * • Atualiza os dados através da entidade de domínio.
 * • Persiste a alteração.
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
 * DTO contendo os novos dados da movimentação.
 */
import com.genesis.application.financial.dto.UpdateFinancialTransactionRequest;

/*
 * Exceção lançada quando a movimentação não existe.
 */

/*
 * Exceção lançada quando a categoria não existe.
 */
import com.genesis.domain.exception.FinancialCategoryNotFoundException;

/*
 * Entidade de domínio da categoria.
 */
import com.genesis.domain.exception.FinancialTransactionNotFoundException;
import com.genesis.domain.financial.FinancialCategory;

/*
 * Entidade de domínio da movimentação.
 */
import com.genesis.domain.financial.FinancialTransaction;

/*
 * Repository utilizado para localizar a categoria.
 */
import com.genesis.domain.repository.FinancialCategoryRepository;

/*
 * Repository utilizado para localizar e salvar
 * a movimentação.
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

/*
 * Identificador único da movimentação.
 */
import java.util.UUID;

/**
 * Use Case responsável pela atualização de movimentações financeiras.
 */
@Service
public class UpdateFinancialTransactionUseCase {

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
     * Repository utilizado para localizar a categoria.
     */
    private final FinancialCategoryRepository categoryRepository;

    /*
     * ============================================================================
     * CONSTRUTOR
     * ============================================================================
     */
    public UpdateFinancialTransactionUseCase(
        FinancialTransactionRepository transactionRepository,
        FinancialCategoryRepository categoryRepository) {

        this.transactionRepository = transactionRepository;
        this.categoryRepository = categoryRepository;
    }

    /*
     * ============================================================================
     * MÉTODO: execute()
     * ============================================================================
     *
     * Responsabilidade:
     *
     * Localizar a movimentação, atualizar seus dados
     * e persistir a alteração.
     */
    public FinancialTransaction execute(
        UUID id,
        UpdateFinancialTransactionRequest request) {

        /*
         * Procura a movimentação pelo ID.
         *
         * Se não existir, lança uma exceção.
         */
        FinancialTransaction transaction =
            transactionRepository
                .findById(id)
                .orElseThrow(() ->
                    new FinancialTransactionNotFoundException(id)
                );

        /*
         * Procura a nova categoria.
         *
         * A movimentação não pode ser associada
         * a uma categoria inexistente.
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
         * Converte o novo valor para o Value Object Money.
         */
        Money amount =
            new Money(request.getAmount());

        /*
         * Atualiza a descrição através da entidade.
         */
        transaction.changeDescription(
            request.getDescription()
        );

        /*
         * Atualiza o valor através da entidade.
         */
        transaction.changeAmount(amount);

        /*
         * Atualiza a categoria através da entidade.
         */
        transaction.changeCategory(category);

        /*
         * Atualiza a forma de pagamento.
         */
        transaction.changePaymentMethod(
            request.getPaymentMethod()
        );

        /*
         * Atualiza a data da movimentação.
         */
        transaction.changeTransactionDate(
            request.getTransactionDate()
        );

        /*
         * Atualiza as observações.
         */
        transaction.changeNotes(
            request.getNotes()
        );

        /*
         * O tipo da movimentação também precisa ser atualizado.
         *
         * Para isso, a entidade FinancialTransaction
         * ainda precisa de um método changeType().
         */
        transaction.changeType(
            request.getType()
        );

        /*
         * Persiste a entidade atualizada.
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
     * ID + Request
     *      ↓
     * Busca Transaction
     *      ↓
     * Busca Category
     *      ↓
     * Cria Money
     *      ↓
     * Atualiza Domain Entity
     *      ↓
     * Repository.save()
     *      ↓
     * FinancialTransaction
     *
     * ============================================================================
     */
}
