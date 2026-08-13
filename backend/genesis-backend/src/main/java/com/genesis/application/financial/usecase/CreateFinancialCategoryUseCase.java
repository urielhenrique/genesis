/**
 * ============================================================================
 * CLASSE: CreateFinancialCategoryUseCase
 * ============================================================================
 *
 * CAMADA:
 * Application -> Financial -> UseCase
 *
 * RESPONSABILIDADE:
 *
 * Criar uma nova categoria financeira.
 *
 * O Use Case coordena a operação entre o DTO,
 * a entidade de domínio e o Repository.
 *
 * ============================================================================
 *
 * FLUXO:
 *
 * CreateFinancialCategoryRequest
 *              ↓
 * CreateFinancialCategoryUseCase
 *              ↓
 * FinancialCategory
 *              ↓
 * FinancialCategoryRepository
 *              ↓
 * Banco de Dados
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
 * DTO que receberá os dados enviados pelo cliente.
 *
 * Ainda vamos criar essa classe.
 */
import com.genesis.application.financial.dto.CreateFinancialCategoryRequest;

/*
 * Entidade de domínio que representa uma categoria.
 */
import com.genesis.domain.financial.FinancialCategory;

/*
 * Contrato de persistência da categoria.
 */
import com.genesis.domain.repository.FinancialCategoryRepository;

/*
 * Permite que o Spring gerencie esta classe.
 */
import org.springframework.stereotype.Service;

/**
 * Use Case responsável pela criação de categorias financeiras.
 */
@Service
public class CreateFinancialCategoryUseCase {

    /*
     * ============================================================================
     * ATRIBUTOS
     * ============================================================================
     */

    /*
     * Repository utilizado para salvar a categoria.
     */
    private final FinancialCategoryRepository categoryRepository;

    /*
     * ============================================================================
     * CONSTRUTOR
     * ============================================================================
     *
     * O Spring injeta automaticamente o Repository.
     */
    public CreateFinancialCategoryUseCase(
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
     * Executar o processo de criação de uma categoria.
     */
    public FinancialCategory execute(
        CreateFinancialCategoryRequest request) {

        /*
         * Cria a entidade de domínio.
         *
         * As regras da entidade FinancialCategory
         * serão executadas neste momento.
         */
        FinancialCategory category =
            new FinancialCategory(
                request.getName()
            );

        /*
         * Persiste a entidade através do contrato
         * definido no domínio.
         */
        return categoryRepository.save(category);
    }

    /*
     * ============================================================================
     * RESUMO
     * ============================================================================
     *
     * Conceitos aprendidos:
     *
     * ✔ Use Case
     * ✔ @Service
     * ✔ Injeção de dependência
     * ✔ Repository
     * ✔ DTO → Domain
     * ✔ Criação de entidade
     * ✔ Separação de responsabilidades
     *
     * ============================================================================
     */
}
