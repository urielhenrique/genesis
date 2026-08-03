package com.genesis.application.product.mapper;

import com.genesis.application.product.response.ProductResponse;
import com.genesis.domain.product.Product;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductResponseMapper {

    public ProductResponse toResponse(Product product) {

        return new ProductResponse(
            product.getId(),
            product.getName(),
            product.getDescription(),
            product.getUnitPrice().getValue(),
            product.getType(),
            product.isActive(),
            product.getCreatedAt(),
            product.getUpdatedAt()
        );
    }

    public List<ProductResponse> toResponseList(List<Product> products) {

        return products.stream()
            .map(this::toResponse)
            .toList();
    }
}
