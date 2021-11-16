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
