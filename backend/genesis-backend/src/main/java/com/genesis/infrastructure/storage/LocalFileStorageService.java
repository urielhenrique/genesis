/**
 * ============================================================================
 * CLASSE: LocalFileStorageService
 * ============================================================================
 *
 * CAMADA:
 * Infrastructure -> Storage
 *
 * RESPONSABILIDADE:
 *
 * Armazenar e remover arquivos fisicamente no servidor.
 *
 * Esta implementação é utilizada inicialmente no ambiente local.
 *
 * O restante da aplicação conhece apenas a interface
 * FileStorageService.
 *
 * ============================================================================
 */
package com.genesis.infrastructure.storage;

import com.genesis.application.storage.FileStorageService;
import com.genesis.domain.exception.ReceiptFileNotFoundException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

/**
 * Implementação local do armazenamento de arquivos.
 */
@Service
public class LocalFileStorageService implements FileStorageService {

    /*
     * ============================================================================
     * ATRIBUTOS
     * ============================================================================
     */

    /*
     * Diretório onde os comprovantes serão armazenados.
     */
    private final Path receiptsPath;

    /*
     * ============================================================================
     * CONSTRUTOR
     * ============================================================================
     */
    public LocalFileStorageService(
        @Value("${app.storage.receipts-path}") String receiptsPath) {

        this.receiptsPath = Paths.get(receiptsPath)
            .toAbsolutePath()
            .normalize();

        try {
            Files.createDirectories(this.receiptsPath);
        } catch (IOException exception) {
            throw new IllegalStateException(
                "Could not initialize receipt storage.",
                exception
            );
        }
    }

    /*
     * ============================================================================
     * MÉTODO: store()
     * ============================================================================
     *
     * Salva o arquivo e retorna a referência utilizada
     * pelo Receipt.
     */
    @Override
    public String store(
        MultipartFile file) throws IOException {

        /*
         * Gera um nome único para evitar colisões.
         */
        String originalFileName = file.getOriginalFilename();

        String extension = "";

        if (originalFileName != null) {

            int lastDot = originalFileName.lastIndexOf('.');

            if (lastDot >= 0) {
                extension =
                    originalFileName.substring(lastDot);
            }
        }

        String storedFileName =
            UUID.randomUUID() + extension;

        /*
         * Resolve o caminho final do arquivo.
         */
        Path targetPath =
            receiptsPath
                .resolve(storedFileName)
                .normalize();

        /*
         * Garante que o arquivo não escape
         * do diretório configurado.
         */
        if (!targetPath.startsWith(receiptsPath)) {

            throw new IOException(
                "Invalid file path."
            );
        }

        /*
         * Salva o arquivo.
         */
        Files.copy(
            file.getInputStream(),
            targetPath
        );

        /*
         * Retorna o nome utilizado para armazenamento.
         *
         * Posteriormente podemos transformar isso
         * em uma URL pública ou privada.
         */
        return storedFileName;
    }

    /*
     * ============================================================================
     * MÉTODO: delete()
     * ============================================================================
     *
     * Remove um arquivo armazenado.
     */
    @Override
    public void delete(
        String fileUrl) throws IOException {

        if (fileUrl == null || fileUrl.isBlank()) {
            return;
        }

        Path filePath =
            receiptsPath
                .resolve(fileUrl)
                .normalize();

        /*
         * Impede tentativa de acessar arquivos fora
         * do diretório de comprovantes.
         */
        if (!filePath.startsWith(receiptsPath)) {

            throw new IOException(
                "Invalid file path."
            );
        }

        Files.deleteIfExists(filePath);
    }

    /*
     * ============================================================================
     * MÉTODO: load()
     * ============================================================================
     *
     * Carrega o conteúdo de um arquivo armazenado.
     *
     * O parâmetro fileUrl corresponde ao valor salvo no Receipt.fileUrl.
     */
    @Override
    public byte[] load(
        String fileUrl) throws IOException {

        if (fileUrl == null || fileUrl.isBlank()) {

            throw new IOException(
                "File reference is required."
            );
        }

        /*
         * Resolve o arquivo dentro do diretório
         * de comprovantes.
         */
        Path filePath =
            receiptsPath
                .resolve(fileUrl)
                .normalize();

        /*
         * Proteção contra Path Traversal.
         *
         * Impede que uma referência como:
         *
         * ../../arquivo.txt
         *
         * consiga acessar arquivos fora de uploads/receipts.
         */
        if (!filePath.startsWith(receiptsPath)) {

            throw new IOException(
                "Invalid file path."
            );
        }

        /*
         * Verifica se o arquivo realmente existe.
         */
        if (!Files.exists(filePath)) {

            throw new ReceiptFileNotFoundException(
                fileUrl
            );
        }

        /*
         * Retorna o conteúdo do arquivo.
         */
        return Files.readAllBytes(filePath);
    }
}
