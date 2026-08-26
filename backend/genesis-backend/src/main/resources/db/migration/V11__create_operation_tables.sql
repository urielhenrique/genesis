CREATE TABLE operations (

                            id UUID PRIMARY KEY,

                            type VARCHAR(50) NOT NULL,

                            status VARCHAR(20) NOT NULL,

                            operation_date TIMESTAMP NOT NULL,

                            description VARCHAR(500),

                            created_at TIMESTAMP NOT NULL,

                            updated_at TIMESTAMP NOT NULL

);


CREATE TABLE operation_items (

                                 id UUID PRIMARY KEY,

                                 operation_id UUID NOT NULL,

                                 product_id UUID NOT NULL,

                                 quantity NUMERIC(19,2) NOT NULL,

                                 unit_price NUMERIC(19,2) NOT NULL,

                                 created_at TIMESTAMP NOT NULL,

                                 updated_at TIMESTAMP NOT NULL,

                                 CONSTRAINT fk_operation_item_operation
                                     FOREIGN KEY (operation_id)
                                         REFERENCES operations(id),

                                 CONSTRAINT fk_operation_item_product
                                     FOREIGN KEY (product_id)
                                         REFERENCES product(id)

);
