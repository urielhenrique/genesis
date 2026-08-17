/**
 * ============================================================================
 * CLASSE: UpdateEventUseCase
 * ============================================================================
 *
 * CAMADA:
 * Application -> Event -> UseCase
 *
 * RESPONSABILIDADE:
 *
 * Atualizar um evento existente.
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
 * DTO contendo os novos dados do evento.
 */
import com.genesis.application.event.dto.UpdateEventRequest;

/*
 * Entidade de domínio.
 */
import com.genesis.domain.event.Event;

/*
 * Exceção lançada quando o evento não existe.
 */
import com.genesis.domain.exception.EventNotFoundException;

/*
 * Repository utilizado para localizar e salvar o evento.
 */
import com.genesis.domain.repository.EventRepository;

/*
 * Permite que o Spring gerencie este Use Case.
 */
import org.springframework.stereotype.Service;

/*
 * Identificador único do evento.
 */
import java.util.UUID;

/**
 * Use Case responsável pela atualização de eventos.
 */
@Service
public class UpdateEventUseCase {

    /*
     * ============================================================================
     * ATRIBUTOS
     * ============================================================================
     */

    private final EventRepository eventRepository;

    /*
     * ============================================================================
     * CONSTRUTOR
     * ============================================================================
     */
    public UpdateEventUseCase(
        EventRepository eventRepository) {

        this.eventRepository = eventRepository;
    }

    /*
     * ============================================================================
     * MÉTODO: execute()
     * ============================================================================
     *
     * Localiza o evento, atualiza seus dados através
     * da entidade de domínio e persiste a alteração.
     */
    public Event execute(
        UUID id,
        UpdateEventRequest request) {

        /*
         * Localiza o evento.
         *
         * Se não existir, lança a exceção.
         */
        Event event =
            eventRepository
                .findById(id)
                .orElseThrow(() ->
                    new EventNotFoundException(id)
                );

        /*
         * A atualização é realizada pela entidade
         * de domínio.
         */
        event.update(
            request.getName(),
            request.getDescription(),
            request.getEventDate()
        );

        /*
         * Persiste o evento atualizado.
         */
        return eventRepository.save(event);
    }

    /*
     * ============================================================================
     * RESUMO
     * ============================================================================
     *
     * ID + Request
     *      ↓
     * Repository.findById()
     *      ↓
     * Event.update()
     *      ↓
     * Repository.save()
     *
     * ============================================================================
     */
}
