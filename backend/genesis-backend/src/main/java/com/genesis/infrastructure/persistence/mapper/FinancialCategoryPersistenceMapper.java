/**
 * ============================================================================
 * CLASSE: FinancialCategoryPersistenceMapper
 * ============================================================================
 *
 * CAMADA:
 * Infrastructure -> Persistence -> Mapper
 *
 * RESPONSABILIDADE:
 *
 * Converter entre a entidade de domínio FinancialCategory
 * e a entidade JPA FinancialCategoryJpaEntity.
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
import com.genesis.domain.financial.FinancialCategory;

/*
 * Entidade utilizada pelo JPA/Hibernate.
 */
import com.genesis.infrastructure.persistence.entity.FinancialCategoryJpaEntity;

/*
 * Permite que o Spring gerencie o Mapper.
 */
import org.springframework.stereotype.Component;

/**
 * Mapper responsável pela conversão de categorias financeiras.
 */
@Component
public class FinancialCategoryPersistenceMapper {

    /*
     * ============================================================================
     * MÉTODO: toJpaEntity()
     * ============================================================================
     *
     * Converte a entidade de domínio para a entidade
     * utilizada pelo JPA.
     */
    public FinancialCategoryJpaEntity toJpaEntity(
        FinancialCategory category) {

        return new FinancialCategoryJpaEntity(
            category.getId(),
            category.getName(),
            category.isActive(),
            category.getCreatedAt(),
            category.getUpdatedAt()
        );
    }

    /*
     * ============================================================================
     * MÉTODO: toDomain()
     * ============================================================================
     *
     * Converte a entidade JPA para a entidade de domínio.
     *
     * É utilizado quando os dados vêm do banco de dados
     * e precisam voltar para a camada de domínio.
     */
    public FinancialCategory toDomain(
        FinancialCategoryJpaEntity entity) {

        return new FinancialCategory(
            entity.getId(),
            entity.getCreatedAt(),
            entity.getUpdatedAt(),
            entity.getName(),
            entity.isActive()
        );
    }

}
