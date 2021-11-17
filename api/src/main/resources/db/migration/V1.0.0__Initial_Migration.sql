CREATE SEQUENCE users_id_sequence START 1;
CREATE TABLE users (
    id BIGINT PRIMARY KEY DEFAULT nextval('users_id_sequence'),
    name VARCHAR(256) ,
    username VARCHAR(256) NOT NULL,
    email VARCHAR(256) NOT NULL UNIQUE,
    role VARCHAR(32) NOT NULL,
    responsibility VARCHAR(32),
    password VARCHAR(256) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    UNIQUE (username, email)
);

CREATE SEQUENCE password_resets_id_sequence START 1;
CREATE TABLE password_resets (
    id BIGINT PRIMARY KEY DEFAULT nextval('password_resets_id_sequence'),
    user_email VARCHAR(256) NOT NULL,
    token VARCHAR(256) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    expires_at TIMESTAMP NOT NULL
);

CREATE SEQUENCE auth_token_id_sequence START 1;
CREATE TABLE auth_tokens (
    id BIGINT PRIMARY KEY DEFAULT nextval('auth_token_id_sequence'),
    token VARCHAR(256) DEFAULT NULL,
    valid_until TIMESTAMP DEFAULT NULL,
    refresh_until TIMESTAMP DEFAULT NULL,
    is_blacklisted BOOLEAN NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    user_id BIGINT NOT NULL
);

CREATE TABLE companies (
    id SERIAL PRIMARY KEY,
    name VARCHAR(256) NOT NULL,
    email VARCHAR(256) NOT NULL UNIQUE,
    base_url VARCHAR(256) NOT NULL,
    company_status BOOLEAN DEFAULT TRUE,
    username_checkmob VARCHAR(256) NOT NULL,
    password_checkmob VARCHAR(256) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NULL,
    deleted_at TIMESTAMP NULL,

    UNIQUE (email)
);

CREATE SEQUENCE authorizations_id_sequence START 1;
CREATE TABLE authorizations (
    id BIGINT PRIMARY KEY DEFAULT nextval('authorizations_id_sequence'),
    type VARCHAR(30) NOT NULL,
    auth_json VARCHAR(256) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NULL,
    deleted_at TIMESTAMP NULL
);

CREATE SEQUENCE endpoints_id_sequence START 1;
CREATE TABLE endpoints (
    id BIGINT PRIMARY KEY DEFAULT nextval('endpoints_id_sequence'),
    name VARCHAR(32) NOT NULL,
    url VARCHAR(256) NOT NULL,
    company_id BIGINT NOT NULL,
    authentication_type VARCHAR(256) NOT NULL,
    data_reader VARCHAR(256) NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP NULL
);

