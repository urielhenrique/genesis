package com.genesis.application.operation.usecase;

import com.genesis.domain.operation.Operation;
import com.genesis.domain.operation.OperationItem;
import com.genesis.domain.repository.OperationRepository;
import com.genesis.domain.repository.ProductRepository;
import com.genesis.domain.product.Product;
import com.genesis.domain.shared.enums.OperationType;
import com.genesis.domain.shared.valueobject.Money;
import com.genesis.domain.shared.valueobject.Quantity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class CreateOperationUseCase {

    private final OperationRepository operationRepository;
    private final ProductRepository productRepository;

    public CreateOperationUseCase(
        OperationRepository operationRepository,
        ProductRepository productRepository) {

        this.operationRepository = operationRepository;
        this.productRepository = productRepository;
    }

    public Operation execute(
        OperationType type,
        LocalDateTime operationDate,
        String description,
        List<ItemInput> items) {

        Operation operation =
            new Operation(
                type,
                operationDate,
                description
            );

        for (ItemInput input : items) {

            Product product = productRepository
                .findById(input.productId())
                .orElseThrow(() ->
                    new IllegalArgumentException(
                        "Product not found: " + input.productId()
                    )
                );

            OperationItem item =
                new OperationItem(
                    product,
                    new Quantity(input.quantity()),
                    new Money(input.unitPrice())
                );

            operation.addItem(item);
        }

        return operationRepository.save(operation);
    }

    public record ItemInput(
        UUID productId,
        BigDecimal quantity,
        BigDecimal unitPrice
    ) {
    }
}
