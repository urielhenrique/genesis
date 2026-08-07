/**
 * ============================================================================
 * CLASSE: ProductPersistenceMapper
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
 * Este Mapper evita que a entidade de domínio conheça
 * detalhes do banco de dados e que a entidade JPA conheça
 * regras de negócio.
 *
 * ============================================================================
 *
 * CONVERSÕES REALIZADAS:
 *
 * Product
 *        ↓
 * ProductJpaEntity
 *
 * ProductJpaEntity
 *        ↓
 * Product
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
 * Entidade de domínio.
 */
import com.genesis.domain.product.Product;

/*
 * Value Object utilizado para representar dinheiro.
 */
import com.genesis.domain.shared.valueobject.Money;

/*
 * Entidade utilizada pelo JPA.
 */
import com.genesis.infrastructure.persistence.entity.ProductJpaEntity;

/*
 * Registra esta classe como um componente gerenciado
 * pelo Spring Boot.
 */
import org.springframework.stereotype.Component;

@Component
public class ProductPersistenceMapper {

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
     * Esta conversão é utilizada antes de salvar
     * os dados no banco.
     */
    public ProductJpaEntity toJpaEntity(Product product) {

        return new ProductJpaEntity(
            product.getId(),
            product.getName(),
            product.getDescription(),
            product.getUnitPrice().getValue(),
            product.getType(),
            product.isActive(),
            product.getCreatedAt(),
            product.getUpdatedAt()
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
     * Esta conversão é utilizada após recuperar
     * os dados do banco.
     */
    public Product toDomain(ProductJpaEntity entity) {

        return new Product(
            entity.getId(),
            entity.getCreatedAt(),
            entity.getUpdatedAt(),
            entity.getName(),
            entity.getDescription(),
            new Money(entity.getUnitPrice()),
            entity.getType(),
            entity.isActive()
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
     * ✔ Separação entre Domínio e Persistência
     * ✔ @Component
     * ✔ Clean Architecture
     *
     * ============================================================================
     */

}
