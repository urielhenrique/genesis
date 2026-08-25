CREATE TABLE app_user (

                          id UUID PRIMARY KEY,

                          name VARCHAR(120) NOT NULL,

                          email VARCHAR(180) NOT NULL UNIQUE,

                          role VARCHAR(30) NOT NULL,

                          active BOOLEAN NOT NULL,

                          created_at TIMESTAMP NOT NULL,

                          updated_at TIMESTAMP NOT NULL
);
