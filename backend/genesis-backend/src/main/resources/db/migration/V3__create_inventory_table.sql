CREATE TABLE inventory (

                           id UUID PRIMARY KEY,

                           product_id UUID NOT NULL UNIQUE,

                           quantity NUMERIC(19,2) NOT NULL,

                           created_at TIMESTAMP NOT NULL,

                           updated_at TIMESTAMP NOT NULL,

                           CONSTRAINT fk_inventory_product
                               FOREIGN KEY (product_id)
                                   REFERENCES product(id)

);
