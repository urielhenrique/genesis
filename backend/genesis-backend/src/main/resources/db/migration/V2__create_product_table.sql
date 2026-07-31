CREATE TABLE product (

                         id UUID PRIMARY KEY,

                         name VARCHAR(120) NOT NULL,

                         description VARCHAR(500),

                         unit_price NUMERIC(15,2) NOT NULL,

                         type VARCHAR(50) NOT NULL,

                         active BOOLEAN NOT NULL,

                         created_at TIMESTAMP NOT NULL,

                         updated_at TIMESTAMP NOT NULL

);
