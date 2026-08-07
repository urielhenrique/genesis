/**
 * ============================================================================
 * CLASSE: InventoryResponse
 * ============================================================================
 *
 * CAMADA:
 * Application -> Inventory -> DTO
 *
 * RESPONSABILIDADE:
 *
 * Representar os dados retornados pela API referentes
 * ao estoque de um produto.
 *
 * Este DTO é utilizado para enviar informações da
 * aplicação para o cliente (Front-end, Mobile,
 * Postman, etc.).
 *
 * Ele não possui regras de negócio.
 *
 * ============================================================================
 *
 * FLUXO:
 *
 * Inventory
 *      ↓
 * InventoryResponseMapper
 *      ↓
 * InventoryResponse
 *      ↓
 * JSON
 *
 * ============================================================================
 */
package com.genesis.application.inventory.dto;

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
 * Representa valores decimais.
 *
 * Neste caso é utilizado para representar
 * a quantidade em estoque.
 */
import java.math.BigDecimal;

/*
 * Identificador único do produto.
 */
import java.util.UUID;

public class InventoryResponse {

    /*
     * ============================================================================
     * ATRIBUTOS
     * ============================================================================
     */

    /*
     * Identificador do produto.
     */
    private final UUID productId;

    /*
     * Nome do produto.
     */
    private final String productName;

    /*
     * Tipo do produto.
     */
    private final ProductType productType;

    /*
     * Quantidade disponível em estoque.
     */
    private final BigDecimal quantity;

    /*
     * ============================================================================
     * CONSTRUTOR
     * ============================================================================
     *
     * Cria um DTO contendo as informações que
     * serão retornadas ao cliente.
     */
    public InventoryResponse(
        UUID productId,
        String productName,
        ProductType productType,
        BigDecimal quantity) {

        this.productId = productId;
        this.productName = productName;
        this.productType = productType;
        this.quantity = quantity;
    }

    /*
     * ============================================================================
     * GETTERS
     * ============================================================================
     *
     * Apenas retornam os dados que serão
     * serializados para JSON.
     */

    public UUID getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public ProductType getProductType() {
        return productType;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    /*
     * ============================================================================
     * RESUMO
     * ============================================================================
     *
     * Conceitos aprendidos:
     *
     * ✔ DTO
     * ✔ Response
     * ✔ Objeto Imutável
     * ✔ JSON de Resposta
     * ✔ UUID
     * ✔ BigDecimal
     * ✔ Encapsulamento
     *
     * ============================================================================
     */
}
