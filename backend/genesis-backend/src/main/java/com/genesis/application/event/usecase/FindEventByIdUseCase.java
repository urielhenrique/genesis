/**
 * ============================================================================
 * CLASSE: FindEventByIdUseCase
 * ============================================================================
 *
 * CAMADA:
 * Application -> Event -> UseCase
 *
 * RESPONSABILIDADE:
 *
 * Buscar um evento pelo seu identificador.
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
 * Entidade de domínio do evento.
 */
import com.genesis.domain.event.Event;

/*
 * Exceção lançada quando o evento não existe.
 */
import com.genesis.domain.exception.EventNotFoundException;

/*
 * Contrato de persistência dos eventos.
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
 * Use Case responsável por buscar um evento pelo ID.
 */
@Service
public class FindEventByIdUseCase {

    /*
     * ============================================================================
     * ATRIBUTOS
     * ============================================================================
     */

    /*
     * Repository utilizado para localizar o evento.
     */
    private final EventRepository eventRepository;

    /*
     * ============================================================================
     * CONSTRUTOR
     * ============================================================================
     */
    public FindEventByIdUseCase(
        EventRepository eventRepository) {

        this.eventRepository = eventRepository;
    }

    /*
     * ============================================================================
     * MÉTODO: execute()
     * ============================================================================
     *
     * Busca o evento pelo ID.
     *
     * Caso não exista, lança EventNotFoundException.
     */
    public Event execute(UUID id) {

        return eventRepository
            .findById(id)
            .orElseThrow(() ->
                new EventNotFoundException(id)
            );
    }

    /*
     * ============================================================================
     * RESUMO
     * ============================================================================
     *
     * ID
     *  ↓
     * EventRepository.findById()
     *  ↓
     * Event
     *
     * Se não encontrar:
     *
     * EventNotFoundException
     *
     * ============================================================================
     */
}
