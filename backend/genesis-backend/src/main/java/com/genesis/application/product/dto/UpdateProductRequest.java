/**
 * ============================================================================
 * CLASSE: UpdateProductRequest
 * ============================================================================
 *
 * CAMADA:
 * Application -> Product -> DTO
 *
 * RESPONSABILIDADE:
 *
 * Representar os dados enviados pelo cliente para
 * atualizar um produto existente.
 *
 * Este DTO transporta apenas os dados da requisição.
 *
 * Ele não possui regras de negócio.
 *
 * ============================================================================
 *
 * FLUXO:
 *
 * JSON
 *      ↓
 * UpdateProductRequest
 *      ↓
 * ProductController
 *      ↓
 * UpdateProductUseCase
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
 * Representa valores monetários.
 */
import java.math.BigDecimal;

public class UpdateProductRequest {

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
    private final String name;

    /*
     * Descrição do produto.
     *
     * Campo opcional.
     *
     * Máximo de 500 caracteres.
     */
    @Size(max = 500)
    private final String description;

    /*
     * Valor unitário.
     *
     * Obrigatório.
     *
     * Deve ser maior que zero.
     */
    @NotNull(message = "Unit price is required.")
    @Positive(message = "Unit price must be greater than zero.")
    private final BigDecimal unitPrice;

    /*
     * Tipo do produto.
     *
     * Obrigatório.
     */
    @NotNull(message = "Product type is required.")
    private final ProductType type;

    /*
     * ============================================================================
     * CONSTRUTOR
     * ============================================================================
     *
     * Cria um DTO contendo os novos dados
     * do produto.
     *
     * O nome é normalizado removendo espaços
     * no início e no final.
     */
    public UpdateProductRequest(
        String name,
        String description,
        BigDecimal unitPrice,
        ProductType type) {

        this.name = name == null ? null : name.trim();
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
     * ✔ Objeto Imutável
     * ✔ JSON → DTO
     *
     * ============================================================================
     */
}
