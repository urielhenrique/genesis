/**
 * ============================================================================
 * CLASSE: UpdateFinancialCategoryRequest
 * ============================================================================
 *
 * CAMADA:
 * Application -> Financial -> DTO
 *
 * RESPONSABILIDADE:
 *
 * Representar os dados enviados pelo cliente para
 * atualização de uma categoria financeira.
 *
 * Este DTO transporta e valida os dados da requisição.
 *
 * Não possui regra de negócio.
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
 * Garante que o nome não seja nulo, vazio
 * ou composto apenas por espaços.
 */
import jakarta.validation.constraints.NotBlank;

/*
 * Define o tamanho máximo permitido para o nome.
 */
import jakarta.validation.constraints.Size;

public class UpdateFinancialCategoryRequest {

    /*
     * ============================================================================
     * ATRIBUTOS
     * ============================================================================
     */

    /*
     * Novo nome da categoria.
     */
    @NotBlank(message = "Category name is required.")
    @Size(max = 120)
    private final String name;

    /*
     * ============================================================================
     * CONSTRUTOR
     * ============================================================================
     *
     * Recebe os novos dados da categoria.
     */
    public UpdateFinancialCategoryRequest(String name) {

        /*
         * Remove espaços desnecessários no início
         * e no final do nome.
         */
        this.name = name == null ? null : name.trim();
    }

    /*
     * ============================================================================
     * GETTER
     * ============================================================================
     *
     * Retorna o novo nome da categoria.
     */
    public String getName() {
        return name;
    }

    /*
     * ============================================================================
     * RESUMO
     * ============================================================================
     *
     * Conceitos:
     *
     * ✔ DTO
     * ✔ Request
     * ✔ Bean Validation
     * ✔ @NotBlank
     * ✔ @Size
     * ✔ Objeto Imutável
     *
     * ============================================================================
     */
}
