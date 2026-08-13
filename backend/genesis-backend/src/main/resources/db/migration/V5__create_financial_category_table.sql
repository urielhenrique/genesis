-- ============================================================================
-- MIGRATION: V5
-- ============================================================================
-- RESPONSABILIDADE:
-- Criar a tabela de categorias financeiras.
-- ============================================================================

CREATE TABLE financial_category (

    -- Identificador único da categoria.
                                    id UUID PRIMARY KEY,

    -- Nome da categoria.
                                    name VARCHAR(120) NOT NULL,

    -- Indica se a categoria está ativa.
                                    active BOOLEAN NOT NULL,

    -- Data de criação do registro.
                                    created_at TIMESTAMP NOT NULL,

    -- Data da última atualização.
                                    updated_at TIMESTAMP NOT NULL,

    -- Impede duas categorias com o mesmo nome.
                                    CONSTRAINT uk_financial_category_name UNIQUE (name)
);
