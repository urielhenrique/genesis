/**
 * ============================================================================
 * CLASSE: GlobalExceptionHandler
 * ============================================================================
 *
 * CAMADA:
 * Infrastructure -> Web -> Advice
 *
 * RESPONSABILIDADE:
 *
 * Centralizar o tratamento das exceções lançadas pela aplicação
 * e convertê-las em respostas HTTP apropriadas.
 *
 * ============================================================================
 */
package com.genesis.infrastructure.web.advice;

/*
 * ============================================================================
 * IMPORTS
 * ============================================================================
 */

/*
 * Exceção lançada quando um produto não é encontrado.
 */
import com.genesis.domain.exception.ProductNotFoundException;

/*
 * Exceção lançada quando uma categoria financeira
 * não é encontrada.
 */
import com.genesis.domain.exception.FinancialCategoryNotFoundException;

/*
 * Exceção lançada quando uma movimentação financeira
 * não é encontrada.
 */
import com.genesis.domain.exception.FinancialTransactionNotFoundException;

import com.genesis.domain.exception.EventNotFoundException;
import com.genesis.domain.exception.ReceiptFileNotFoundException;

/*
 * Status HTTP.
 */
import org.springframework.http.HttpStatus;

/*
 * Representa uma resposta HTTP.
 */
import org.springframework.http.ResponseEntity;

/*
 * Exceção lançada quando a validação de um DTO falha.
 */
import org.springframework.web.bind.MethodArgumentNotValidException;

/*
 * Permite criar handlers para exceções específicas.
 */
import org.springframework.web.bind.annotation.ExceptionHandler;

/*
 * Indica que esta classe trata exceções globalmente
 * para os Controllers REST.
 */
import org.springframework.web.bind.annotation.RestControllerAdvice;

/*
 * Data e hora utilizadas na resposta de validação.
 */
import java.time.LocalDateTime;

/*
 * Lista utilizada para armazenar as mensagens
 * de validação.
 */
import java.util.List;

/**
 * Handler global de exceções da API.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /*
     * ============================================================================
     * MÉTODO: handleValidation()
     * ============================================================================
     *
     * Responsabilidade:
     *
     * Tratar erros de validação dos DTOs.
     *
     * Exemplo:
     *
     * @NotNull
     * @NotBlank
     * @Positive
     * @Size
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleValidation(
        MethodArgumentNotValidException exception) {

        /*
         * Obtém todas as mensagens de erro dos campos
         * que falharam na validação.
         */
        List<String> messages = exception
            .getBindingResult()
            .getFieldErrors()
            .stream()
            .map(error -> error.getDefaultMessage())
            .toList();

        /*
         * Cria a resposta padronizada de validação.
         */
        ValidationErrorResponse response =
            new ValidationErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Validation Error",
                messages
            );

        /*
         * Retorna HTTP 400 Bad Request.
         */
        return ResponseEntity
            .badRequest()
            .body(response);
    }

    /*
     * ============================================================================
     * MÉTODO: handleProductNotFound()
     * ============================================================================
     *
     * Responsabilidade:
     *
     * Converter ProductNotFoundException
     * para HTTP 404 Not Found.
     */
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> handleProductNotFound(
        ProductNotFoundException exception) {

        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(exception.getMessage());
    }

    /*
     * ============================================================================
     * MÉTODO: handleFinancialCategoryNotFound()
     * ============================================================================
     *
     * Responsabilidade:
     *
     * Converter FinancialCategoryNotFoundException
     * para HTTP 404 Not Found.
     */
    @ExceptionHandler(FinancialCategoryNotFoundException.class)
    public ResponseEntity<String> handleFinancialCategoryNotFound(
        FinancialCategoryNotFoundException exception) {

        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(exception.getMessage());
    }

    /*
     * ============================================================================
     * MÉTODO: handleFinancialTransactionNotFound()
     * ============================================================================
     *
     * Responsabilidade:
     *
     * Converter FinancialTransactionNotFoundException
     * para HTTP 404 Not Found.
     */
    @ExceptionHandler(FinancialTransactionNotFoundException.class)
    public ResponseEntity<String> handleFinancialTransactionNotFound(
        FinancialTransactionNotFoundException exception) {

        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(exception.getMessage());
    }

    /*
     * ============================================================================
     * MÉTODO: handleEventNotFound()
     * ============================================================================
     *
     * Responsabilidade:
     *
     * Converter EventNotFoundException
     * para HTTP 404 Not Found.
     */
    @ExceptionHandler(EventNotFoundException.class)
    public ResponseEntity<String> handleEventNotFound(
        EventNotFoundException exception) {

        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(exception.getMessage());
    }

    /*
     * ============================================================================
     * MÉTODO: handleIllegalArgument()
     * ============================================================================
     *
     * Responsabilidade:
     *
     * Converter erros de entrada/regra de validação para
     * HTTP 400 Bad Request.
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(
        IllegalArgumentException exception) {

        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(exception.getMessage());
    }

    @ExceptionHandler(ReceiptFileNotFoundException.class)
    public ResponseEntity<String> handleReceiptFileNotFound(
        ReceiptFileNotFoundException exception) {

        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(exception.getMessage());
    }

}
