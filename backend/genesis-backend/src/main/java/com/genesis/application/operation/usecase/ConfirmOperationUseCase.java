package com.genesis.application.operation.usecase;

import com.genesis.domain.operation.Operation;
import com.genesis.domain.repository.OperationRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ConfirmOperationUseCase {

    private final OperationRepository operationRepository;

    public ConfirmOperationUseCase(
        OperationRepository operationRepository) {

        this.operationRepository = operationRepository;
    }

    public Operation execute(UUID operationId) {

        Operation operation = operationRepository
            .findById(operationId)
            .orElseThrow(() ->
                new IllegalArgumentException(
                    "Operation not found: " + operationId
                )
            );

        operation.confirm();

        return operationRepository.save(operation);
    }
}
