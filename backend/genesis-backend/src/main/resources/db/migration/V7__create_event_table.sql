CREATE TABLE event (
                       id UUID PRIMARY KEY,
                       name VARCHAR(120) NOT NULL,
                       description VARCHAR(500),
                       event_date TIMESTAMP NOT NULL,
                       active BOOLEAN NOT NULL,
                       created_at TIMESTAMP NOT NULL,
                       updated_at TIMESTAMP NOT NULL
);
