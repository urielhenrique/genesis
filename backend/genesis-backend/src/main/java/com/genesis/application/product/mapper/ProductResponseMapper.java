/**
 * ============================================================================
 * CLASSE: ProductResponseMapper
 * ============================================================================
 *
 * CAMADA:
 * Application -> Product -> Mapper
 *
 * RESPONSABILIDADE:
 *
 * Converter entidades do Domínio (Product)
 * em DTOs de resposta (ProductResponse).
 *
 * O Mapper impede que a API exponha diretamente
 * as entidades de domínio, mantendo a separação
 * entre as camadas da aplicação.
 *
 * ============================================================================
 *
 * FLUXO:
 *
 * Product
 *      ↓
 * ProductResponseMapper
 *      ↓
 * ProductResponse
 *      ↓
 * JSON
 *
 * ============================================================================
 */
package com.genesis.application.product.mapper;

/*
 * ============================================================================
 * IMPORTS
 * ============================================================================
 */

/*
 * DTO retornado pela API.
 */
import com.genesis.application.product.dto.ProductResponse;

/*
 * Entidade de domínio.
 */
import com.genesis.domain.product.Product;

/*
 * Registra esta classe como um componente
 * gerenciado pelo Spring Boot.
 */
import org.springframework.stereotype.Component;

/*
 * Utilizado para retornar listas de DTOs.
 */
import java.util.List;

/**
 * Mapper responsável pela conversão entre
 * Product e ProductResponse.
 */
@Component
public class ProductResponseMapper {

    /*
     * ============================================================================
     * MÉTODO: toResponse()
     * ============================================================================
     *
     * Responsabilidade:
     *
     * Converter um Product em ProductResponse.
     *
     * Esta conversão acontece antes do retorno
     * da resposta da API.
     */
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

    /*
     * ============================================================================
     * MÉTODO: toResponseList()
     * ============================================================================
     *
     * Responsabilidade:
     *
     * Converter uma lista de Product em
     * uma lista de ProductResponse.
     *
     * Para evitar duplicação de código,
     * reutiliza o método toResponse().
     */
    public List<ProductResponse> toResponseList(List<Product> products) {

        return products.stream()
            .map(this::toResponse)
            .toList();
    }

    /*
     * ============================================================================
     * RESUMO
     * ============================================================================
     *
     * Conceitos aprendidos:
     *
     * ✔ Mapper
     * ✔ Conversão Domínio → DTO
     * ✔ Stream API
     * ✔ Method Reference
     * ✔ Reutilização de código
     * ✔ @Component
     *
     * ============================================================================
     */
}
