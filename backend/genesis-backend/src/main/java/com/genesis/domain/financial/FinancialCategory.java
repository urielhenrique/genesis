/**
 * ============================================================================
 * CLASSE: FinancialCategory
 * ============================================================================
 *
 * CAMADA:
 * Domain -> Financial
 *
 * RESPONSABILIDADE:
 *
 * Representar uma categoria utilizada para classificar
 * movimentações financeiras.
 *
 * Exemplos:
 *
 * • Alimentação
 * • Doações
 * • Material de limpeza
 * • Energia elétrica
 * • Manutenção
 *
 * A categoria é uma entidade própria porque pode ser
 * cadastrada, alterada, ativada e desativada.
 *
 * ============================================================================
 */
package com.genesis.domain.financial;

/*
 * ============================================================================
 * IMPORTS
 * ============================================================================
 */

/*
 * Entidade base do domínio.
 *
 * Fornece:
 * • id
 * • createdAt
 * • updatedAt
 * • touch()
 */
import com.genesis.domain.shared.entity.BaseEntity;

/**
 * Entidade que representa uma categoria financeira.
 */
public class FinancialCategory extends BaseEntity {

    /*
     * ============================================================================
     * ATRIBUTOS
     * ============================================================================
     */

    /*
     * Nome da categoria.
     */
    private String name;

    /*
     * Indica se a categoria está disponível
     * para utilização.
     */
    private boolean active;

    /*
     * ============================================================================
     * CONSTRUTOR
     * ============================================================================
     *
     * Cria uma nova categoria financeira.
     */
    public FinancialCategory(String name) {

        /*
         * O nome é obrigatório.
         */
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(
                "Category name is required."
            );
        }

        /*
         * Remove espaços desnecessários
         * no início e no final.
         */
        this.name = name.trim();

        /*
         * Toda categoria começa ativa.
         */
        this.active = true;
    }

    /*
     * ============================================================================
     * CONSTRUTOR DE PERSISTÊNCIA
     * ============================================================================
     *
     * Reconstrói uma categoria existente a partir
     * dos dados recuperados do banco.
     */
    public FinancialCategory(
        java.util.UUID id,
        java.time.LocalDateTime createdAt,
        java.time.LocalDateTime updatedAt,
        String name,
        boolean active) {

        super(id, createdAt, updatedAt);

        /*
         * Valida o nome mesmo durante a reconstrução.
         */
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(
                "Category name is required."
            );
        }

        this.name = name.trim();
        this.active = active;
    }

    /*
     * ============================================================================
     * GETTERS
     * ============================================================================
     */

    public String getName() {
        return name;
    }

    public boolean isActive() {
        return active;
    }

    /*
     * ============================================================================
     * MÉTODO: rename()
     * ============================================================================
     *
     * Altera o nome da categoria.
     */
    public void rename(String newName) {

        if (newName == null || newName.isBlank()) {
            throw new IllegalArgumentException(
                "Category name is required."
            );
        }

        this.name = newName.trim();

        /*
         * Atualiza a data de alteração.
         */
        touch();
    }

    /*
     * ============================================================================
     * MÉTODO: activate()
     * ============================================================================
     *
     * Ativa a categoria.
     */
    public void activate() {

        this.active = true;

        /*
         * Atualiza a data de alteração.
         */
        touch();
    }

    /*
     * ============================================================================
     * MÉTODO: deactivate()
     * ============================================================================
     *
     * Desativa a categoria.
     */
    public void deactivate() {

        this.active = false;

        /*
         * Atualiza a data de alteração.
         */
        touch();
    }

}
