package com.genesis.application.inventory.usecase;

import com.genesis.domain.exception.ProductNotFoundException;
import com.genesis.domain.inventory.Inventory;
import com.genesis.domain.repository.InventoryRepository;
import com.genesis.domain.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FindInventoryByProductUseCase {

    private final InventoryRepository inventoryRepository;
    private final ProductRepository productRepository;

    public FindInventoryByProductUseCase(
        InventoryRepository inventoryRepository,
        ProductRepository productRepository) {

        this.inventoryRepository = inventoryRepository;
        this.productRepository = productRepository;
    }

    public Inventory execute(UUID productId) {

        productRepository.findById(productId)
            .orElseThrow(() -> new ProductNotFoundException(productId));

        return inventoryRepository
            .findByProductId(productId)
            .orElseThrow(() ->
                new IllegalArgumentException("Inventory not found."));
    }
}
