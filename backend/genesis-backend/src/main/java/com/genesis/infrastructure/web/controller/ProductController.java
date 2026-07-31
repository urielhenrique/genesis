package com.genesis.infrastructure.web.controller;

import com.genesis.application.product.dto.CreateProductRequest;
import com.genesis.application.product.usecase.CreateProductUseCase;
import com.genesis.application.product.usecase.ListProductsUseCase;
import com.genesis.domain.product.Product;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final CreateProductUseCase createProductUseCase;
    private final ListProductsUseCase listProductsUseCase;

    public ProductController(
        CreateProductUseCase createProductUseCase,
        ListProductsUseCase listProductsUseCase) {

        this.createProductUseCase = createProductUseCase;
        this.listProductsUseCase = listProductsUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@Valid @RequestBody CreateProductRequest request) {

        return createProductUseCase.execute(request);

    }

    @GetMapping
    public List<Product> findAll() {

        return listProductsUseCase.execute();

    }

}
