/**
 * ============================================================================
 * CLASSE: ActivateEventUseCase
 * ============================================================================
 *
 * CAMADA:
 * Application -> Event -> UseCase
 *
 * RESPONSABILIDADE:
 *
 * Ativar um evento existente.
 *
 * ============================================================================
 */
package com.genesis.application.event.usecase;

import com.genesis.domain.event.Event;
import com.genesis.domain.exception.EventNotFoundException;
import com.genesis.domain.repository.EventRepository;

import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Use Case responsável por ativar eventos.
 */
@Service
public class ActivateEventUseCase {

    /*
     * Repository utilizado para localizar e salvar
     * o evento.
     */
    private final EventRepository eventRepository;

    /*
     * ============================================================================
     * CONSTRUTOR
     * ============================================================================
     */
    public ActivateEventUseCase(
        EventRepository eventRepository) {

        this.eventRepository = eventRepository;
    }

    /*
     * ============================================================================
     * MÉTODO: execute()
     * ============================================================================
     */
    public Event execute(UUID id) {

        /*
         * Localiza o evento.
         */
        Event event =
            eventRepository
                .findById(id)
                .orElseThrow(() ->
                    new EventNotFoundException(id)
                );

        /*
         * Altera o estado através da entidade de domínio.
         */
        event.activate();

        /*
         * Persiste a alteração.
         */
        return eventRepository.save(event);
    }
}
