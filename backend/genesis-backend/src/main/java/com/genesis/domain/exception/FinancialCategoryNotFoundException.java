/**
 * ============================================================================
 * CLASSE: FinancialCategoryNotFoundException
 * ============================================================================
 *
 * CAMADA:
 * Domain -> Exception
 *
 * RESPONSABILIDADE:
 *
 * Representar o erro que ocorre quando uma categoria
 * financeira não é encontrada pelo seu identificador.
 *
 * ============================================================================
 */
package com.genesis.domain.exception;

/*
 * Identificador único da categoria.
 */
import java.util.UUID;

/**
 * Exceção lançada quando uma categoria financeira
 * não existe.
 */
public class FinancialCategoryNotFoundException
    extends RuntimeException {

    /*
     * ============================================================================
     * CONSTRUTOR
     * ============================================================================
     *
     * Recebe o ID da categoria que não foi encontrada.
     */
    public FinancialCategoryNotFoundException(UUID id) {

        /*
         * Mensagem utilizada para identificar
         * qual categoria não foi encontrada.
         */
        super("Financial category not found: " + id);
    }
}
