CREATE SEQUENCE authorizations_id_sequence START 1;
CREATE TABLE authorizations (
    id BIGINT PRIMARY KEY DEFAULT nextval('authorizations_id_sequence'),
    type VARCHAR(30) NOT NULL,
    auth_json VARCHAR(256) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NULL,
    deleted_at TIMESTAMP NULL
);