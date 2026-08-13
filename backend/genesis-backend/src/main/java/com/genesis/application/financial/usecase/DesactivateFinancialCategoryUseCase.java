/**
 * ============================================================================
 * CLASSE: DesactivateFinancialCategoryUseCase
 * ============================================================================
 *
 * CAMADA:
 * Application -> Financial -> UseCase
 *
 * RESPONSABILIDADE:
 *
 * Desativar uma categoria financeira existente.
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
 * Entidade de domínio da categoria financeira.
 */
import com.genesis.domain.financial.FinancialCategory;

/*
 * Exceção lançada quando a categoria não existe.
 */
import com.genesis.domain.exception.FinancialCategoryNotFoundException;

/*
 * Contrato de persistência da categoria.
 */
import com.genesis.domain.repository.FinancialCategoryRepository;

/*
 * Permite que o Spring gerencie este Use Case.
 */
import org.springframework.stereotype.Service;

/*
 * Identificador único da categoria.
 */
import java.util.UUID;

/**
 * Use Case responsável por desativar uma categoria financeira.
 */
@Service
public class DesactivateFinancialCategoryUseCase {

    /*
     * ============================================================================
     * ATRIBUTOS
     * ============================================================================
     */

    /*
     * Repository utilizado para localizar e salvar
     * a categoria.
     */
    private final FinancialCategoryRepository categoryRepository;

    /*
     * ============================================================================
     * CONSTRUTOR
     * ============================================================================
     */
    public DesactivateFinancialCategoryUseCase(
        FinancialCategoryRepository categoryRepository) {

        this.categoryRepository = categoryRepository;
    }

    /*
     * ============================================================================
     * MÉTODO: execute()
     * ============================================================================
     *
     * Responsabilidade:
     *
     * Localizar uma categoria, desativá-la através
     * da entidade de domínio e persistir a alteração.
     */
    public FinancialCategory execute(UUID id) {

        /*
         * Procura a categoria pelo ID.
         *
         * Se não existir, lança a exceção.
         */
        FinancialCategory category =
            categoryRepository
                .findById(id)
                .orElseThrow(() ->
                    new FinancialCategoryNotFoundException(id)
                );

        /*
         * A regra de desativação pertence à entidade
         * FinancialCategory.
         */
        category.deactivate();

        /*
         * Persiste a alteração.
         */
        return categoryRepository.save(category);
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
     * FinancialCategory
     *  ↓
     * category.deactivate()
     *  ↓
     * Repository.save()
     *
     * ============================================================================
     */
}
