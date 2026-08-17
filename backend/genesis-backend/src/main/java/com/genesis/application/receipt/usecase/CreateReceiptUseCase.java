/**
 * ============================================================================
 * CLASSE: CreateReceiptUseCase
 * ============================================================================
 *
 * CAMADA:
 * Application -> Receipt -> UseCase
 *
 * RESPONSABILIDADE:
 *
 * Criar um comprovante associado a uma movimentação financeira existente.
 *
 * Também é responsável por validar o arquivo e solicitar seu armazenamento
 * através do FileStorageService.
 *
 * ============================================================================
 */
package com.genesis.application.receipt.usecase;

import com.genesis.application.receipt.dto.CreateReceiptRequest;
import com.genesis.application.storage.FileStorageService;
import com.genesis.domain.exception.FinancialTransactionNotFoundException;
import com.genesis.domain.financial.FinancialTransaction;
import com.genesis.domain.receipt.Receipt;
import com.genesis.domain.repository.FinancialTransactionRepository;
import com.genesis.domain.repository.ReceiptRepository;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Use Case responsável pela criação de comprovantes.
 */
@Service
public class CreateReceiptUseCase {

    /*
     * ============================================================================
     * CONSTANTES
     * ============================================================================
     */

    /*
     * Tamanho máximo permitido:
     *
     * 10 MB.
     */
    private static final long MAX_FILE_SIZE =
        10 * 1024 * 1024;

    /*
     * Tipos de arquivo permitidos.
     */
    private static final String JPEG = "image/jpeg";

    private static final String PNG = "image/png";

    private static final String PDF = "application/pdf";

    /*
     * ============================================================================
     * ATRIBUTOS
     * ============================================================================
     */

    /*
     * Repository das movimentações financeiras.
     */
    private final FinancialTransactionRepository financialTransactionRepository;

    /*
     * Repository dos comprovantes.
     */
    private final ReceiptRepository receiptRepository;

    /*
     * Serviço responsável pelo armazenamento físico.
     */
    private final FileStorageService fileStorageService;

    /*
     * ============================================================================
     * CONSTRUTOR
     * ============================================================================
     */
    public CreateReceiptUseCase(
        FinancialTransactionRepository financialTransactionRepository,
        ReceiptRepository receiptRepository,
        FileStorageService fileStorageService) {

        this.financialTransactionRepository =
            financialTransactionRepository;

        this.receiptRepository =
            receiptRepository;

        this.fileStorageService =
            fileStorageService;
    }

    /*
     * ============================================================================
     * MÉTODO: execute()
     * ============================================================================
     */
    public Receipt execute(
        CreateReceiptRequest request) {

        /*
         * Obtém o arquivo recebido.
         */
        MultipartFile file =
            request.getFile();

        /*
         * Valida o arquivo.
         */
        validateFile(file);

        /*
         * Verifica se a movimentação financeira existe.
         */
        FinancialTransaction transaction =
            financialTransactionRepository
                .findById(request.getFinancialTransactionId())
                .orElseThrow(() ->
                    new FinancialTransactionNotFoundException(
                        request.getFinancialTransactionId()
                    )
                );

        String storedFileName = null;

        try {

            /*
             * ====================================================================
             * 1. ARMAZENAMENTO FÍSICO
             * ====================================================================
             *
             * O FileStorageService decide onde o arquivo será armazenado.
             */
            storedFileName =
                fileStorageService.store(file);

            /*
             * ====================================================================
             * 2. CRIAÇÃO DO RECEIPT
             * ====================================================================
             *
             * Os dados do arquivo são obtidos do MultipartFile.
             */
            Receipt receipt = new Receipt(
                transaction.getId(),
                file.getOriginalFilename(),
                storedFileName,
                file.getContentType()
            );

            /*
             * ====================================================================
             * 3. PERSISTÊNCIA
             * ====================================================================
             */
            return receiptRepository.save(receipt);

        } catch (IOException exception) {

            /*
             * Erro durante armazenamento.
             */
            throw new IllegalStateException(
                "Could not store receipt file.",
                exception
            );

        } catch (RuntimeException exception) {

            /*
             * Se o arquivo já foi armazenado, tenta removê-lo
             * caso a persistência do Receipt tenha falhado.
             */
            if (storedFileName != null) {

                try {

                    fileStorageService.delete(
                        storedFileName
                    );

                } catch (IOException cleanupException) {

                    /*
                     * Mantemos a exceção original como
                     * principal. O cleanup é secundário.
                     */
                    exception.addSuppressed(
                        cleanupException
                    );
                }
            }

            throw exception;
        }
    }

    /*
     * ============================================================================
     * MÉTODO: validateFile()
     * ============================================================================
     *
     * Valida:
     *
     * • arquivo existente
     * • tamanho
     * • content-type
     */
    private void validateFile(
        MultipartFile file) {

        /*
         * Verifica se o arquivo existe.
         */
        if (file == null || file.isEmpty()) {

            throw new IllegalArgumentException(
                "Receipt file is required."
            );
        }

        /*
         * Verifica o tamanho.
         */
        if (file.getSize() > MAX_FILE_SIZE) {

            throw new IllegalArgumentException(
                "Receipt file must be smaller than or equal to 10 MB."
            );
        }

        /*
         * Obtém o Content-Type informado pelo cliente.
         */
        String contentType =
            file.getContentType();

        /*
         * Verifica se o tipo é permitido.
         */
        if (!isAllowedContentType(contentType)) {

            throw new IllegalArgumentException(
                "Only JPG, PNG and PDF files are allowed."
            );
        }
    }

    /*
     * ============================================================================
     * MÉTODO: isAllowedContentType()
     * ============================================================================
     */
    private boolean isAllowedContentType(
        String contentType) {

        return JPEG.equalsIgnoreCase(contentType)
            || PNG.equalsIgnoreCase(contentType)
            || PDF.equalsIgnoreCase(contentType);
    }
}
