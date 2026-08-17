/**
 * ============================================================================
 * CLASSE: CreateEventUseCase
 * ============================================================================
 *
 * CAMADA:
 * Application -> Event -> UseCase
 *
 * RESPONSABILIDADE:
 *
 * Criar um novo evento.
 *
 * ============================================================================
 */
package com.genesis.application.event.usecase;

/*
 * ============================================================================
 * IMPORTS
 * ============================================================================
 */

/*
 * DTO contendo os dados da requisição.
 */
import com.genesis.application.event.dto.CreateEventRequest;

/*
 * Entidade de domínio do evento.
 */
import com.genesis.domain.event.Event;

/*
 * Repository utilizado para persistir o evento.
 */
import com.genesis.domain.repository.EventRepository;

/*
 * Permite que o Spring gerencie este Use Case.
 */
import org.springframework.stereotype.Service;

/**
 * Use Case responsável pela criação de eventos.
 */
@Service
public class CreateEventUseCase {

    /*
     * ============================================================================
     * ATRIBUTOS
     * ============================================================================
     */

    /*
     * Repository utilizado para salvar o evento.
     */
    private final EventRepository eventRepository;

    /*
     * ============================================================================
     * CONSTRUTOR
     * ============================================================================
     */
    public CreateEventUseCase(
        EventRepository eventRepository) {

        this.eventRepository = eventRepository;
    }

    /*
     * ============================================================================
     * MÉTODO: execute()
     * ============================================================================
     *
     * Responsabilidade:
     *
     * Criar a entidade de domínio e persistir o evento.
     */
    public Event execute(
        CreateEventRequest request) {

        /*
         * Cria a entidade de domínio.
         *
         * As regras de negócio ficam dentro
         * da entidade Event.
         */
        Event event = new Event(
            request.getName(),
            request.getDescription(),
            request.getEventDate()
        );

        /*
         * Persiste o evento através do contrato
         * definido no Domain.
         */
        return eventRepository.save(event);
    }

    /*
     * ============================================================================
     * RESUMO
     * ============================================================================
     *
     * Fluxo:
     *
     * Request
     *    ↓
     * Event
     *    ↓
     * Repository.save()
     *    ↓
     * Event
     *
     * ============================================================================
     */
}
