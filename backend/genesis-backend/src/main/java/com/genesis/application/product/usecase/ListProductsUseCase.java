package com.genesis.application.product.usecase;

import com.genesis.domain.product.Product;
import com.genesis.domain.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListProductsUseCase {

    private final ProductRepository repository;

    public ListProductsUseCase(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> execute() {
        return repository.findAll();
    }

}
