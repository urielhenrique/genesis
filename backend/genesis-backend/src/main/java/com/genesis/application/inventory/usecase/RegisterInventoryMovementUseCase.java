package com.genesis.application.inventory.usecase;

import com.genesis.application.inventory.dto.RegisterInventoryMovementRequest;
import com.genesis.domain.exception.ProductNotFoundException;
import com.genesis.domain.inventory.Inventory;
import com.genesis.domain.inventory.InventoryMovement;
import com.genesis.domain.inventory.InventoryMovementType;
import com.genesis.domain.product.Product;
import com.genesis.domain.repository.InventoryMovementRepository;
import com.genesis.domain.repository.InventoryRepository;
import com.genesis.domain.repository.ProductRepository;
import com.genesis.domain.shared.valueobject.Quantity;
import org.springframework.stereotype.Service;

@Service
public class RegisterInventoryMovementUseCase {

    private final InventoryRepository inventoryRepository;
    private final InventoryMovementRepository inventoryMovementRepository;
    private final ProductRepository productRepository;

    public RegisterInventoryMovementUseCase(
        InventoryRepository inventoryRepository,
        InventoryMovementRepository inventoryMovementRepository,
        ProductRepository productRepository) {

        this.inventoryRepository = inventoryRepository;
        this.inventoryMovementRepository = inventoryMovementRepository;
        this.productRepository = productRepository;
    }

    public Inventory execute(RegisterInventoryMovementRequest request) {

        Product product = productRepository
            .findById(request.getProductId())
            .orElseThrow(() ->
                new ProductNotFoundException(request.getProductId()));

        Inventory inventory = inventoryRepository
            .findByProductId(product.getId())
            .orElse(null);

        if (inventory == null) {

            if (request.getMovementType() == InventoryMovementType.EXIT) {
                throw new IllegalArgumentException(
                    "Inventory not found for product."
                );
            }

            inventory = new Inventory(
                product,
                Quantity.ZERO
            );
        }

        Quantity quantity = new Quantity(request.getQuantity());

        if (request.getMovementType() == InventoryMovementType.ENTRY) {

            inventory.increase(quantity);

        } else {

            inventory.decrease(quantity);
        }

        inventory = inventoryRepository.save(inventory);

        InventoryMovement movement = new InventoryMovement(
            inventory,
            request.getMovementType(),
            request.getMovementReason(),
            quantity,
            request.getNotes()
        );

        inventoryMovementRepository.save(movement);

        return inventory;
    }
}
