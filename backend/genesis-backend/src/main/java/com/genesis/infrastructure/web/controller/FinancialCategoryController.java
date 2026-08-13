/**
 * ============================================================================
 * CLASSE: FinancialCategoryController
 * ============================================================================
 *
 * CAMADA:
 * Infrastructure -> Web -> Controller
 *
 * RESPONSABILIDADE:
 *
 * Expor a API REST de categorias financeiras.
 *
 * O Controller:
 *
 * • Recebe requisições HTTP.
 * • Valida os DTOs.
 * • Chama os Use Cases.
 * • Converte o resultado para Response.
 *
 * Não possui regra de negócio.
 *
 * ============================================================================
 *
 * ENDPOINTS:
 *
 * POST   /api/financial/categories
 * GET    /api/financial/categories
 * GET    /api/financial/categories/{id}
 * PUT    /api/financial/categories/{id}
 * PATCH  /api/financial/categories/{id}/activate
 * PATCH  /api/financial/categories/{id}/desactivate
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
 * DTO utilizado para criação.
 */
import com.genesis.application.financial.dto.CreateFinancialCategoryRequest;

/*
 * DTO utilizado para atualização.
 */
import com.genesis.application.financial.dto.UpdateFinancialCategoryRequest;

/*
 * DTO retornado pela API.
 */
import com.genesis.application.financial.dto.FinancialCategoryResponse;

/*
 * Mapper responsável por converter Domain → Response.
 */
import com.genesis.application.financial.mapper.FinancialCategoryResponseMapper;

/*
 * Use Case para criação.
 */
import com.genesis.application.financial.usecase.CreateFinancialCategoryUseCase;

/*
 * Use Case para atualização.
 */
import com.genesis.application.financial.usecase.UpdateFinancialCategoryUseCase;

/*
 * Use Case para listagem.
 */
import com.genesis.application.financial.usecase.ListFinancialCategoriesUseCase;

/*
 * Use Case para busca por ID.
 */
import com.genesis.application.financial.usecase.FindFinancialCategoryByIdUseCase;

/*
 * Use Case para ativação.
 */
import com.genesis.application.financial.usecase.ActivateFinancialCategoryUseCase;

/*
 * Use Case para desativação.
 */
import com.genesis.application.financial.usecase.DesactivateFinancialCategoryUseCase;

/*
 * Validação automática dos DTOs.
 */
import jakarta.validation.Valid;

/*
 * Classes HTTP do Spring.
 */
import org.springframework.http.HttpStatus;

/*
 * Anotações utilizadas para criação dos endpoints REST.
 */
import org.springframework.web.bind.annotation.*;

/*
 * Lista utilizada na resposta da API.
 */
import java.util.List;

/*
 * Identificador único da categoria.
 */
import java.util.UUID;

/**
 * Controller responsável pelos endpoints de categorias financeiras.
 */
@RestController

/*
 * Prefixo de todos os endpoints deste Controller.
 */
@RequestMapping("/api/financial/categories")
public class FinancialCategoryController {

    /*
     * ============================================================================
     * ATRIBUTOS
     * ============================================================================
     */

    private final CreateFinancialCategoryUseCase createUseCase;

    private final UpdateFinancialCategoryUseCase updateUseCase;

    private final ListFinancialCategoriesUseCase listUseCase;

    private final FindFinancialCategoryByIdUseCase findByIdUseCase;

    private final ActivateFinancialCategoryUseCase activateUseCase;

    private final DesactivateFinancialCategoryUseCase desactivateUseCase;

    /*
     * Mapper responsável pela conversão
     * Domain → Response.
     */
    private final FinancialCategoryResponseMapper responseMapper;

    /*
     * ============================================================================
     * CONSTRUTOR
     * ============================================================================
     *
     * O Spring injeta automaticamente todas
     * as dependências.
     */
    public FinancialCategoryController(
        CreateFinancialCategoryUseCase createUseCase,
        UpdateFinancialCategoryUseCase updateUseCase,
        ListFinancialCategoriesUseCase listUseCase,
        FindFinancialCategoryByIdUseCase findByIdUseCase,
        ActivateFinancialCategoryUseCase activateUseCase,
        DesactivateFinancialCategoryUseCase desactivateUseCase,
        FinancialCategoryResponseMapper responseMapper) {

        this.createUseCase = createUseCase;
        this.updateUseCase = updateUseCase;
        this.listUseCase = listUseCase;
        this.findByIdUseCase = findByIdUseCase;
        this.activateUseCase = activateUseCase;
        this.desactivateUseCase = desactivateUseCase;
        this.responseMapper = responseMapper;
    }

    /*
     * ============================================================================
     * MÉTODO: create()
     * ============================================================================
     *
     * Endpoint:
     *
     * POST /api/financial/categories
     *
     * Responsabilidade:
     *
     * Criar uma categoria financeira.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FinancialCategoryResponse create(
        @Valid @RequestBody CreateFinancialCategoryRequest request) {

        return responseMapper.toResponse(
            createUseCase.execute(request)
        );
    }

    /*
     * ============================================================================
     * MÉTODO: findAll()
     * ============================================================================
     *
     * Endpoint:
     *
     * GET /api/financial/categories
     *
     * Responsabilidade:
     *
     * Listar todas as categorias.
     */
    @GetMapping
    public List<FinancialCategoryResponse> findAll() {

        return listUseCase
            .execute()
            .stream()
            .map(responseMapper::toResponse)
            .toList();
    }

    /*
     * ============================================================================
     * MÉTODO: findById()
     * ============================================================================
     *
     * Endpoint:
     *
     * GET /api/financial/categories/{id}
     *
     * Responsabilidade:
     *
     * Buscar uma categoria pelo ID.
     */
    @GetMapping("/{id}")
    public FinancialCategoryResponse findById(
        @PathVariable UUID id) {

        return responseMapper.toResponse(
            findByIdUseCase.execute(id)
        );
    }

    /*
     * ============================================================================
     * MÉTODO: update()
     * ============================================================================
     *
     * Endpoint:
     *
     * PUT /api/financial/categories/{id}
     *
     * Responsabilidade:
     *
     * Atualizar uma categoria.
     */
    @PutMapping("/{id}")
    public FinancialCategoryResponse update(
        @PathVariable UUID id,
        @Valid @RequestBody UpdateFinancialCategoryRequest request) {

        return responseMapper.toResponse(
            updateUseCase.execute(id, request)
        );
    }

    /*
     * ============================================================================
     * MÉTODO: activate()
     * ============================================================================
     *
     * Endpoint:
     *
     * PATCH /api/financial/categories/{id}/activate
     *
     * Responsabilidade:
     *
     * Ativar uma categoria.
     */
    @PatchMapping("/{id}/activate")
    public FinancialCategoryResponse activate(
        @PathVariable UUID id) {

        return responseMapper.toResponse(
            activateUseCase.execute(id)
        );
    }

    /*
     * ============================================================================
     * MÉTODO: desactivate()
     * ============================================================================
     *
     * Endpoint:
     *
     * PATCH /api/financial/categories/{id}/desactivate
     *
     * Responsabilidade:
     *
     * Desativar uma categoria.
     */
    @PatchMapping("/{id}/desactivate")
    public FinancialCategoryResponse desactivate(
        @PathVariable UUID id) {

        return responseMapper.toResponse(
            desactivateUseCase.execute(id)
        );
    }

}
