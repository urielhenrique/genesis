/**
 * ============================================================================
 * CLASSE: DeleteEventUseCase
 * ============================================================================
 *
 * CAMADA:
 * Application -> Event -> UseCase
 *
 * RESPONSABILIDADE:
 *
 * Excluir um evento existente.
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
 * Use Case responsável pela exclusão de eventos.
 */
@Service
public class DeleteEventUseCase {

    /*
     * ============================================================================
     * ATRIBUTOS
     * ============================================================================
     */

    /*
     * Repository utilizado para localizar e excluir
     * o evento.
     */
    private final EventRepository eventRepository;

    /*
     * ============================================================================
     * CONSTRUTOR
     * ============================================================================
     */
    public DeleteEventUseCase(
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
     * Localizar o evento e removê-lo.
     */
    public void execute(UUID id) {

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
         * Remove o evento através do Repository.
         */
        eventRepository.delete(event);
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
     *  ↓
     * EventRepository.delete()
     *
     * ============================================================================
     */
}
