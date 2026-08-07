/**
 * ============================================================================
 * CLASSE: InventoryMovementPersistenceMapper
 * ============================================================================
 *
 * CAMADA:
 * Infrastructure -> Persistence -> Mapper
 *
 * RESPONSABILIDADE:
 *
 * Converter uma entidade de domínio (InventoryMovement)
 * para uma entidade de persistência (InventoryMovementJpaEntity).
 *
 * Esta conversão é realizada antes de salvar uma
 * movimentação de estoque no banco de dados.
 *
 * Assim mantemos a camada de Domínio independente
 * da tecnologia de persistência.
 *
 * ============================================================================
 */
package com.genesis.infrastructure.persistence.mapper;

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
 * Entidade utilizada pelo JPA para persistência.
 */
import com.genesis.infrastructure.persistence.entity.InventoryMovementJpaEntity;

/*
 * Registra esta classe como um componente
 * gerenciado pelo Spring Boot.
 */
import org.springframework.stereotype.Component;

@Component
public class InventoryMovementPersistenceMapper {

    /*
     * ============================================================================
     * MÉTODO: toJpaEntity()
     * ============================================================================
     *
     * Responsabilidade:
     *
     * Converter uma entidade do Domínio para uma
     * entidade JPA.
     *
     * Esta conversão ocorre antes da gravação
     * da movimentação no banco de dados.
     */
    public InventoryMovementJpaEntity toJpaEntity(
        InventoryMovement movement) {

        return new InventoryMovementJpaEntity(
            movement.getId(),
            movement.getInventory().getId(),
            movement.getType(),
            movement.getReason(),
            movement.getQuantity().getValue(),
            movement.getNotes(),
            movement.getCreatedAt(),
            movement.getUpdatedAt()
        );
    }

    /*
     * ============================================================================
     * RESUMO
     * ============================================================================
     *
     * Conceitos aprendidos:
     *
     * ✔ Mapper
     * ✔ Conversão Domínio → Persistência
     * ✔ Entity de Domínio
     * ✔ Entity JPA
     * ✔ @Component
     * ✔ Clean Architecture
     *
     * ============================================================================
     */
}
