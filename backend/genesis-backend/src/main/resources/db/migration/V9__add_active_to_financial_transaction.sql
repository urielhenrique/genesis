-- ============================================================================
-- MIGRATION: V7
-- ============================================================================
-- RESPONSABILIDADE:
-- Adicionar controle de exclusão lógica às movimentações financeiras.
-- ============================================================================

ALTER TABLE financial_transaction
    ADD COLUMN active BOOLEAN NOT NULL DEFAULT TRUE;
