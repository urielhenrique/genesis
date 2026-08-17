/**
 * ============================================================================
 * CLASSE: EventResponseMapper
 * ============================================================================
 *
 * CAMADA:
 * Application -> Event -> Mapper
 *
 * RESPONSABILIDADE:
 *
 * Converter a entidade de domínio Event para o DTO
 * EventResponse utilizado pela API.
 *
 * ============================================================================
 */
package com.genesis.application.event.mapper;

import com.genesis.application.event.dto.EventResponse;
import com.genesis.domain.event.Event;

import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Mapper responsável pelas respostas de Event.
 */
@Component
public class EventResponseMapper {

    /*
     * ============================================================================
     * MÉTODO: toResponse()
     * ============================================================================
     *
     * Converte Event → EventResponse.
     */
    public EventResponse toResponse(Event event) {

        return new EventResponse(
            event.getId(),
            event.getName(),
            event.getDescription(),
            event.getEventDate(),
            event.isActive(),
            event.getCreatedAt(),
            event.getUpdatedAt()
        );
    }

    /*
     * ============================================================================
     * MÉTODO: toResponseList()
     * ============================================================================
     *
     * Converte uma lista de Events para uma lista
     * de EventResponse.
     */
    public List<EventResponse> toResponseList(
        List<Event> events) {

        return events.stream()
            .map(this::toResponse)
            .toList();
    }
}
