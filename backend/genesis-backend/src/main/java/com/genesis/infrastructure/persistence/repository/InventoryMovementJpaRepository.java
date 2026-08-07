/**
 * ============================================================================
 * INTERFACE: InventoryMovementJpaRepository
 * ============================================================================
 *
 * CAMADA:
 * Infrastructure -> Persistence -> Repository
 *
 * RESPONSABILIDADE:
 *
 * Realizar o acesso à tabela de movimentações de estoque
 * utilizando Spring Data JPA.
 *
 * Esta interface é responsável apenas pela persistência
 * dos dados, não contendo nenhuma regra de negócio.
 *
 * O Spring Data JPA cria automaticamente sua implementação
 * durante a inicialização da aplicação.
 *
 * ============================================================================
 *
 * QUEM UTILIZA ESTA INTERFACE?
 *
 * • InventoryMovementPersistenceAdapter
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
 * Entidade JPA que representa a tabela
 * inventory_movement no banco de dados.
 */
import com.genesis.infrastructure.persistence.entity.InventoryMovementJpaEntity;

/*
 * Interface base do Spring Data JPA.
 *
 * Disponibiliza automaticamente operações como:
 *
 * • save()
 * • findById()
 * • findAll()
 * • delete()
 */
import org.springframework.data.jpa.repository.JpaRepository;

/*
 * Lista utilizada para retornar várias movimentações.
 */
import java.util.List;

/*
 * Identificador único utilizado nas consultas.
 */
import java.util.UUID;

/**
 * Repositório JPA responsável pela persistência da
 * entidade InventoryMovementJpaEntity.
 */
public interface InventoryMovementJpaRepository
    extends JpaRepository<InventoryMovementJpaEntity, UUID> {

    /**
     * Retorna todas as movimentações pertencentes
     * a um determinado estoque.
     *
     * O Spring Data JPA gera automaticamente a consulta
     * a partir do nome do método.
     *
     * Equivalente a:
     *
     * SELECT *
     * FROM inventory_movement
     * WHERE inventory_id = ?
     *
     * @param inventoryId Identificador do estoque.
     * @return Lista de movimentações encontradas.
     */
    List<InventoryMovementJpaEntity> findByInventoryId(UUID inventoryId);

}
