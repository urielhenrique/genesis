/**
 * ============================================================================
 * CLASSE: CreateProductUseCase
 * ============================================================================
 *
 * CAMADA:
 * Application -> Product -> UseCase
 *
 * RESPONSABILIDADE:
 *
 * Orquestrar o processo de criação de um novo produto.
 *
 * O Use Case coordena o fluxo da aplicação.
 *
 * Ele não contém regras de persistência nem detalhes
 * de banco de dados.
 *
 * ============================================================================
 *
 * QUEM UTILIZA ESTA CLASSE?
 *
 * • ProductController
 *
 * QUEM ESTA CLASSE UTILIZA?
 *
 * • ProductRepository
 * • InventoryRepository
 * • Product
 * • Inventory
 * • Money
 * • Quantity
 *
 * ============================================================================
 */
package com.genesis.application.product.usecase;

/*
 * ============================================================================
 * IMPORTS
 * ============================================================================
 */

/*
 * Indica ao Spring Boot que esta classe é um Service.
 *
 * O Spring criará automaticamente uma instância desta
 * classe e permitirá sua injeção em outras classes.
 */
import org.springframework.stereotype.Service;

/*
 * DTO que contém os dados enviados pelo cliente
 * para criação de um produto.
 */
import com.genesis.application.product.dto.CreateProductRequest;

/*
 * Entidade de domínio que representa um produto.
 */
import com.genesis.domain.product.Product;

/*
 * Repositório responsável por persistir produtos.
 */
import com.genesis.domain.repository.ProductRepository;

/*
 * Value Object responsável por representar valores monetários.
 */
import com.genesis.domain.shared.valueobject.Money;

/*
 * Entidade de domínio responsável pelo estoque do produto.
 */
import com.genesis.domain.inventory.Inventory;

/*
 * Repositório responsável por persistir estoques.
 */
import com.genesis.domain.repository.InventoryRepository;

/*
 * Value Object que representa uma quantidade.
 */
import com.genesis.domain.shared.valueobject.Quantity;

@Service
public class CreateProductUseCase {

    /*
     * ============================================================================
     * ATRIBUTOS
     * ============================================================================
     */

    /*
     * Repositório utilizado para salvar produtos.
     */
    private final ProductRepository productRepository;

    /*
     * Repositório utilizado para salvar o estoque inicial.
     */
    private final InventoryRepository inventoryRepository;

    /*
     * ============================================================================
     * CONSTRUTOR
     * ============================================================================
     *
     * O Spring Boot injeta automaticamente os repositórios
     * necessários para execução deste caso de uso.
     */
    public CreateProductUseCase(
        ProductRepository productRepository,
        InventoryRepository inventoryRepository) {

        this.productRepository = productRepository;
        this.inventoryRepository = inventoryRepository;
    }

    /*
     * ============================================================================
     * MÉTODO: execute()
     * ============================================================================
     *
     * Responsabilidade:
     *
     * Criar um novo produto e seu estoque inicial.
     *
     * Fluxo:
     *
     * 1. Criar a entidade Product.
     * 2. Salvar o produto.
     * 3. Criar o estoque com quantidade zero.
     * 4. Salvar o estoque.
     * 5. Retornar o produto criado.
     */
    public Product execute(CreateProductRequest request) {

        /*
         * Cria a entidade Product a partir dos dados
         * recebidos no DTO.
         */
        Product product = new Product(
            request.getName(),
            request.getDescription(),
            new Money(request.getUnitPrice()),
            request.getType()
        );

        /*
         * Persiste o produto no banco de dados.
         */
        product = productRepository.save(product);

        /*
         * Todo produto nasce com estoque igual a zero.
         */
        Inventory inventory = new Inventory(
            product,
            Quantity.ZERO
        );

        /*
         * Persiste o estoque inicial.
         */
        inventoryRepository.save(inventory);

        /*
         * Retorna o produto criado.
         */
        return product;
    }

}
