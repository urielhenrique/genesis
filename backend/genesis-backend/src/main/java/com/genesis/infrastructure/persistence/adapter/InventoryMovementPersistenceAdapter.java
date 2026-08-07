/**
 * ============================================================================
 * CLASSE: InventoryMovementPersistenceAdapter
 * ============================================================================
 *
 * CAMADA:
 * Infrastructure -> Persistence -> Adapter
 *
 * RESPONSABILIDADE:
 *
 * Implementar o contrato InventoryMovementRepository
 * definido na camada de Domínio.
 *
 * Esta classe é responsável por persistir o histórico
 * das movimentações de estoque utilizando o Spring Data JPA.
 *
 * Assim como os demais Adapters, ela faz a ponte entre
 * o Domínio e a camada de Persistência.
 *
 * ============================================================================
 *
 * FLUXO:
 *
 * InventoryMovement (Domínio)
 *             ↓
 * InventoryMovementPersistenceMapper
 *             ↓
 * InventoryMovementJpaEntity
 *             ↓
 * InventoryMovementJpaRepository
 *             ↓
 * PostgreSQL
 *
 * ============================================================================
 */
package com.genesis.infrastructure.persistence.adapter;

/*
 * ============================================================================
 * IMPORTS
 * ============================================================================
 */

/*
 * Entidade de domínio que representa uma movimentação
 * de estoque.
 */
import com.genesis.domain.inventory.InventoryMovement;

/*
 * Contrato de persistência definido no Domínio.
 */
import com.genesis.domain.repository.InventoryMovementRepository;

/*
 * Responsável por converter InventoryMovement
 * ⇄ InventoryMovementJpaEntity.
 */
import com.genesis.infrastructure.persistence.mapper.InventoryMovementPersistenceMapper;

/*
 * Repositório JPA responsável pelo acesso à tabela
 * inventory_movement.
 */
import com.genesis.infrastructure.persistence.repository.InventoryMovementJpaRepository;

/*
 * Registra esta classe como um Repository
 * gerenciado pelo Spring Boot.
 */
import org.springframework.stereotype.Repository;

@Repository
public class InventoryMovementPersistenceAdapter
    implements InventoryMovementRepository {

    /*
     * ============================================================================
     * ATRIBUTOS
     * ============================================================================
     */

    /*
     * Repositório JPA responsável pela persistência
     * das movimentações de estoque.
     */
    private final InventoryMovementJpaRepository jpaRepository;

    /*
     * Responsável pela conversão entre
     * Domínio e Persistência.
     */
    private final InventoryMovementPersistenceMapper mapper;

    /*
     * ============================================================================
     * CONSTRUTOR
     * ============================================================================
     *
     * O Spring Boot injeta automaticamente
     * as dependências necessárias.
     */
    public InventoryMovementPersistenceAdapter(
        InventoryMovementJpaRepository jpaRepository,
        InventoryMovementPersistenceMapper mapper) {

        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    /*
     * ============================================================================
     * MÉTODO: save()
     * ============================================================================
     *
     * Responsabilidade:
     *
     * Persistir uma movimentação de estoque.
     *
     * Fluxo:
     *
     * InventoryMovement
     *          ↓
     * InventoryMovementJpaEntity
     *          ↓
     * Banco de Dados
     */
    @Override
    public InventoryMovement save(InventoryMovement movement) {

        jpaRepository.save(
            mapper.toJpaEntity(movement)
        );

        return movement;
    }

    /*
     * ============================================================================
     * RESUMO
     * ============================================================================
     *
     * Conceitos aprendidos:
     *
     * ✔ Adapter
     * ✔ Repository Pattern
     * ✔ Implementação de Interface
     * ✔ Mapper
     * ✔ Persistência
     * ✔ Spring Data JPA
     * ✔ @Repository
     * ✔ Clean Architecture
     *
     * ============================================================================
     */
}
