package com.genesis.infrastructure.web.controller;

import com.genesis.application.operation.usecase.CancelOperationUseCase;
import com.genesis.application.operation.usecase.ConfirmOperationUseCase;
import com.genesis.application.operation.usecase.CreateOperationUseCase;
import com.genesis.domain.operation.Operation;
import com.genesis.domain.repository.OperationRepository;
import com.genesis.domain.shared.enums.OperationType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/operations")
public class OperationController {

    private final CreateOperationUseCase createOperationUseCase;
    private final OperationRepository operationRepository;
    private final ConfirmOperationUseCase confirmOperationUseCase;
    private final CancelOperationUseCase cancelOperationUseCase;

    public OperationController(
        CreateOperationUseCase createOperationUseCase,
        OperationRepository operationRepository,
        ConfirmOperationUseCase confirmOperationUseCase,
        CancelOperationUseCase cancelOperationUseCase) {

        this.createOperationUseCase = createOperationUseCase;
        this.operationRepository = operationRepository;
        this.confirmOperationUseCase = confirmOperationUseCase;
        this.cancelOperationUseCase = cancelOperationUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Operation create(
        @RequestBody CreateOperationRequest request) {

        List<CreateOperationUseCase.ItemInput> items =
            request.items()
                .stream()
                .map(item ->
                    new CreateOperationUseCase.ItemInput(
                        item.productId(),
                        item.quantity(),
                        item.unitPrice()
                    )
                )
                .toList();

        return createOperationUseCase.execute(
            request.type(),
            request.operationDate(),
            request.description(),
            items
        );
    }

    @PostMapping("/{id}/confirm")
    public Operation confirm(@PathVariable UUID id) {

        return confirmOperationUseCase.execute(id);
    }

    @PostMapping("/{id}/cancel")
    public Operation cancel(@PathVariable UUID id) {

        return cancelOperationUseCase.execute(id);
    }

    @GetMapping("/{id}")
    public Operation findById(@PathVariable UUID id) {

        return operationRepository
            .findById(id)
            .orElseThrow(() ->
                new IllegalArgumentException(
                    "Operation not found: " + id
                )
            );
    }

    @GetMapping
    public List<Operation> findAll() {

        return operationRepository.findAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {

        Operation operation =
            operationRepository
                .findById(id)
                .orElseThrow(() ->
                    new IllegalArgumentException(
                        "Operation not found: " + id
                    )
                );

        operationRepository.delete(operation);
    }

    public record CreateOperationRequest(
        OperationType type,
        LocalDateTime operationDate,
        String description,
        List<ItemRequest> items
    ) {
    }

    public record ItemRequest(
        UUID productId,
        BigDecimal quantity,
        BigDecimal unitPrice
    ) {
    }
}
