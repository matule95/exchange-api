
CREATE SEQUENCE connections_id_sequence START 1;
CREATE TABLE connections (
            id BIGINT PRIMARY KEY DEFAULT nextval('connections_id_sequence'),
            from_third_party BIGINT NOT NULL,
            from_url VARCHAR(256) NOT NULL,
            from_request_Type VARCHAR(256) NOT NULL,
            to_third_party BIGINT NOT NULL,
            request_executor_id BIGINT NOT NULL,
            to_url VARCHAR(256) NOT NULL,
            to_request_type VARCHAR(256) NOT NULL,
            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
            updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
            deleted_at TIMESTAMP NULL
);

CREATE TABLE request_executor (
            id SERIAL PRIMARY KEY,
            frequency VARCHAR(256) DEFAULT NULL,
            unity VARCHAR(256) DEFAULT NULL,
            every BIGINT DEFAULT NULL,
            execute_at TIMESTAMP DEFAULT NULL,
);