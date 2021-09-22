CREATE SEQUENCE endpoints_id_sequence START 1;
CREATE TABLE endpoints (
    id BIGINT PRIMARY KEY DEFAULT nextval('endpoints_id_sequence'),
    url VARCHAR(256) NOT NULL,
    data_reader VARCHAR(256) NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP NULL
);