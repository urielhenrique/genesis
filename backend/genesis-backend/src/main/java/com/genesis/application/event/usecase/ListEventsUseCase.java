/**
 * ============================================================================
 * CLASSE: ListEventsUseCase
 * ============================================================================
 *
 * CAMADA:
 * Application -> Event -> UseCase
 *
 * RESPONSABILIDADE:
 *
 * Buscar todos os eventos cadastrados.
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
 * Contrato de persistência dos eventos.
 */
import com.genesis.domain.repository.EventRepository;

/*
 * Permite que o Spring gerencie este Use Case.
 */
import org.springframework.stereotype.Service;

/*
 * Lista de eventos.
 */
import java.util.List;

/**
 * Use Case responsável por listar eventos.
 */
@Service
public class ListEventsUseCase {

    /*
     * ============================================================================
     * ATRIBUTOS
     * ============================================================================
     */

    /*
     * Repository utilizado para consultar os eventos.
     */
    private final EventRepository eventRepository;

    /*
     * ============================================================================
     * CONSTRUTOR
     * ============================================================================
     */
    public ListEventsUseCase(
        EventRepository eventRepository) {

        this.eventRepository = eventRepository;
    }

    /*
     * ============================================================================
     * MÉTODO: execute()
     * ============================================================================
     *
     * Retorna todos os eventos cadastrados.
     */
    public List<Event> execute() {

        return eventRepository.findAll();
    }

    /*
     * ============================================================================
     * RESUMO
     * ============================================================================
     *
     * Controller
     *      ↓
     * execute()
     *      ↓
     * EventRepository.findAll()
     *      ↓
     * List<Event>
     *
     * ============================================================================
     */
}
