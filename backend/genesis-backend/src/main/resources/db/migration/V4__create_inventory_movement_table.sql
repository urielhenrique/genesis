CREATE TABLE inventory_movement (

                                    id UUID PRIMARY KEY,

                                    inventory_id UUID NOT NULL,

                                    movement_type VARCHAR(20) NOT NULL,

                                    movement_reason VARCHAR(50) NOT NULL,

                                    quantity NUMERIC(19,2) NOT NULL,

                                    notes VARCHAR(500),

                                    created_at TIMESTAMP NOT NULL,

                                    updated_at TIMESTAMP NOT NULL,

                                    CONSTRAINT fk_inventory_movement_inventory
                                        FOREIGN KEY (inventory_id)
                                            REFERENCES inventory(id)

);
