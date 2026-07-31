package com.genesis.infrastructure.web.controller;

import com.genesis.application.product.dto.CreateProductRequest;
import com.genesis.application.product.usecase.CreateProductUseCase;
import com.genesis.domain.product.Product;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final CreateProductUseCase createProductUseCase;

    public ProductController(CreateProductUseCase createProductUseCase) {
        this.createProductUseCase = createProductUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@RequestBody CreateProductRequest request) {

        return createProductUseCase.execute(request);

    }

}
