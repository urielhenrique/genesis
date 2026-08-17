/**
 * ============================================================================
 * CLASSE: ReceiptController
 * ============================================================================
 *
 * CAMADA:
 * Infrastructure -> Web -> Controller
 *
 * RESPONSABILIDADE:
 *
 * Expor a API REST de comprovantes.
 *
 * ============================================================================
 *
 * ENDPOINTS:
 *
 * POST /api/receipts
 * GET  /api/receipts/{id}
 * GET  /api/receipts/financial/transactions/{transactionId}
 *
 * ============================================================================
 */
package com.genesis.infrastructure.web.controller;

import com.genesis.application.receipt.dto.CreateReceiptRequest;
import com.genesis.application.receipt.dto.ReceiptResponse;
import com.genesis.application.receipt.mapper.ReceiptResponseMapper;
import com.genesis.application.receipt.usecase.CreateReceiptUseCase;
import com.genesis.application.receipt.usecase.FindReceiptByIdUseCase;
import com.genesis.application.receipt.usecase.ListReceiptsByFinancialTransactionUseCase;
import com.genesis.application.storage.FileStorageService;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

/**
 * Controller responsável pelos endpoints de comprovantes.
 */
@RestController
@RequestMapping("/api/receipts")
public class ReceiptController {

    /*
     * ============================================================================
     * ATRIBUTOS
     * ============================================================================
     */

    private final CreateReceiptUseCase createUseCase;

    private final FindReceiptByIdUseCase findByIdUseCase;

    private final ListReceiptsByFinancialTransactionUseCase listByTransactionUseCase;

    private final ReceiptResponseMapper responseMapper;

    private final FileStorageService fileStorageService;

    /*
     * ============================================================================
     * CONSTRUTOR
     * ============================================================================
     */

    public ReceiptController(
        CreateReceiptUseCase createUseCase,
        FindReceiptByIdUseCase findByIdUseCase,
        ListReceiptsByFinancialTransactionUseCase listByTransactionUseCase,
        ReceiptResponseMapper responseMapper,
        FileStorageService fileStorageService) {

        this.createUseCase = createUseCase;
        this.findByIdUseCase = findByIdUseCase;
        this.listByTransactionUseCase = listByTransactionUseCase;
        this.responseMapper = responseMapper;
        this.fileStorageService = fileStorageService;
    }

    /*
     * ============================================================================
     * MÉTODO: create()
     * ============================================================================
     *
     * POST /api/receipts
     *
     * Content-Type:
     *
     * multipart/form-data
     *
     * Campos:
     *
     * financialTransactionId → UUID
     * file                   → arquivo
     *
     * ============================================================================
     */
    @PostMapping(consumes = "multipart/form-data")
    @ResponseStatus(HttpStatus.CREATED)
    public ReceiptResponse create(
        @RequestParam UUID financialTransactionId,
        @RequestPart("file") MultipartFile file) {

        /*
         * Monta o DTO utilizado pelo Use Case.
         */
        CreateReceiptRequest request =
            new CreateReceiptRequest(
                financialTransactionId,
                file
            );

        /*
         * Executa o Use Case.
         */
        return responseMapper.toResponse(
            createUseCase.execute(request)
        );
    }

    /*
     * ============================================================================
     * MÉTODO: findById()
     * ============================================================================
     *
     * GET /api/receipts/{id}
     *
     * Busca um comprovante pelo ID.
     */
    @GetMapping("/{id}")
    public ReceiptResponse findById(
        @PathVariable UUID id) {

        return responseMapper.toResponse(
            findByIdUseCase.execute(id)
        );
    }

    /*
     * ============================================================================
     * MÉTODO: findByFinancialTransaction()
     * ============================================================================
     *
     * GET /api/receipts/financial/transactions/{transactionId}
     *
     * Lista os comprovantes associados a uma movimentação financeira.
     */
    @GetMapping("/financial/transactions/{transactionId}")
    public List<ReceiptResponse> findByFinancialTransaction(
        @PathVariable UUID transactionId) {

        return responseMapper.toResponseList(
            listByTransactionUseCase.execute(transactionId)
        );
    }

    /*
     * ============================================================================
     * MÉTODO: downloadFile()
     * ============================================================================
     *
     * GET /api/receipts/{id}/file
     *
     * Retorna o arquivo físico associado ao comprovante.
     *
     * O Content-Type é obtido do Receipt.
     * ============================================================================
     */
    @GetMapping("/{id}/file")
    public ResponseEntity<Resource> downloadFile(
        @PathVariable UUID id) {

        /*
         * Busca o Receipt.
         */
        var receipt =
            findByIdUseCase.execute(id);

        try {

            /*
             * Carrega o arquivo físico.
             */
            byte[] file =
                fileStorageService.load(
                    receipt.getFileUrl()
                );

            /*
             * Transforma o conteúdo em Resource.
             */
            ByteArrayResource resource =
                new ByteArrayResource(file);

            /*
             * Define o Content-Type.
             */
            MediaType mediaType =
                MediaType.parseMediaType(
                    receipt.getContentType()
                );

            /*
             * Retorna o arquivo.
             *
             * inline permite que o navegador tente visualizar
             * PDF/imagens em vez de obrigatoriamente baixar.
             */
            return ResponseEntity
                .ok()
                .contentType(mediaType)
                .contentLength(file.length)
                .header(
                    "Content-Disposition",
                    "inline; filename=\"" +
                        receipt.getFileName() +
                        "\""
                )
                .body(resource);

        } catch (IOException exception) {

            throw new IllegalStateException(
                "Could not load receipt file.",
                exception
            );
        }
    }
}
