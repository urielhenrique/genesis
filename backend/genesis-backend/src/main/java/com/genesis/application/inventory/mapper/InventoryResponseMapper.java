/**
 * ============================================================================
 * CLASSE: InventoryResponseMapper
 * ============================================================================
 *
 * CAMADA:
 * Application -> Inventory -> Mapper
 *
 * RESPONSABILIDADE:
 *
 * Converter entidades do Domínio (Inventory)
 * em DTOs de resposta (InventoryResponse).
 *
 * O objetivo deste Mapper é impedir que a camada
 * Web tenha acesso direto às entidades do Domínio.
 *
 * Dessa forma a API retorna apenas as informações
 * necessárias para o cliente.
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
package com.genesis.application.inventory.mapper;

/*
 * ============================================================================
 * IMPORTS
 * ============================================================================
 */

/*
 * DTO utilizado como resposta da API.
 */
import com.genesis.application.inventory.dto.InventoryResponse;

/*
 * Entidade de domínio Inventory.
 */
import com.genesis.domain.inventory.Inventory;

/*
 * Registra esta classe como um componente
 * gerenciado pelo Spring Boot.
 */
import org.springframework.stereotype.Component;

/**
 * Mapper responsável por converter objetos
 * Inventory em InventoryResponse.
 */
@Component
public class InventoryResponseMapper {

    /*
     * ============================================================================
     * MÉTODO: toResponse()
     * ============================================================================
     *
     * Responsabilidade:
     *
     * Converter uma entidade Inventory do Domínio
     * em um InventoryResponse utilizado pela API.
     *
     * Somente os dados necessários para o cliente
     * são incluídos na resposta.
     */
    public InventoryResponse toResponse(
        Inventory inventory) {

        return new InventoryResponse(
            inventory.getProduct().getId(),
            inventory.getProduct().getName(),
            inventory.getProduct().getType(),
            inventory.getQuantity().getValue()
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
     * ✔ Conversão Domínio → DTO
     * ✔ Encapsulamento
     * ✔ @Component
     * ✔ Clean Architecture
     *
     * ============================================================================
     */
}
