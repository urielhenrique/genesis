/**
 * ============================================================================
 * CLASSE: RegisterInventoryMovementRequest
 * ============================================================================
 *
 * CAMADA:
 * Application -> Inventory -> DTO
 *
 * RESPONSABILIDADE:
 *
 * Representar os dados enviados pelo cliente para
 * registrar uma movimentação de estoque.
 *
 * Este DTO transporta apenas os dados da requisição
 * até o Use Case responsável.
 *
 * Ele não possui regras de negócio.
 *
 * ============================================================================
 *
 * FLUXO:
 *
 * JSON
 *      ↓
 * RegisterInventoryMovementRequest
 *      ↓
 * InventoryController
 *      ↓
 * RegisterInventoryMovementUseCase
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
 * Enum que representa o tipo da movimentação.
 *
 * Exemplos:
 *
 * ENTRY
 * EXIT
 * ADJUSTMENT
 */
import com.genesis.domain.inventory.InventoryMovementType;

/*
 * Enum que representa o motivo da movimentação.
 *
 * Exemplos:
 *
 * PURCHASE
 * DONATION
 * LOSS
 */
import com.genesis.domain.inventory.InventoryMovementReason;

/*
 * Anotações de validação do Jakarta Validation.
 *
 * As validações são executadas automaticamente
 * quando o Controller recebe a requisição.
 */
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/*
 * Representa valores decimais.
 *
 * Utilizado para representar a quantidade
 * movimentada.
 */
import java.math.BigDecimal;

/*
 * Identificador único do produto.
 */
import java.util.UUID;

public class RegisterInventoryMovementRequest {

    /*
     * ============================================================================
     * ATRIBUTOS
     * ============================================================================
     */

    /*
     * Produto que será movimentado.
     *
     * Obrigatório.
     */
    @NotNull(message = "Product is required.")
    private UUID productId;

    /*
     * Tipo da movimentação.
     *
     * Obrigatório.
     */
    @NotNull(message = "Movement type is required.")
    private InventoryMovementType movementType;

    /*
     * Motivo da movimentação.
     *
     * Obrigatório.
     */
    @NotNull(message = "Movement reason is required.")
    private InventoryMovementReason movementReason;

    /*
     * Quantidade movimentada.
     *
     * Obrigatória.
     *
     * Deve ser maior que zero.
     */
    @NotNull(message = "Quantity is required.")
    @Positive(message = "Quantity must be greater than zero.")
    private BigDecimal quantity;

    /*
     * Observações da movimentação.
     *
     * Campo opcional.
     */
    private String notes;

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
    protected RegisterInventoryMovementRequest() {
    }

    /*
     * ============================================================================
     * CONSTRUTOR
     * ============================================================================
     *
     * Cria um DTO contendo todos os dados
     * necessários para registrar uma
     * movimentação de estoque.
     */
    public RegisterInventoryMovementRequest(
        UUID productId,
        InventoryMovementType movementType,
        InventoryMovementReason movementReason,
        BigDecimal quantity,
        String notes) {

        this.productId = productId;
        this.movementType = movementType;
        this.movementReason = movementReason;
        this.quantity = quantity;
        this.notes = notes;
    }

    /*
     * ============================================================================
     * GETTERS
     * ============================================================================
     *
     * Apenas retornam os dados recebidos
     * na requisição.
     */

    public UUID getProductId() {
        return productId;
    }

    public InventoryMovementType getMovementType() {
        return movementType;
    }

    public InventoryMovementReason getMovementReason() {
        return movementReason;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public String getNotes() {
        return notes;
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
     * ✔ @NotNull
     * ✔ @Positive
     * ✔ UUID
     * ✔ BigDecimal
     * ✔ JSON → DTO
     *
     * ============================================================================
     */
}
