package com.genesis.infrastructure.web.controller;

import com.genesis.application.product.dto.CreateProductRequest;
import com.genesis.application.product.mapper.ProductResponseMapper;
import com.genesis.application.product.response.ProductResponse;
import com.genesis.application.product.usecase.CreateProductUseCase;
import com.genesis.application.product.usecase.FindProductByIdUseCase;
import com.genesis.application.product.usecase.ListProductsUseCase;
import com.genesis.application.product.usecase.UpdateProductUseCase;
import com.genesis.application.product.dto.UpdateProductRequest;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final CreateProductUseCase createProductUseCase;
    private final ListProductsUseCase listProductsUseCase;
    private final ProductResponseMapper responseMapper;
    private final FindProductByIdUseCase findProductByIdUseCase;
    private final UpdateProductUseCase updateProductUseCase;

    public ProductController(
        CreateProductUseCase createProductUseCase,
        ListProductsUseCase listProductsUseCase,
        FindProductByIdUseCase findProductByIdUseCase,
        UpdateProductUseCase updateProductUseCase,
        ProductResponseMapper responseMapper) {

        this.createProductUseCase = createProductUseCase;
        this.listProductsUseCase = listProductsUseCase;
        this.findProductByIdUseCase = findProductByIdUseCase;
        this.updateProductUseCase = updateProductUseCase;
        this.responseMapper = responseMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse create(@Valid @RequestBody CreateProductRequest request) {

        return responseMapper.toResponse(
            createProductUseCase.execute(request)
        );

    }

    @GetMapping
    public List<ProductResponse> findAll() {

        return responseMapper.toResponseList(
            listProductsUseCase.execute()
        );

    }

    @GetMapping("/{id}")
    public ProductResponse findById(@PathVariable UUID id) {

        return responseMapper.toResponse(
            findProductByIdUseCase.execute(id)
        );

    }

    @PutMapping("/{id}")
    public ProductResponse update(
        @PathVariable UUID id,
        @Valid @RequestBody UpdateProductRequest request) {

        return responseMapper.toResponse(
            updateProductUseCase.execute(id, request)
        );
    }

}
