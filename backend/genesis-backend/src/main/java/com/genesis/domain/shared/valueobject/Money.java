/**
 * ============================================================================
 * CLASSE: Money
 * ============================================================================
 *
 * CAMADA:
 * Domain -> Shared -> Value Object
 *
 * RESPONSABILIDADE:
 *
 * Representar um valor monetário dentro do sistema Genesis.
 *
 * Exemplos:
 *
 * • Preço de um produto
 * • Valor de uma venda
 * • Valor de uma compra
 * • Valor de uma despesa
 * • Valor de uma receita
 *
 * ============================================================================
 *
 * POR QUE EXISTE ESTA CLASSE?
 *
 * Poderíamos simplesmente utilizar:
 *
 * BigDecimal
 *
 * Mas isso faria cada classe validar dinheiro
 * de uma maneira diferente.
 *
 * Criando um Value Object, todas as regras
 * ficam centralizadas.
 *
 * ============================================================================
 *
 * O QUE ESTA CLASSE GARANTE?
 *
 * ✔ Nunca será nula.
 *
 * ✔ Nunca será negativa.
 *
 * ✔ Sempre possuirá duas casas decimais.
 *
 * ✔ Toda soma será válida.
 *
 * ✔ Toda subtração será válida.
 *
 * ✔ Toda multiplicação será válida.
 *
 * ============================================================================
 *
 * O QUE É UM VALUE OBJECT?
 *
 * Diferente de uma Entity,
 * Money NÃO possui identidade.
 *
 * Exemplo:
 *
 * Money(100.00)
 *
 * e
 *
 * Money(100.00)
 *
 * representam exatamente o mesmo valor.
 *
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
 * Nunca utilizamos double para dinheiro.
 *
 * Exemplo:
 *
 * double:
 *
 * 0.1 + 0.2
 *
 * resultado:
 *
 * 0.30000000000000004
 *
 * BigDecimal:
 *
 * 0.30
 */
import java.math.BigDecimal;

/*
 * Define como arredondamentos serão realizados.
 *
 * HALF_UP é o arredondamento financeiro
 * mais utilizado.
 *
 * Exemplos:
 *
 * 10.124 -> 10.12
 *
 * 10.125 -> 10.13
 */
import java.math.RoundingMode;

/*
 * Classe utilitária utilizada para implementar
 * equals() e hashCode().
 */
import java.util.Objects;

public final class Money {

    /*
     * ========================================================================
     * ATRIBUTOS
     * ========================================================================
     */

    /*
     * Valor monetário.
     *
     * final significa que nunca poderá ser alterado.
     *
     * Isso torna a classe imutável.
     */
    private final BigDecimal value;

    /*
     * ========================================================================
     * CONSTRUTOR
     * ========================================================================
     *
     * Toda validação acontece aqui.
     *
     * Nunca existirá um Money inválido.
     */
    public Money(BigDecimal value) {

        /*
         * Dinheiro nunca pode ser nulo.
         */
        if (value == null) {
            throw new IllegalArgumentException(
                "Money cannot be null."
            );
        }

        /*
         * O Genesis não trabalha com dinheiro negativo.
         *
         * Caso seja necessário futuramente,
         * esta regra poderá ser revisada.
         */
        if (value.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException(
                "Money cannot be negative."
            );
        }

        /*
         * setScale(2)
         *
         * Obriga o valor a possuir
         * exatamente duas casas decimais.
         *
         * Exemplos:
         *
         * 10 -> 10.00
         *
         * 10.5 -> 10.50
         *
         * 10.567 -> 10.57
         */
        this.value =
            value.setScale(
                2,
                RoundingMode.HALF_UP
            );
    }

    /*
     * ========================================================================
     * GETTER
     * ========================================================================
     */

    public BigDecimal getValue() {
        return value;
    }

    /*
     * ========================================================================
     * MÉTODO add()
     * ========================================================================
     *
     * Soma dois valores monetários.
     *
     * IMPORTANTE:
     *
     * Este método NÃO altera este objeto.
     *
     * Ele cria um novo Money.
     */
    public Money add(Money other) {

        if (other == null) {
            throw new IllegalArgumentException(
                "Money cannot be null."
            );
        }

        return new Money(
            this.value.add(other.value)
        );
    }

    /*
     * ========================================================================
     * MÉTODO subtract()
     * ========================================================================
     *
     * Subtrai um valor monetário.
     *
     * Também retorna um novo objeto.
     */
    public Money subtract(Money other) {

        if (other == null) {
            throw new IllegalArgumentException(
                "Money cannot be null."
            );
        }

        return new Money(
            this.value.subtract(other.value)
        );
    }

    /*
     * ========================================================================
     * MÉTODO multiply()
     * ========================================================================
     *
     * Multiplica um valor monetário.
     *
     * Exemplo:
     *
     * Produto
     *
     * R$ 10,00
     *
     * Quantidade:
     *
     * 5
     *
     * Resultado:
     *
     * R$ 50,00
     */
    public Money multiply(BigDecimal quantity) {

        if (quantity == null) {
            throw new IllegalArgumentException(
                "Quantity cannot be null."
            );
        }

        return new Money(
            this.value.multiply(quantity)
        );
    }

    /*
     * ========================================================================
     * equals()
     * ========================================================================
     *
     * Dois objetos Money são iguais
     * quando possuem exatamente
     * o mesmo valor.
     */
    @Override
    public boolean equals(Object object) {

        if (this == object) {
            return true;
        }

        if (!(object instanceof Money money)) {
            return false;
        }

        return Objects.equals(
            value,
            money.value
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
     * Utilizado quando o objeto
     * precisa ser convertido para texto.
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
     * ✔ RoundingMode
     * ✔ setScale()
     * ✔ Imutabilidade
     * ✔ Encapsulamento
     * ✔ equals()
     * ✔ hashCode()
     * ✔ Operações monetárias
     * ============================================================================
     */
}
