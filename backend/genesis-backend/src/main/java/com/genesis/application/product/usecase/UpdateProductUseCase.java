package com.genesis.application.product.usecase;

import com.genesis.application.product.dto.UpdateProductRequest;
import com.genesis.domain.exception.ProductNotFoundException;
import com.genesis.domain.product.Product;
import com.genesis.domain.repository.ProductRepository;
import com.genesis.domain.shared.valueobject.Money;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UpdateProductUseCase {

    private final ProductRepository repository;

    public UpdateProductUseCase(ProductRepository repository) {
        this.repository = repository;
    }

    public Product execute(UUID id, UpdateProductRequest request) {

        Product product = repository
            .findById(id)
            .orElseThrow(() -> new ProductNotFoundException(id));

        product.rename(request.getName());
        product.changeDescription(request.getDescription());
        product.changePrice(new Money(request.getUnitPrice()));
        product.changeType(request.getType());

        return repository.save(product);
    }
}
