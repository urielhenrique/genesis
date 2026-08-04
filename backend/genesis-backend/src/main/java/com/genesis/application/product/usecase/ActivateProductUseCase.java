package com.genesis.application.product.usecase;

import com.genesis.domain.exception.ProductNotFoundException;
import com.genesis.domain.product.Product;
import com.genesis.domain.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ActivateProductUseCase {

    private final ProductRepository repository;

    public ActivateProductUseCase(ProductRepository repository) {
        this.repository = repository;
    }

    public Product execute(UUID id) {

        Product product = repository
            .findById(id)
            .orElseThrow(() -> new ProductNotFoundException(id));

        product.activate();

        return repository.save(product);
    }
}
