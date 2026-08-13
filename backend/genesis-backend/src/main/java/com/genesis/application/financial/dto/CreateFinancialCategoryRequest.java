/**
 * ============================================================================
 * CLASSE: CreateFinancialCategoryRequest
 * ============================================================================
 *
 * CAMADA:
 * Application -> Financial -> DTO
 *
 * RESPONSABILIDADE:
 *
 * Representar os dados enviados pelo cliente para
 * criação de uma categoria financeira.
 *
 * Este DTO apenas transporta e valida os dados da
 * requisição.
 *
 * Não possui regra de negócio.
 *
 * ============================================================================
 *
 * FLUXO:
 *
 * JSON
 *      ↓
 * CreateFinancialCategoryRequest
 *      ↓
 * FinancialController
 *      ↓
 * CreateFinancialCategoryUseCase
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

public class CreateFinancialCategoryRequest {

    /*
     * ============================================================================
     * ATRIBUTOS
     * ============================================================================
     */

    /*
     * Nome da categoria financeira.
     */
    @NotBlank(message = "Category name is required.")
    @Size(max = 120)
    private String name;

    /*
     * ============================================================================
     * CONSTRUTOR
     * ============================================================================
     *
     * Construtor sem argumentos utilizado pelo
     * Jackson para transformar o JSON recebido
     * em um objeto Java.
     */
    protected CreateFinancialCategoryRequest() {
    }

    /*
     * ============================================================================
     * CONSTRUTOR
     * ============================================================================
     *
     * Permite criar o DTO informando o nome
     * diretamente.
     */
    public CreateFinancialCategoryRequest(String name) {

        this.name = name;
    }

    /*
     * ============================================================================
     * GETTER
     * ============================================================================
     *
     * Retorna o nome recebido na requisição.
     */
    public String getName() {
        return name;
    }

    /*
     * ============================================================================
     * RESUMO
     * ============================================================================
     *
     * Conceitos aprendidos:
     *
     * ✔ DTO
     * ✔ Request
     * ✔ Bean Validation
     * ✔ @NotBlank
     * ✔ @Size
     * ✔ Jackson
     * ✔ JSON → DTO
     *
     * ============================================================================
     */
}
