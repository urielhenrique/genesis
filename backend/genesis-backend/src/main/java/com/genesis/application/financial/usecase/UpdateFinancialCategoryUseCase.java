/**
 * ============================================================================
 * CLASSE: UpdateFinancialCategoryUseCase
 * ============================================================================
 *
 * CAMADA:
 * Application -> Financial -> UseCase
 *
 * RESPONSABILIDADE:
 *
 * Atualizar os dados de uma categoria financeira existente.
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
 * DTO com os novos dados da categoria.
 */
import com.genesis.application.financial.dto.UpdateFinancialCategoryRequest;

/*
 * Entidade de domínio da categoria financeira.
 */
import com.genesis.domain.financial.FinancialCategory;

/*
 * Exceção utilizada quando a categoria não existe.
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
 * Identificador da categoria.
 */
import java.util.UUID;

/**
 * Use Case responsável pela atualização de categorias financeiras.
 */
@Service
public class UpdateFinancialCategoryUseCase {

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
    public UpdateFinancialCategoryUseCase(
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
     * Localizar uma categoria existente,
     * atualizar seus dados e persistir a alteração.
     */
    public FinancialCategory execute(
        UUID id,
        UpdateFinancialCategoryRequest request) {

        /*
         * Procura a categoria pelo ID.
         *
         * Caso não exista, lança uma exceção.
         */
        FinancialCategory category =
            categoryRepository
                .findById(id)
                .orElseThrow(() ->
                    new FinancialCategoryNotFoundException(id)
                );

        /*
         * A alteração do nome é realizada pela própria
         * entidade de domínio.
         *
         * Dessa forma, a regra permanece no Domain.
         */
        category.rename(request.getName());

        /*
         * Salva a entidade atualizada.
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
     * ID + Request
     *      ↓
     * Repository.findById()
     *      ↓
     * FinancialCategory
     *      ↓
     * category.rename()
     *      ↓
     * Repository.save()
     *      ↓
     * FinancialCategory
     *
     * ============================================================================
     */
}
