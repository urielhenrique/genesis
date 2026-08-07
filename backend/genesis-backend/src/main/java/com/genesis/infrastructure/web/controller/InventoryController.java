/**
 * ============================================================================
 * CLASSE: InventoryController
 * ============================================================================
 *
 * CAMADA:
 * Infrastructure -> Web -> Controller
 *
 * RESPONSABILIDADE:
 *
 * Expor os endpoints REST relacionados ao estoque.
 *
 * O Controller recebe requisições HTTP,
 * chama o Use Case correspondente e devolve
 * a resposta para o cliente.
 *
 * Nenhuma regra de negócio deve ficar nesta classe.
 *
 * ============================================================================
 *
 * FLUXO:
 *
 * Cliente
 *      ↓
 * InventoryController
 *      ↓
 * UseCase
 *      ↓
 * Repository
 *      ↓
 * Banco de Dados
 *
 * ============================================================================
 */
package com.genesis.infrastructure.web.controller;

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
 * DTO utilizado para registrar uma movimentação.
 */
import com.genesis.application.inventory.dto.RegisterInventoryMovementRequest;

/*
 * Responsável por converter Inventory para
 * InventoryResponse.
 */
import com.genesis.application.inventory.mapper.InventoryResponseMapper;

/*
 * Use Cases da aplicação.
 */
import com.genesis.application.inventory.usecase.FindInventoryByProductUseCase;
import com.genesis.application.inventory.usecase.ListInventoryUseCase;
import com.genesis.application.inventory.usecase.RegisterInventoryMovementUseCase;

/*
 * Validação automática dos DTOs.
 */
import jakarta.validation.Valid;

/*
 * Classes do Spring Web.
 */
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/*
 * Classes da API Java.
 */
import java.util.List;
import java.util.UUID;

/**
 * Controller responsável pelos endpoints
 * relacionados ao estoque.
 */
@RestController

/**
 * Prefixo comum utilizado pelos endpoints da API.
 *
 * Endpoints disponíveis:
 *
 * GET    /api/inventory
 * GET    /api/inventory/{productId}
 * POST   /api/inventory/movements
 */
@RequestMapping("/api/inventory")
public class InventoryController {

    /*
     * ============================================================================
     * ATRIBUTOS
     * ============================================================================
     */

    /*
     * Caso de uso responsável por listar os estoques.
     */
    private final ListInventoryUseCase listInventoryUseCase;

    /*
     * Caso de uso responsável por localizar
     * o estoque de um produto.
     */
    private final FindInventoryByProductUseCase findInventoryByProductUseCase;

    /*
     * Caso de uso responsável por registrar
     * movimentações de estoque.
     */
    private final RegisterInventoryMovementUseCase registerInventoryMovementUseCase;

    /*
     * Responsável por converter entidades
     * de domínio para DTOs de resposta.
     */
    private final InventoryResponseMapper responseMapper;

    /*
     * ============================================================================
     * CONSTRUTOR
     * ============================================================================
     *
     * O Spring Boot injeta automaticamente
     * todas as dependências necessárias.
     */
    public InventoryController(
        ListInventoryUseCase listInventoryUseCase,
        FindInventoryByProductUseCase findInventoryByProductUseCase,
        RegisterInventoryMovementUseCase registerInventoryMovementUseCase,
        InventoryResponseMapper responseMapper) {

        this.listInventoryUseCase = listInventoryUseCase;
        this.findInventoryByProductUseCase = findInventoryByProductUseCase;
        this.registerInventoryMovementUseCase = registerInventoryMovementUseCase;
        this.responseMapper = responseMapper;
    }

    /*
     * ============================================================================
     * MÉTODO: list()
     * ============================================================================
     *
     * Endpoint:
     *
     * GET /api/inventory
     *
     * Responsabilidade:
     *
     * Retornar todos os estoques cadastrados.
     */
    @GetMapping
    public List<InventoryResponse> list() {

        return listInventoryUseCase.execute()
            .stream()
            .map(responseMapper::toResponse)
            .toList();
    }

    /*
     * ============================================================================
     * MÉTODO: findByProductId()
     * ============================================================================
     *
     * Endpoint:
     *
     * GET /api/inventory/{productId}
     *
     * Responsabilidade:
     *
     * Buscar o estoque de um produto.
     */
    @GetMapping("/{productId}")
    public InventoryResponse findByProductId(
        @PathVariable UUID productId) {

        return responseMapper.toResponse(
            findInventoryByProductUseCase.execute(productId)
        );
    }

    /*
     * ============================================================================
     * MÉTODO: registerMovement()
     * ============================================================================
     *
     * Endpoint:
     *
     * POST /api/inventory/movements
     *
     * Responsabilidade:
     *
     * Registrar uma entrada, saída ou ajuste
     * de estoque.
     *
     * Retorna:
     *
     * HTTP 201 (Created)
     */
    @PostMapping("/movements")
    @ResponseStatus(HttpStatus.CREATED)
    public InventoryResponse registerMovement(
        @Valid @RequestBody RegisterInventoryMovementRequest request) {

        return responseMapper.toResponse(
            registerInventoryMovementUseCase.execute(request)
        );
    }

    /*
     * ============================================================================
     * RESUMO
     * ============================================================================
     *
     * Conceitos aprendidos:
     *
     * ✔ REST Controller
     * ✔ Endpoints HTTP
     * ✔ @RestController
     * ✔ @RequestMapping
     * ✔ @GetMapping
     * ✔ @PostMapping
     * ✔ @RequestBody
     * ✔ @PathVariable
     * ✔ @ResponseStatus
     * ✔ @Valid
     * ✔ DTO
     * ✔ Use Case
     * ✔ Response Mapper
     *
     * ============================================================================
     */
}
