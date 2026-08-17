CREATE TABLE receipt (
                         id UUID PRIMARY KEY,

                         financial_transaction_id UUID NOT NULL,

                         file_name VARCHAR(255) NOT NULL,

                         file_url VARCHAR(1000) NOT NULL,

                         content_type VARCHAR(100) NOT NULL,

                         created_at TIMESTAMP NOT NULL,

                         updated_at TIMESTAMP NOT NULL,

                         CONSTRAINT fk_receipt_financial_transaction
                             FOREIGN KEY (financial_transaction_id)
                                 REFERENCES financial_transaction(id)
);
