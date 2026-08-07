/**
 * ============================================================================
 * INTERFACE: InventoryJpaRepository
 * ============================================================================
 *
 * CAMADA:
 * Infrastructure -> Persistence -> Repository
 *
 * RESPONSABILIDADE:
 *
 * Realizar o acesso à tabela de estoque utilizando
 * Spring Data JPA.
 *
 * Esta interface pertence à camada de infraestrutura
 * e conhece apenas a entidade JPA utilizada para
 * persistência no banco de dados.
 *
 * O Spring Boot cria automaticamente sua implementação
 * durante a inicialização da aplicação.
 *
 * ============================================================================
 *
 * QUEM UTILIZA ESTA INTERFACE?
 *
 * • InventoryPersistenceAdapter
 *
 * QUEM IMPLEMENTA ESTA INTERFACE?
 *
 * • Spring Data JPA
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
 * Entidade JPA que representa a tabela inventory
 * no banco de dados.
 */
import com.genesis.infrastructure.persistence.entity.InventoryJpaEntity;

/*
 * Interface base do Spring Data JPA.
 *
 * Disponibiliza automaticamente operações como:
 *
 * • save()
 * • findById()
 * • findAll()
 * • delete()
 * • existsById()
 */
import org.springframework.data.jpa.repository.JpaRepository;

/*
 * Optional representa um resultado que pode
 * existir ou não.
 */
import java.util.Optional;

/*
 * Identificador único utilizado nas consultas.
 */
import java.util.UUID;

/**
 * Repositório JPA responsável pela persistência
 * da entidade InventoryJpaEntity.
 */
public interface InventoryJpaRepository
    extends JpaRepository<InventoryJpaEntity, UUID> {

    /**
     * Localiza o estoque de um determinado produto.
     *
     * O Spring Data JPA gera automaticamente a consulta
     * a partir do nome do método.
     *
     * Equivalente a:
     *
     * SELECT *
     * FROM inventory
     * WHERE product_id = ?
     *
     * @param productId Identificador do produto.
     * @return Estoque encontrado, caso exista.
     */
    Optional<InventoryJpaEntity> findByProductId(UUID productId);

}
