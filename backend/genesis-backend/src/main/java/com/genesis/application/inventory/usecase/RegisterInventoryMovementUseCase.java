/**
 * ============================================================================
 * CLASSE: RegisterInventoryMovementUseCase
 * ============================================================================
 *
 * CAMADA:
 * Application -> Inventory -> UseCase
 *
 * RESPONSABILIDADE:
 *
 * Registrar uma movimentação de estoque.
 *
 * Este caso de uso coordena todo o processo de entrada,
 * saída ou ajuste de estoque.
 *
 * Ele não conhece banco de dados nem JPA.
 * Apenas orquestra o fluxo utilizando os repositórios
 * do domínio.
 *
 * ============================================================================
 *
 * FLUXO:
 *
 * 1. Buscar o produto.
 * 2. Buscar o estoque.
 * 3. Criar estoque (quando permitido).
 * 4. Aplicar a movimentação.
 * 5. Salvar o estoque.
 * 6. Registrar o histórico.
 * 7. Retornar o estoque atualizado.
 *
 * ============================================================================
 */
package com.genesis.application.inventory.usecase;

/*
 * ============================================================================
 * IMPORTS
 * ============================================================================
 */

/*
 * DTO contendo os dados enviados para registrar
 * uma movimentação de estoque.
 */
import com.genesis.application.inventory.dto.RegisterInventoryMovementRequest;

/*
 * Exceção lançada quando o produto informado
 * não existe.
 */
import com.genesis.domain.exception.ProductNotFoundException;

/*
 * Entidade que representa o estoque.
 */
import com.genesis.domain.inventory.Inventory;

/*
 * Entidade que representa o histórico
 * das movimentações.
 */
import com.genesis.domain.inventory.InventoryMovement;

/*
 * Enum que define se a movimentação é
 * Entrada, Saída ou Ajuste.
 */
import com.genesis.domain.inventory.InventoryMovementType;

/*
 * Entidade Produto.
 */
import com.genesis.domain.product.Product;

/*
 * Repositório responsável pelas movimentações.
 */
import com.genesis.domain.repository.InventoryMovementRepository;

/*
 * Repositório responsável pelo estoque.
 */
import com.genesis.domain.repository.InventoryRepository;

/*
 * Repositório responsável pelos produtos.
 */
import com.genesis.domain.repository.ProductRepository;

/*
 * Value Object utilizado para representar
 * quantidades.
 */
import com.genesis.domain.shared.valueobject.Quantity;

/*
 * Registra esta classe como um Service
 * gerenciado pelo Spring Boot.
 */
import org.springframework.stereotype.Service;

@Service
public class RegisterInventoryMovementUseCase {

    /*
     * ============================================================================
     * ATRIBUTOS
     * ============================================================================
     */

    /*
     * Responsável por acessar o estoque.
     */
    private final InventoryRepository inventoryRepository;

    /*
     * Responsável por salvar o histórico
     * das movimentações.
     */
    private final InventoryMovementRepository inventoryMovementRepository;

    /*
     * Responsável por localizar produtos.
     */
    private final ProductRepository productRepository;

    /*
     * ============================================================================
     * CONSTRUTOR
     * ============================================================================
     *
     * O Spring injeta automaticamente todas
     * as dependências necessárias.
     */
    public RegisterInventoryMovementUseCase(
        InventoryRepository inventoryRepository,
        InventoryMovementRepository inventoryMovementRepository,
        ProductRepository productRepository) {

        this.inventoryRepository = inventoryRepository;
        this.inventoryMovementRepository = inventoryMovementRepository;
        this.productRepository = productRepository;
    }

    /*
     * ============================================================================
     * MÉTODO: execute()
     * ============================================================================
     *
     * Responsabilidade:
     *
     * Registrar uma movimentação de estoque.
     *
     * Retorna o estoque atualizado após
     * a movimentação.
     */
    public Inventory execute(RegisterInventoryMovementRequest request) {

        if (request.getMovementType() == InventoryMovementType.ADJUSTMENT) {

            throw new IllegalArgumentException(
                "ADJUSTMENT movements are not supported yet."
            );
        }

        Product product = productRepository
            .findById(request.getProductId())
            .orElseThrow(() ->
                new ProductNotFoundException(request.getProductId()));

        /*
         * Procura o estoque do produto.
         */
        Inventory inventory = inventoryRepository
            .findByProductId(product.getId())
            .orElse(null);

        /*
         * Caso o produto ainda não possua estoque,
         * ele será criado apenas para movimentações
         * de entrada.
         */
        if (inventory == null) {

            /*
             * Não é permitido realizar saída
             * sem existir estoque.
             */
            if (request.getMovementType() == InventoryMovementType.EXIT) {

                throw new IllegalArgumentException(
                    "Inventory not found for product."
                );
            }

            /*
             * Cria um novo estoque com saldo zero.
             */
            inventory = new Inventory(
                product,
                Quantity.ZERO
            );
        }

        /*
         * Converte o valor recebido no DTO
         * para o Value Object Quantity.
         */
        Quantity quantity = new Quantity(request.getQuantity());

        /*
         * Aplica a movimentação no estoque.
         */
        if (request.getMovementType() == InventoryMovementType.ENTRY) {

            inventory.increase(quantity);

        } else {

            inventory.decrease(quantity);
        }

        /*
         * Salva o novo estado do estoque.
         */
        inventory = inventoryRepository.save(inventory);

        /*
         * Cria o registro de histórico
         * da movimentação.
         */
        InventoryMovement movement = new InventoryMovement(
            inventory,
            request.getMovementType(),
            request.getMovementReason(),
            quantity,
            request.getNotes()
        );

        /*
         * Persiste o histórico.
         */
        inventoryMovementRepository.save(movement);

        /*
         * Retorna o estoque atualizado.
         */
        return inventory;
    }

}
