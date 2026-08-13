/**
 * ============================================================================
 * INTERFACE: FinancialCategoryRepository
 * ============================================================================
 *
 * CAMADA:
 * Domain -> Repository
 *
 * RESPONSABILIDADE:
 *
 * Definir o contrato para persistência das categorias
 * financeiras.
 *
 * O Domínio conhece apenas esta interface.
 *
 * A implementação real ficará na camada de Infrastructure,
 * utilizando JPA/PostgreSQL.
 *
 * ============================================================================
 */
package com.genesis.domain.repository;

/*
 * ============================================================================
 * IMPORTS
 * ============================================================================
 */

/*
 * Entidade de domínio que será persistida.
 */
import com.genesis.domain.financial.FinancialCategory;

/*
 * Optional representa um resultado que pode
 * existir ou não.
 */
import java.util.Optional;

/*
 * Lista utilizada para retornar várias categorias.
 */
import java.util.List;

/*
 * Identificador único da categoria.
 */
import java.util.UUID;

/**
 * Contrato de persistência das categorias financeiras.
 */
public interface FinancialCategoryRepository {

    /*
     * ============================================================================
     * MÉTODO: save()
     * ============================================================================
     *
     * Salva uma categoria financeira.
     */
    FinancialCategory save(FinancialCategory category);

    /*
     * ============================================================================
     * MÉTODO: findById()
     * ============================================================================
     *
     * Procura uma categoria pelo seu identificador.
     */
    Optional<FinancialCategory> findById(UUID id);

    /*
     * ============================================================================
     * MÉTODO: findByName()
     * ============================================================================
     *
     * Procura uma categoria pelo nome.
     */
    Optional<FinancialCategory> findByName(String name);

    /*
     * ============================================================================
     * MÉTODO: findAll()
     * ============================================================================
     *
     * Retorna todas as categorias.
     */
    List<FinancialCategory> findAll();

}
