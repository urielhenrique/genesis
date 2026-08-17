/**
 * ============================================================================
 * CLASSE: FindReceiptByIdUseCase
 * ============================================================================
 *
 * CAMADA:
 * Application -> Receipt -> UseCase
 *
 * RESPONSABILIDADE:
 *
 * Buscar um comprovante pelo seu identificador.
 *
 * ============================================================================
 */
package com.genesis.application.receipt.usecase;

import com.genesis.domain.exception.ReceiptNotFoundException;
import com.genesis.domain.receipt.Receipt;
import com.genesis.domain.repository.ReceiptRepository;

import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Use Case responsável pela busca de comprovantes.
 */
@Service
public class FindReceiptByIdUseCase {

    /*
     * Repository utilizado para localizar o comprovante.
     */
    private final ReceiptRepository receiptRepository;

    /*
     * ============================================================================
     * CONSTRUTOR
     * ============================================================================
     */
    public FindReceiptByIdUseCase(
        ReceiptRepository receiptRepository) {

        this.receiptRepository = receiptRepository;
    }

    /*
     * ============================================================================
     * MÉTODO: execute()
     * ============================================================================
     *
     * Busca o comprovante pelo ID.
     *
     * Caso não exista, lança ReceiptNotFoundException.
     */
    public Receipt execute(UUID id) {

        return receiptRepository
            .findById(id)
            .orElseThrow(() ->
                new ReceiptNotFoundException(id)
            );
    }
}
