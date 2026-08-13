/**
 * ============================================================================
 * CLASSE: FinancialTransactionController
 * ============================================================================
 *
 * CAMADA:
 * Infrastructure -> Web -> Controller
 *
 * RESPONSABILIDADE:
 *
 * Expor a API REST de movimentações financeiras.
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
 * POST   /api/financial/transactions
 * GET    /api/financial/transactions
 * GET    /api/financial/transactions/{id}
 * PUT    /api/financial/transactions/{id}
 * DELETE /api/financial/transactions/{id}
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
import com.genesis.application.financial.dto.CreateFinancialTransactionRequest;

/*
 * DTO utilizado para atualização.
 */
import com.genesis.application.financial.dto.UpdateFinancialTransactionRequest;

/*
 * DTO retornado pela API.
 */
import com.genesis.application.financial.dto.FinancialTransactionResponse;

/*
 * Mapper responsável por converter Domain → Response.
 */
import com.genesis.application.financial.mapper.FinancialTransactionResponseMapper;

/*
 * Use Case para criação.
 */
import com.genesis.application.financial.usecase.CreateFinancialTransactionUseCase;

/*
 * Use Case para atualização.
 */
import com.genesis.application.financial.usecase.UpdateFinancialTransactionUseCase;

/*
 * Use Case para listagem.
 */
import com.genesis.application.financial.usecase.ListFinancialTransactionsUseCase;

/*
 * Use Case para busca por ID.
 */
import com.genesis.application.financial.usecase.FindFinancialTransactionByIdUseCase;

/*
 * Use Case para exclusão.
 */
import com.genesis.application.financial.usecase.DeleteFinancialTransactionUseCase;

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
 * Identificador único da movimentação.
 */
import java.util.UUID;

/**
 * Controller responsável pelos endpoints de movimentações financeiras.
 */
@RestController

/*
 * Prefixo de todos os endpoints deste Controller.
 */
@RequestMapping("/api/financial/transactions")
public class FinancialTransactionController {

    /*
     * ============================================================================
     * ATRIBUTOS
     * ============================================================================
     */

    /*
     * Use Case responsável pela criação.
     */
    private final CreateFinancialTransactionUseCase createUseCase;

    /*
     * Use Case responsável pela atualização.
     */
    private final UpdateFinancialTransactionUseCase updateUseCase;

    /*
     * Use Case responsável pela listagem.
     */
    private final ListFinancialTransactionsUseCase listUseCase;

    /*
     * Use Case responsável pela busca por ID.
     */
    private final FindFinancialTransactionByIdUseCase findByIdUseCase;

    /*
     * Use Case responsável pela exclusão.
     */
    private final DeleteFinancialTransactionUseCase deleteUseCase;

    /*
     * Mapper responsável pela conversão
     * Domain → Response.
     */
    private final FinancialTransactionResponseMapper responseMapper;

    /*
     * ============================================================================
     * CONSTRUTOR
     * ============================================================================
     *
     * O Spring injeta automaticamente todas
     * as dependências.
     */
    public FinancialTransactionController(
        CreateFinancialTransactionUseCase createUseCase,
        UpdateFinancialTransactionUseCase updateUseCase,
        ListFinancialTransactionsUseCase listUseCase,
        FindFinancialTransactionByIdUseCase findByIdUseCase,
        DeleteFinancialTransactionUseCase deleteUseCase,
        FinancialTransactionResponseMapper responseMapper) {

        this.createUseCase = createUseCase;
        this.updateUseCase = updateUseCase;
        this.listUseCase = listUseCase;
        this.findByIdUseCase = findByIdUseCase;
        this.deleteUseCase = deleteUseCase;
        this.responseMapper = responseMapper;
    }

    /*
     * ============================================================================
     * MÉTODO: create()
     * ============================================================================
     *
     * Endpoint:
     *
     * POST /api/financial/transactions
     *
     * Responsabilidade:
     *
     * Criar uma nova movimentação financeira.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FinancialTransactionResponse create(
        @Valid @RequestBody CreateFinancialTransactionRequest request) {

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
     * GET /api/financial/transactions
     *
     * Responsabilidade:
     *
     * Listar todas as movimentações financeiras.
     */
    @GetMapping
    public List<FinancialTransactionResponse> findAll() {

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
     * GET /api/financial/transactions/{id}
     *
     * Responsabilidade:
     *
     * Buscar uma movimentação pelo ID.
     */
    @GetMapping("/{id}")
    public FinancialTransactionResponse findById(
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
     * PUT /api/financial/transactions/{id}
     *
     * Responsabilidade:
     *
     * Atualizar uma movimentação financeira.
     */
    @PutMapping("/{id}")
    public FinancialTransactionResponse update(
        @PathVariable UUID id,
        @Valid @RequestBody UpdateFinancialTransactionRequest request) {

        return responseMapper.toResponse(
            updateUseCase.execute(id, request)
        );
    }

    /*
     * ============================================================================
     * MÉTODO: delete()
     * ============================================================================
     *
     * Endpoint:
     *
     * DELETE /api/financial/transactions/{id}
     *
     * Responsabilidade:
     *
     * Excluir uma movimentação financeira.
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {

        deleteUseCase.execute(id);
    }

    /*
     * ============================================================================
     * RESUMO
     * ============================================================================
     *
     * Endpoints:
     *
     * POST   /api/financial/transactions
     * GET    /api/financial/transactions
     * GET    /api/financial/transactions/{id}
     * PUT    /api/financial/transactions/{id}
     * DELETE /api/financial/transactions/{id}
     *
     * Conceitos:
     *
     * ✔ REST Controller
     * ✔ HTTP Methods
     * ✔ DTO
     * ✔ Validation
     * ✔ Use Case
     * ✔ Response Mapper
     * ✔ @PathVariable
     * ✔ @RequestBody
     *
     * ============================================================================
     */
}
