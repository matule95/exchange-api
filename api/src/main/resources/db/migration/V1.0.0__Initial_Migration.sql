CREATE SEQUENCE users_id_sequence START 1;
CREATE TABLE users (
    id BIGINT PRIMARY KEY DEFAULT nextval('users_id_sequence'),
    name VARCHAR(256) NOT NULL,
    username VARCHAR(256) NOT NULL,
    email VARCHAR(256) NOT NULL UNIQUE,
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

CREATE SEQUENCE roles_id_sequence START 1;
CREATE TABLE roles (
    id BIGINT PRIMARY KEY DEFAULT nextval('roles_id_sequence'),
    name VARCHAR(256) NOT NULL,
    description VARCHAR(256) NOT NULL,

    UNIQUE(name)
);

CREATE TABLE user_roles (
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    UNIQUE (user_id, role_id),
    CONSTRAINT FK_user_roles_user_id FOREIGN KEY (user_id) REFERENCES users (id),
    CONSTRAINT FK_user_roles_role_id FOREIGN KEY (role_id) REFERENCES roles (id)
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
