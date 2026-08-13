-- ============================================================================
-- MIGRATION: V6
-- ============================================================================
-- RESPONSABILIDADE:
-- Criar a tabela de movimentações financeiras.
-- ============================================================================

CREATE TABLE financial_transaction (

    -- Identificador único da movimentação.
                                       id UUID PRIMARY KEY,

    -- Descrição da movimentação.
                                       description VARCHAR(255) NOT NULL,

    -- Valor da movimentação.
                                       amount NUMERIC(19, 2) NOT NULL,

    -- Tipo: INCOME ou EXPENSE.
                                       type VARCHAR(20) NOT NULL,

    -- Categoria da movimentação.
                                       category_id UUID NOT NULL,

    -- Forma de pagamento ou recebimento.
                                       payment_method VARCHAR(30) NOT NULL,

    -- Data em que a movimentação ocorreu.
                                       transaction_date TIMESTAMP NOT NULL,

    -- Observações adicionais.
                                       notes VARCHAR(500),

    -- Data de criação do registro.
                                       created_at TIMESTAMP NOT NULL,

    -- Data da última atualização.
                                       updated_at TIMESTAMP NOT NULL,

    -- Relacionamento com a categoria financeira.
                                       CONSTRAINT fk_financial_transaction_category
                                           FOREIGN KEY (category_id)
                                               REFERENCES financial_category (id)
);
