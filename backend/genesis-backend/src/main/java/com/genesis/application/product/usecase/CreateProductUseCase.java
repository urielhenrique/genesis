package com.genesis.application.product.usecase;

import org.springframework.stereotype.Service;
import com.genesis.application.product.dto.CreateProductRequest;
import com.genesis.domain.product.Product;
import com.genesis.domain.repository.ProductRepository;
import com.genesis.domain.shared.valueobject.Money;
import com.genesis.domain.inventory.Inventory;
import com.genesis.domain.repository.InventoryRepository;
import com.genesis.domain.shared.valueobject.Quantity;

import java.math.BigDecimal;

@Service
public class CreateProductUseCase {

    private final ProductRepository productRepository;
    private final InventoryRepository inventoryRepository;

    public CreateProductUseCase(
            ProductRepository productRepository,
            InventoryRepository inventoryRepository) {
        this.productRepository = productRepository;
        this.inventoryRepository = inventoryRepository;
    }

    public Product execute(CreateProductRequest request) {

        Product product = new Product(
            request.getName(),
            request.getDescription(),
            new Money(request.getUnitPrice()),
            request.getType()
        );

        return productRepository.save(product);
    }
}
