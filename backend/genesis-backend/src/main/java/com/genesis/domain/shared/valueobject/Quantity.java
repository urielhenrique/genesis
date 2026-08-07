/**
 * ============================================================================
 * CLASSE: Quantity
 * ============================================================================
 *
 * CAMADA:
 * Domain -> Shared -> Value Object
 *
 * RESPONSABILIDADE:
 * Representar uma quantidade dentro do sistema Genesis.
 *
 * Exemplos:
 *
 * • Quantidade em estoque
 * • Quantidade movimentada
 * • Quantidade comprada
 *
 * Esta classe garante que uma quantidade:
 *
 * • nunca seja nula;
 * • nunca seja negativa;
 * • possa ser somada;
 * • possa ser subtraída;
 *
 * ============================================================================
 *
 * O QUE É UM VALUE OBJECT?
 *
 * Um Value Object representa um VALOR e não uma IDENTIDADE.
 *
 * Exemplo:
 *
 * Quantity(10)
 *
 * e
 *
 * Quantity(10)
 *
 * Representam exatamente o mesmo valor.
 *
 * Diferente de uma Entity, que possui um UUID próprio.
 *
 * ============================================================================
 *
 * POR QUE A CLASSE É FINAL?
 *
 * Porque um Value Object não deve ser herdado.
 *
 * Isso garante que o seu comportamento seja sempre o mesmo.
 * ============================================================================
 */

package com.genesis.domain.shared.valueobject;

/*
 * ============================================================================
 * IMPORTS
 * ============================================================================
 */

/*
 * BigDecimal representa números decimais com precisão.
 *
 * É utilizado no lugar de double porque evita problemas
 * de arredondamento.
 *
 * Exemplo:
 *
 * double:
 *
 * 0.1 + 0.2 = 0.30000000000000004
 *
 * BigDecimal:
 *
 * 0.1 + 0.2 = 0.3
 */
import java.math.BigDecimal;

/*
 * Classe utilitária da própria linguagem Java.
 *
 * Utilizamos para implementar equals() e hashCode().
 */
import java.util.Objects;

public final class Quantity {

    /*
     * ========================================================================
     * CONSTANTES
     * ========================================================================
     */

    /*
     * Representa uma quantidade igual a ZERO.
     *
     * Evita criar diversos objetos:
     *
     * new Quantity(BigDecimal.ZERO)
     *
     * durante toda a aplicação.
     */
    public static final Quantity ZERO =
        new Quantity(BigDecimal.ZERO);

    /*
     * ========================================================================
     * ATRIBUTOS
     * ========================================================================
     */

    /*
     * Valor interno da quantidade.
     *
     * Final significa que nunca poderá ser alterado
     * depois da criação do objeto.
     *
     * Isto torna a classe IMUTÁVEL.
     */
    private final BigDecimal value;

    /*
     * ========================================================================
     * CONSTRUTOR
     * ========================================================================
     *
     * Responsável por criar uma Quantity.
     *
     * Toda a validação obrigatória acontece aqui.
     *
     * Assim garantimos que nunca exista uma Quantity
     * inválida dentro do sistema.
     */
    public Quantity(BigDecimal value) {

        if (value == null) {
            throw new IllegalArgumentException(
                "Quantity cannot be null."
            );
        }

        if (value.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException(
                "Quantity cannot be negative."
            );
        }

        this.value = value;
    }

    /*
     * ========================================================================
     * GETTER
     * ========================================================================
     *
     * Apenas devolve o valor armazenado.
     *
     * Não altera o estado do objeto.
     */
    public BigDecimal getValue() {
        return value;
    }

    /*
     * ========================================================================
     * MÉTODO add()
     * ========================================================================
     *
     * Responsabilidade:
     *
     * Somar duas quantidades.
     *
     * IMPORTANTE:
     *
     * Este método NÃO altera esta Quantity.
     *
     * Ele cria uma NOVA Quantity.
     *
     * Isso é chamado de IMUTABILIDADE.
     */
    public Quantity add(Quantity other) {

        if (other == null) {
            throw new IllegalArgumentException(
                "Quantity cannot be null."
            );
        }

        return new Quantity(
            this.value.add(other.value)
        );
    }

    /*
     * ========================================================================
     * MÉTODO subtract()
     * ========================================================================
     *
     * Responsabilidade:
     *
     * Subtrair uma quantidade.
     *
     * Antes de retornar o resultado,
     * garante que nunca teremos
     * uma quantidade negativa.
     */
    public Quantity subtract(Quantity other) {

        if (other == null) {
            throw new IllegalArgumentException(
                "Quantity cannot be null."
            );
        }

        BigDecimal result =
            this.value.subtract(other.value);

        if (result.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException(
                "Insufficient quantity in stock."
            );
        }

        return new Quantity(result);
    }

    /*
     * Retorna verdadeiro quando
     * a quantidade é igual a zero.
     */
    public boolean isZero() {
        return value.compareTo(BigDecimal.ZERO) == 0;
    }

    /*
     * Retorna verdadeiro quando
     * a quantidade é maior que zero.
     */
    public boolean isPositive() {
        return value.compareTo(BigDecimal.ZERO) > 0;
    }

    /*
     * ========================================================================
     * equals()
     * ========================================================================
     *
     * Dois Value Objects são iguais
     * quando possuem o mesmo valor.
     */
    @Override
    public boolean equals(Object object) {

        if (this == object) {
            return true;
        }

        if (!(object instanceof Quantity quantity)) {
            return false;
        }

        return Objects.equals(
            value,
            quantity.value
        );
    }

    /*
     * Deve sempre acompanhar equals().
     */
    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    /*
     * Utilizado para exibir
     * a quantidade como texto.
     *
     * Exemplo:
     *
     * System.out.println(quantity);
     */
    @Override
    public String toString() {
        return value.toString();
    }

    /*
     * ========================================================================
     * RESUMO DA AULA
     * ========================================================================
     *
     * Nesta classe aprendemos:
     *
     * ✔ Value Object
     * ✔ BigDecimal
     * ✔ final class
     * ✔ Imutabilidade
     * ✔ Encapsulamento
     * ✔ Constantes
     * ✔ equals()
     * ✔ hashCode()
     * ✔ compareTo()
     * ============================================================================
     */
}
