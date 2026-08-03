package com.genesis.application.product.usecase;

import com.genesis.domain.exception.ProductNotFoundException;
import com.genesis.domain.product.Product;
import com.genesis.domain.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FindProductByIdUseCase {

    private final ProductRepository repository;

    public FindProductByIdUseCase(ProductRepository repository) {
        this.repository = repository;
    }

    public Product execute(UUID id) {

        return repository
            .findById(id)
            .orElseThrow(() -> new ProductNotFoundException(id));

    }

}
