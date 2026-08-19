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
         * Como o EventRepository.findById()
         * utiliza apenas eventos ativos,
         * um evento já desativado será tratado
         * como inexistente.
         */
        Event event =
            eventRepository
                .findById(id)
                .orElseThrow(() ->
                    new EventNotFoundException(id)
                );

        /*
         * Exclusão lógica.
         *
         * O registro permanece no banco,
         * mas active passa para false.
         */
        event.deactivate();

        /*
         * Persiste a alteração.
         */
        eventRepository.save(event);
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
