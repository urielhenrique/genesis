/**
 * ============================================================================
 * CLASSE: CreateProductRequest
 * ============================================================================
 *
 * CAMADA:
 * Application -> Product -> DTO
 *
 * RESPONSABILIDADE:
 *
 * Representar os dados enviados pelo cliente para
 * criação de um novo produto.
 *
 * Este DTO é utilizado apenas para transportar dados
 * entre o cliente (Front-end/API) e a aplicação.
 *
 * Ele não possui regra de negócio.
 *
 * ============================================================================
 *
 * FLUXO:
 *
 * JSON
 *      ↓
 * CreateProductRequest
 *      ↓
 * ProductController
 *      ↓
 * CreateProductUseCase
 *
 * ============================================================================
 */
package com.genesis.application.product.dto;

/*
 * ============================================================================
 * IMPORTS
 * ============================================================================
 */

/*
 * Enum que representa o tipo do produto.
 */
import com.genesis.domain.product.ProductType;

/*
 * Anotações de validação do Jakarta Validation.
 *
 * As validações são executadas automaticamente
 * quando o Controller recebe a requisição.
 */
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

/*
 * Representa valores monetários enviados
 * na requisição.
 */
import java.math.BigDecimal;

public class CreateProductRequest {

    /*
     * ============================================================================
     * ATRIBUTOS
     * ============================================================================
     */

    /*
     * Nome do produto.
     *
     * Obrigatório.
     *
     * Máximo de 120 caracteres.
     */
    @NotBlank(message = "Product name is required.")
    @Size(max = 120)
    private String name;

    /*
     * Descrição do produto.
     *
     * Campo opcional.
     *
     * Máximo de 500 caracteres.
     */
    @Size(max = 500)
    private String description;

    /*
     * Valor unitário do produto.
     *
     * Obrigatório.
     *
     * Deve ser maior que zero.
     */
    @NotNull(message = "Unit price is required.")
    @Positive(message = "Unit price must be greater than zero.")
    private BigDecimal unitPrice;

    /*
     * Tipo do produto.
     *
     * Obrigatório.
     */
    @NotNull(message = "Product type is required.")
    private ProductType type;

    /*
     * ============================================================================
     * CONSTRUTOR
     * ============================================================================
     *
     * Construtor sem argumentos.
     *
     * Utilizado pelo Jackson durante a
     * desserialização do JSON.
     */
    protected CreateProductRequest() {
    }

    /*
     * ============================================================================
     * CONSTRUTOR
     * ============================================================================
     *
     * Utilizado para criar o DTO com todos
     * os dados necessários.
     */
    public CreateProductRequest(
        String name,
        String description,
        BigDecimal unitPrice,
        ProductType type) {

        this.name = name;
        this.description = description;
        this.unitPrice = unitPrice;
        this.type = type;
    }

    /*
     * ============================================================================
     * GETTERS
     * ============================================================================
     *
     * Apenas retornam os dados recebidos
     * na requisição.
     */

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public ProductType getType() {
        return type;
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
     * ✔ @NotNull
     * ✔ @Positive
     * ✔ @Size
     * ✔ JSON → DTO
     *
     * ============================================================================
     */
}
