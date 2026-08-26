package com.genesis.domain.repository;

import com.genesis.domain.operation.Operation;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OperationRepository {

    Operation save(Operation operation);

    Optional<Operation> findById(UUID id);

    List<Operation> findAll();

    void delete(Operation operation);
}
