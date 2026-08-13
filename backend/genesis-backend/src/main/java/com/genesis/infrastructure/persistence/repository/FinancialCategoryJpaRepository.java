/**
 * ============================================================================
 * INTERFACE: FinancialCategoryJpaRepository
 * ============================================================================
 *
 * CAMADA:
 * Infrastructure -> Persistence -> Repository
 *
 * RESPONSABILIDADE:
 *
 * Fornecer acesso ao banco de dados para a entidade
 * FinancialCategoryJpaEntity através do Spring Data JPA.
 *
 * ============================================================================
 */
package com.genesis.infrastructure.persistence.repository;

/*
 * ============================================================================
 * IMPORTS
 * ============================================================================
 */

/*
 * Entidade JPA que representa a tabela
 * financial_category.
 */
import com.genesis.infrastructure.persistence.entity.FinancialCategoryJpaEntity;

/*
 * Interface do Spring Data JPA.
 *
 * Fornece automaticamente operações como:
 *
 * • save()
 * • findById()
 * • findAll()
 * • delete()
 */
import org.springframework.data.jpa.repository.JpaRepository;

/*
 * Permite retornar um resultado opcional.
 */
import java.util.Optional;

/*
 * Identificador da entidade.
 */
import java.util.UUID;

/**
 * Repository JPA para categorias financeiras.
 */
public interface FinancialCategoryJpaRepository
    extends JpaRepository<FinancialCategoryJpaEntity, UUID> {

    /*
     * ============================================================================
     * MÉTODO: findByName()
     * ============================================================================
     *
     * O Spring Data JPA cria automaticamente a consulta
     * utilizando o nome do método.
     *
     * Equivalente conceitualmente a:
     *
     * SELECT *
     * FROM financial_category
     * WHERE name = ?
     */
    Optional<FinancialCategoryJpaEntity> findByName(String name);

}
