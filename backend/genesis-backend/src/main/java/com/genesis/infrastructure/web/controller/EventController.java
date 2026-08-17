/**
 * ============================================================================
 * CLASSE: EventController
 * ============================================================================
 *
 * CAMADA:
 * Infrastructure -> Web -> Controller
 *
 * RESPONSABILIDADE:
 *
 * Expor a API REST de Eventos.
 *
 * O Controller:
 *
 * • Recebe requisições HTTP.
 * • Valida os dados de entrada.
 * • Chama o Use Case apropriado.
 * • Converte o resultado para Response.
 *
 * O Controller NÃO possui regra de negócio.
 *
 * ============================================================================
 *
 * ENDPOINTS:
 *
 * POST   /api/events
 * GET    /api/events
 * GET    /api/events/{id}
 * PUT    /api/events/{id}
 * DELETE /api/events/{id}
 * PATCH  /api/events/{id}/activate
 * PATCH  /api/events/{id}/desactivate
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
import com.genesis.application.event.dto.CreateEventRequest;

/*
 * DTO utilizado para atualização.
 */
import com.genesis.application.event.dto.UpdateEventRequest;

/*
 * DTO retornado pela API.
 */
import com.genesis.application.event.dto.EventResponse;

/*
 * Mapper responsável por converter Event → EventResponse.
 */
import com.genesis.application.event.mapper.EventResponseMapper;

/*
 * Use Case para criação.
 */
import com.genesis.application.event.usecase.CreateEventUseCase;

/*
 * Use Case para atualização.
 */
import com.genesis.application.event.usecase.UpdateEventUseCase;

/*
 * Use Case para listagem.
 */
import com.genesis.application.event.usecase.ListEventsUseCase;

/*
 * Use Case para busca por ID.
 */
import com.genesis.application.event.usecase.FindEventByIdUseCase;

/*
 * Use Case para exclusão.
 */
import com.genesis.application.event.usecase.DeleteEventUseCase;

/*
 * Use Case para ativação.
 */
import com.genesis.application.event.usecase.ActivateEventUseCase;

/*
 * Use Case para desativação.
 */
import com.genesis.application.event.usecase.DesactivateEventUseCase;

/*
 * Validação automática dos DTOs.
 */
import jakarta.validation.Valid;

/*
 * Classes HTTP do Spring.
 */
import org.springframework.http.HttpStatus;

/*
 * Anotações utilizadas nos endpoints REST.
 */
import org.springframework.web.bind.annotation.*;

/*
 * Lista utilizada nas respostas.
 */
import java.util.List;

/*
 * Identificador único do evento.
 */
import java.util.UUID;

/**
 * Controller responsável pelos endpoints de Evento.
 */
@RestController

/*
 * Prefixo de todos os endpoints.
 */
@RequestMapping("/api/events")
public class EventController {

    /*
     * ============================================================================
     * ATRIBUTOS
     * ============================================================================
     */

    private final CreateEventUseCase createUseCase;

    private final ListEventsUseCase listUseCase;

    private final FindEventByIdUseCase findByIdUseCase;

    private final UpdateEventUseCase updateUseCase;

    private final DeleteEventUseCase deleteUseCase;

    private final ActivateEventUseCase activateUseCase;

    private final DesactivateEventUseCase desactivateUseCase;

    /*
     * Mapper responsável pela conversão
     * Domain → Response.
     */
    private final EventResponseMapper responseMapper;

    /*
     * ============================================================================
     * CONSTRUTOR
     * ============================================================================
     */
    public EventController(
        CreateEventUseCase createUseCase,
        ListEventsUseCase listUseCase,
        FindEventByIdUseCase findByIdUseCase,
        UpdateEventUseCase updateUseCase,
        DeleteEventUseCase deleteUseCase,
        ActivateEventUseCase activateUseCase,
        DesactivateEventUseCase desactivateUseCase,
        EventResponseMapper responseMapper) {

        this.createUseCase = createUseCase;
        this.listUseCase = listUseCase;
        this.findByIdUseCase = findByIdUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
        this.activateUseCase = activateUseCase;
        this.desactivateUseCase = desactivateUseCase;
        this.responseMapper = responseMapper;
    }

    /*
     * ============================================================================
     * MÉTODO: create()
     * ============================================================================
     *
     * POST /api/events
     *
     * Cria um novo evento.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EventResponse create(
        @Valid @RequestBody CreateEventRequest request) {

        return responseMapper.toResponse(
            createUseCase.execute(request)
        );
    }

    /*
     * ============================================================================
     * MÉTODO: findAll()
     * ============================================================================
     *
     * GET /api/events
     *
     * Lista todos os eventos.
     */
    @GetMapping
    public List<EventResponse> findAll() {

        return responseMapper.toResponseList(
            listUseCase.execute()
        );
    }

    /*
     * ============================================================================
     * MÉTODO: findById()
     * ============================================================================
     *
     * GET /api/events/{id}
     *
     * Busca um evento pelo ID.
     */
    @GetMapping("/{id}")
    public EventResponse findById(
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
     * PUT /api/events/{id}
     *
     * Atualiza os dados do evento.
     */
    @PutMapping("/{id}")
    public EventResponse update(
        @PathVariable UUID id,
        @Valid @RequestBody UpdateEventRequest request) {

        return responseMapper.toResponse(
            updateUseCase.execute(id, request)
        );
    }

    /*
     * ============================================================================
     * MÉTODO: delete()
     * ============================================================================
     *
     * DELETE /api/events/{id}
     *
     * Exclui o evento.
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
        @PathVariable UUID id) {

        deleteUseCase.execute(id);
    }

    /*
     * ============================================================================
     * MÉTODO: activate()
     * ============================================================================
     *
     * PATCH /api/events/{id}/activate
     *
     * Ativa o evento.
     */
    @PatchMapping("/{id}/activate")
    public EventResponse activate(
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
     * PATCH /api/events/{id}/desactivate
     *
     * Desativa o evento.
     */
    @PatchMapping("/{id}/desactivate")
    public EventResponse desactivate(
        @PathVariable UUID id) {

        return responseMapper.toResponse(
            desactivateUseCase.execute(id)
        );
    }
}
