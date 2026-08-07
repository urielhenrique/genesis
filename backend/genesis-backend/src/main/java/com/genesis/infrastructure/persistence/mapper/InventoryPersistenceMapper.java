/**
 * ============================================================================
 * CLASSE: InventoryPersistenceMapper
 * ============================================================================
 *
 * CAMADA:
 * Infrastructure -> Persistence -> Mapper
 *
 * RESPONSABILIDADE:
 *
 * Converter objetos entre a camada de Domínio e a camada
 * de Persistência.
 *
 * Este Mapper é responsável por transformar:
 *
 * Inventory            → InventoryJpaEntity
 * InventoryJpaEntity   → Inventory
 *
 * Dessa forma o Domínio permanece independente do
 * banco de dados e das tecnologias de persistência.
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
 * Entidade de domínio que representa o estoque.
 */
import com.genesis.domain.inventory.Inventory;

/*
 * Entidade de domínio que representa o produto.
 *
 * É necessária para reconstruir o Inventory,
 * pois o banco armazena apenas o UUID do produto.
 */
import com.genesis.domain.product.Product;

/*
 * Value Object responsável por representar
 * quantidades.
 */
import com.genesis.domain.shared.valueobject.Quantity;

/*
 * Entidade utilizada pelo JPA para persistência.
 */
import com.genesis.infrastructure.persistence.entity.InventoryJpaEntity;

/*
 * Registra esta classe como um componente
 * gerenciado pelo Spring Boot.
 */
import org.springframework.stereotype.Component;

@Component
public class InventoryPersistenceMapper {

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
     * no banco de dados.
     */
    public InventoryJpaEntity toJpaEntity(Inventory inventory) {

        return new InventoryJpaEntity(
            inventory.getId(),
            inventory.getProduct().getId(),
            inventory.getQuantity().getValue(),
            inventory.getCreatedAt(),
            inventory.getUpdatedAt()
        );
    }

    /*
     * ============================================================================
     * MÉTODO: toDomain()
     * ============================================================================
     *
     * Responsabilidade:
     *
     * Converter uma entidade JPA para uma entidade
     * do Domínio.
     *
     * Como a tabela inventory armazena apenas o UUID
     * do produto, a entidade Product precisa ser
     * informada para reconstrução completa do Inventory.
     */
    public Inventory toDomain(
        InventoryJpaEntity entity,
        Product product) {

        return new Inventory(
            entity.getId(),
            entity.getCreatedAt(),
            entity.getUpdatedAt(),
            product,
            new Quantity(entity.getQuantity())
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
     * ✔ Conversão entre camadas
     * ✔ Entity de Domínio
     * ✔ Entity JPA
     * ✔ Value Object
     * ✔ @Component
     * ✔ Clean Architecture
     *
     * ============================================================================
     */
}
