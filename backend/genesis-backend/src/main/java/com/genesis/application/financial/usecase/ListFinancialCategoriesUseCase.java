/**
 * ============================================================================
 * CLASSE: ListFinancialCategoriesUseCase
 * ============================================================================
 *
 * CAMADA:
 * Application -> Financial -> UseCase
 *
 * RESPONSABILIDADE:
 *
 * Buscar todas as categorias financeiras cadastradas.
 *
 * Este Use Case apenas coordena a consulta ao Repository.
 * Não possui regra de negócio.
 *
 * ============================================================================
 *
 * FLUXO:
 *
 * FinancialCategoryRepository
 *              ↓
 * ListFinancialCategoriesUseCase
 *              ↓
 * List<FinancialCategory>
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
 * Entidade de domínio que representa uma categoria financeira.
 */
import com.genesis.domain.financial.FinancialCategory;

/*
 * Contrato de persistência das categorias.
 */
import com.genesis.domain.repository.FinancialCategoryRepository;

/*
 * Permite que o Spring gerencie este Use Case.
 */
import org.springframework.stereotype.Service;

/*
 * Utilizado para representar uma lista de categorias.
 */
import java.util.List;

/**
 * Use Case responsável por listar categorias financeiras.
 */
@Service
public class ListFinancialCategoriesUseCase {

    /*
     * ============================================================================
     * ATRIBUTOS
     * ============================================================================
     */

    /*
     * Repository utilizado para consultar as categorias.
     */
    private final FinancialCategoryRepository categoryRepository;

    /*
     * ============================================================================
     * CONSTRUTOR
     * ============================================================================
     *
     * O Spring injeta automaticamente o Repository.
     */
    public ListFinancialCategoriesUseCase(
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
     * Retornar todas as categorias financeiras.
     */
    public List<FinancialCategory> execute() {

        /*
         * Delega a consulta para o Repository.
         */
        return categoryRepository.findAll();
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
     * categoryRepository.findAll()
     *      ↓
     * List<FinancialCategory>
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
