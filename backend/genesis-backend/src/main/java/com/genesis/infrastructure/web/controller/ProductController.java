/**
 * ============================================================================
 * CLASSE: ProductController
 * ============================================================================
 *
 * CAMADA:
 * Infrastructure -> Web -> Controller
 *
 * RESPONSABILIDADE:
 *
 * Expor a API REST de Produtos.
 *
 * O Controller é a porta de entrada da aplicação.
 *
 * Sua responsabilidade é:
 *
 * • Receber requisições HTTP.
 * • Validar os dados de entrada.
 * • Chamar o Use Case apropriado.
 * • Converter o resultado para Response.
 * • Retornar a resposta ao cliente.
 *
 * O Controller NÃO possui regra de negócio.
 *
 * ============================================================================
 *
 * FLUXO:
 *
 * Cliente (Postman / Front-end)
 *              ↓
 * ProductController
 *              ↓
 * UseCase
 *              ↓
 * Repository
 *              ↓
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
 * DTO utilizado para criação de produtos.
 */
import com.genesis.application.product.dto.CreateProductRequest;

/*
 * DTO utilizado para atualização de produtos.
 */
import com.genesis.application.product.dto.UpdateProductRequest;

/*
 * Responsável por converter Product para ProductResponse.
 */
import com.genesis.application.product.mapper.ProductResponseMapper;

/*
 * DTO retornado pela API.
 */
import com.genesis.application.product.dto.ProductResponse;

/*
 * Use Cases da aplicação.
 */
import com.genesis.application.product.usecase.ActivateProductUseCase;
import com.genesis.application.product.usecase.CreateProductUseCase;
import com.genesis.application.product.usecase.DesactivateProductUseCase;
import com.genesis.application.product.usecase.FindProductByIdUseCase;
import com.genesis.application.product.usecase.ListProductsUseCase;
import com.genesis.application.product.usecase.UpdateProductUseCase;

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
 * Controller responsável pelos endpoints de Produto.
 */
@RestController

/**
 * Prefixo utilizado por todos os endpoints.
 *
 * Exemplo:
 *
 * /api/products
 */
@RequestMapping("/api/products")
public class ProductController {

    /*
     * ============================================================================
     * ATRIBUTOS
     * ============================================================================
     */

    /*
     * Cada Use Case representa uma ação da aplicação.
     */
    private final CreateProductUseCase createProductUseCase;
    private final ListProductsUseCase listProductsUseCase;
    private final FindProductByIdUseCase findProductByIdUseCase;
    private final UpdateProductUseCase updateProductUseCase;
    private final ActivateProductUseCase activateProductUseCase;
    private final DesactivateProductUseCase desactivateProductUseCase;

    /*
     * Responsável por converter o domínio
     * para o DTO de resposta.
     */
    private final ProductResponseMapper responseMapper;

    /*
     * ============================================================================
     * CONSTRUTOR
     * ============================================================================
     *
     * O Spring injeta automaticamente todas
     * as dependências necessárias.
     */
    public ProductController(
        CreateProductUseCase createProductUseCase,
        ListProductsUseCase listProductsUseCase,
        FindProductByIdUseCase findProductByIdUseCase,
        UpdateProductUseCase updateProductUseCase,
        ActivateProductUseCase activateProductUseCase,
        DesactivateProductUseCase desactivateProductUseCase,
        ProductResponseMapper responseMapper) {

        this.createProductUseCase = createProductUseCase;
        this.listProductsUseCase = listProductsUseCase;
        this.findProductByIdUseCase = findProductByIdUseCase;
        this.updateProductUseCase = updateProductUseCase;
        this.activateProductUseCase = activateProductUseCase;
        this.desactivateProductUseCase = desactivateProductUseCase;
        this.responseMapper = responseMapper;
    }

    /*
     * ============================================================================
     * MÉTODO: create()
     * ============================================================================
     *
     * Endpoint:
     *
     * POST /api/products
     *
     * Responsabilidade:
     *
     * Criar um novo produto.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse create(
        @Valid @RequestBody CreateProductRequest request) {

        return responseMapper.toResponse(
            createProductUseCase.execute(request)
        );
    }

    /*
     * ============================================================================
     * MÉTODO: findAll()
     * ============================================================================
     *
     * Endpoint:
     *
     * GET /api/products
     *
     * Responsabilidade:
     *
     * Listar todos os produtos.
     */
    @GetMapping
    public List<ProductResponse> findAll() {

        return responseMapper.toResponseList(
            listProductsUseCase.execute()
        );
    }

    /*
     * ============================================================================
     * MÉTODO: findById()
     * ============================================================================
     *
     * Endpoint:
     *
     * GET /api/products/{id}
     *
     * Responsabilidade:
     *
     * Buscar um produto pelo seu identificador.
     */
    @GetMapping("/{id}")
    public ProductResponse findById(@PathVariable UUID id) {

        return responseMapper.toResponse(
            findProductByIdUseCase.execute(id)
        );
    }

    /*
     * ============================================================================
     * MÉTODO: update()
     * ============================================================================
     *
     * Endpoint:
     *
     * PUT /api/products/{id}
     *
     * Responsabilidade:
     *
     * Atualizar os dados de um produto.
     */
    @PutMapping("/{id}")
    public ProductResponse update(
        @PathVariable UUID id,
        @Valid @RequestBody UpdateProductRequest request) {

        return responseMapper.toResponse(
            updateProductUseCase.execute(id, request)
        );
    }

    /*
     * ============================================================================
     * MÉTODO: activate()
     * ============================================================================
     *
     * Endpoint:
     *
     * PATCH /api/products/{id}/activate
     *
     * Responsabilidade:
     *
     * Ativar um produto.
     */
    @PatchMapping("/{id}/activate")
    public ProductResponse activate(@PathVariable UUID id) {

        return responseMapper.toResponse(
            activateProductUseCase.execute(id)
        );
    }

    /*
     * ============================================================================
     * MÉTODO: desactivate()
     * ============================================================================
     *
     * Endpoint:
     *
     * PATCH /api/products/{id}/desactivate
     *
     * Responsabilidade:
     *
     * Desativar um produto.
     */
    @PatchMapping("/{id}/desactivate")
    public ProductResponse desactivate(@PathVariable UUID id) {

        return responseMapper.toResponse(
            desactivateProductUseCase.execute(id)
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
     * ✔ @PostMapping
     * ✔ @GetMapping
     * ✔ @PutMapping
     * ✔ @PatchMapping
     * ✔ @RequestBody
     * ✔ @PathVariable
     * ✔ @Valid
     * ✔ DTO
     * ✔ Use Case
     * ✔ Response Mapper
     *
     * ============================================================================
     */
}
