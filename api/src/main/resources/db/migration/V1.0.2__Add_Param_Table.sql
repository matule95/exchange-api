
CREATE SEQUENCE params_id_sequence START 1;
CREATE TABLE params (
            id BIGINT PRIMARY KEY DEFAULT nextval('params_id_sequence'),
            from_field    VARCHAR(255),
            to_field      VARCHAR(255),
            connection_id BIGINT
);

ALTER TABLE params
    ADD CONSTRAINT FK_PARAMS_ON_CONNECTION FOREIGN KEY (connection_id) REFERENCES connections (id);