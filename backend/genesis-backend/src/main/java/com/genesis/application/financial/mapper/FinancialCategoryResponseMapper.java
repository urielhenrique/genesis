/**
 * ============================================================================
 * CLASSE: FinancialCategoryResponseMapper
 * ============================================================================
 *
 * CAMADA:
 * Application -> Financial -> Mapper
 *
 * RESPONSABILIDADE:
 *
 * Converter a entidade de domínio FinancialCategory
 * para o DTO FinancialCategoryResponse.
 *
 * O Mapper separa o modelo interno do domínio
 * do modelo que será exposto pela API.
 *
 * ============================================================================
 */
package com.genesis.application.financial.mapper;

/*
 * ============================================================================
 * IMPORTS
 * ============================================================================
 */

/*
 * DTO que será retornado pela API.
 */
import com.genesis.application.financial.dto.FinancialCategoryResponse;

/*
 * Entidade de domínio que será convertida.
 */
import com.genesis.domain.financial.FinancialCategory;

/*
 * Permite que o Spring gerencie o Mapper.
 */
import org.springframework.stereotype.Component;

/**
 * Mapper responsável pelas respostas de categoria financeira.
 */
@Component
public class FinancialCategoryResponseMapper {

    /*
     * ============================================================================
     * MÉTODO: toResponse()
     * ============================================================================
     *
     * Responsabilidade:
     *
     * Converter uma entidade FinancialCategory
     * em FinancialCategoryResponse.
     */
    public FinancialCategoryResponse toResponse(
        FinancialCategory category) {

        return new FinancialCategoryResponse(
            category.getId(),
            category.getName(),
            category.isActive(),
            category.getCreatedAt(),
            category.getUpdatedAt()
        );
    }
}
