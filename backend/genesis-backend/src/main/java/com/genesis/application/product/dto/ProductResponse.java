/**
 * ============================================================================
 * CLASSE: ProductResponse
 * ============================================================================
 *
 * CAMADA:
 * Application -> Product -> DTO
 *
 * RESPONSABILIDADE:
 *
 * Representar os dados retornados pela API após uma
 * operação envolvendo produtos.
 *
 * Diferente do CreateProductRequest, este DTO é enviado
 * da aplicação para o cliente (Front-end, Mobile,
 * Postman, etc.).
 *
 * Ele não possui regras de negócio.
 *
 * ============================================================================
 *
 * FLUXO:
 *
 * Product
 *      ↓
 * ProductResponseMapper
 *      ↓
 * ProductResponse
 *      ↓
 * JSON
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
 * Representa valores monetários.
 */
import java.math.BigDecimal;

/*
 * Representa data e hora.
 */
import java.time.LocalDateTime;

/*
 * Identificador único do produto.
 */
import java.util.UUID;

public class ProductResponse {

    /*
     * ============================================================================
     * ATRIBUTOS
     * ============================================================================
     */

    /*
     * Identificador único do produto.
     */
    private final UUID id;

    /*
     * Nome do produto.
     */
    private final String name;

    /*
     * Descrição do produto.
     */
    private final String description;

    /*
     * Valor unitário do produto.
     */
    private final BigDecimal unitPrice;

    /*
     * Tipo do produto.
     */
    private final ProductType type;

    /*
     * Indica se o produto está ativo.
     */
    private final boolean active;

    /*
     * Data de criação do registro.
     */
    private final LocalDateTime createdAt;

    /*
     * Data da última atualização.
     */
    private final LocalDateTime updatedAt;

    /*
     * ============================================================================
     * CONSTRUTOR
     * ============================================================================
     *
     * Cria um DTO contendo todas as informações que
     * serão devolvidas ao cliente.
     */
    public ProductResponse(
        UUID id,
        String name,
        String description,
        BigDecimal unitPrice,
        ProductType type,
        boolean active,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

        this.id = id;
        this.name = name;
        this.description = description;
        this.unitPrice = unitPrice;
        this.type = type;
        this.active = active;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    /*
     * ============================================================================
     * GETTERS
     * ============================================================================
     *
     * Apenas retornam os dados que serão serializados
     * para JSON na resposta da API.
     */

    public UUID getId() {
        return id;
    }

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

    public boolean isActive() {
        return active;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
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
     * ✔ Getters
     * ✔ UUID
     * ✔ LocalDateTime
     *
     * ============================================================================
     */
}
