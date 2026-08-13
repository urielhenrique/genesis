/**
 * ============================================================================
 * CLASSE: FinancialCategoryResponse
 * ============================================================================
 *
 * CAMADA:
 * Application -> Financial -> DTO
 *
 * RESPONSABILIDADE:
 *
 * Representar os dados de uma categoria financeira
 * que serão retornados pela API.
 *
 * ============================================================================
 */
package com.genesis.application.financial.dto;

/*
 * ============================================================================
 * IMPORTS
 * ============================================================================
 */

/*
 * Identificador único da categoria.
 */
import java.util.UUID;

/**
 * DTO utilizado como resposta da API.
 */
public class FinancialCategoryResponse {

    /*
     * ============================================================================
     * ATRIBUTOS
     * ============================================================================
     */

    /*
     * Identificador da categoria.
     */
    private final UUID id;

    /*
     * Nome da categoria.
     */
    private final String name;

    /*
     * Indica se a categoria está ativa.
     */
    private final boolean active;

    /*
     * Data de criação.
     */
    private final java.time.LocalDateTime createdAt;

    /*
     * Data da última atualização.
     */
    private final java.time.LocalDateTime updatedAt;

    /*
     * ============================================================================
     * CONSTRUTOR
     * ============================================================================
     */
    public FinancialCategoryResponse(
        UUID id,
        String name,
        boolean active,
        java.time.LocalDateTime createdAt,
        java.time.LocalDateTime updatedAt) {

        this.id = id;
        this.name = name;
        this.active = active;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    /*
     * ============================================================================
     * GETTERS
     * ============================================================================
     */

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isActive() {
        return active;
    }

    public java.time.LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public java.time.LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
