/**
 * ============================================================================
 * CLASSE: FindFinancialCategoryByIdUseCase
 * ============================================================================
 *
 * CAMADA:
 * Application -> Financial -> UseCase
 *
 * RESPONSABILIDADE:
 *
 * Buscar uma categoria financeira pelo seu identificador.
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
 * Use Case responsável por buscar uma categoria pelo ID.
 */
@Service
public class FindFinancialCategoryByIdUseCase {

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
     * ============================================================================
     * CONSTRUTOR
     * ============================================================================
     */
    public FindFinancialCategoryByIdUseCase(
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
     * Localizar uma categoria financeira pelo ID.
     *
     * Caso ela não exista, uma exceção é lançada.
     */
    public FinancialCategory execute(UUID id) {

        return categoryRepository
            .findById(id)
            .orElseThrow(() ->
                new FinancialCategoryNotFoundException(id)
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
     * FinancialCategory
     *
     * Se não encontrar:
     *
     * FinancialCategoryNotFoundException
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
