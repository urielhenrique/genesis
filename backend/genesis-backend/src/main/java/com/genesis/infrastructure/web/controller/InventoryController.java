package com.genesis.infrastructure.web.controller;

import com.genesis.application.inventory.dto.InventoryResponse;
import com.genesis.application.inventory.dto.RegisterInventoryMovementRequest;
import com.genesis.application.inventory.mapper.InventoryResponseMapper;
import com.genesis.application.inventory.usecase.FindInventoryByProductUseCase;
import com.genesis.application.inventory.usecase.ListInventoryUseCase;
import com.genesis.application.inventory.usecase.RegisterInventoryMovementUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final ListInventoryUseCase listInventoryUseCase;
    private final FindInventoryByProductUseCase findInventoryByProductUseCase;
    private final RegisterInventoryMovementUseCase registerInventoryMovementUseCase;
    private final InventoryResponseMapper responseMapper;

    public InventoryController(
        ListInventoryUseCase listInventoryUseCase,
        FindInventoryByProductUseCase findInventoryByProductUseCase,
        RegisterInventoryMovementUseCase registerInventoryMovementUseCase,
        InventoryResponseMapper responseMapper) {

        this.listInventoryUseCase = listInventoryUseCase;
        this.findInventoryByProductUseCase = findInventoryByProductUseCase;
        this.registerInventoryMovementUseCase = registerInventoryMovementUseCase;
        this.responseMapper = responseMapper;
    }

    @GetMapping
    public List<InventoryResponse> list() {

        return listInventoryUseCase.execute()
            .stream()
            .map(responseMapper::toResponse)
            .toList();
    }

    @GetMapping("/{productId}")
    public InventoryResponse findByProductId(
        @PathVariable UUID productId) {

        return responseMapper.toResponse(
            findInventoryByProductUseCase.execute(productId)
        );
    }

    @PostMapping("/movements")
    @ResponseStatus(HttpStatus.OK)
    public InventoryResponse registerMovement(
        @Valid @RequestBody RegisterInventoryMovementRequest request) {

        return responseMapper.toResponse(
            registerInventoryMovementUseCase.execute(request)
        );
    }
}
