package com.genesis.application.product.usecase;

import com.genesis.application.product.dto.CreateProductRequest;
import com.genesis.domain.product.Product;
import com.genesis.domain.repository.ProductRepository;
import com.genesis.domain.shared.valueobject.Money;

public class CreateProductUseCase {

    private final ProductRepository productRepository;

    public CreateProductUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
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
