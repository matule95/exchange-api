
ALTER TABLE connections
           ADD COLUMN from_third_party_id BIGINT,
           ADD COLUMN to_third_party_id BIGINT,
           DROP COLUMN from_third_party,
           DROP COLUMN to_third_party;




ALTER TABLE connections
    ADD CONSTRAINT FK_FROM_ON_CONNECTION FOREIGN KEY (from_third_party_id) REFERENCES endpoints (id),
    ADD CONSTRAINT FK_TO_ON_CONNECTION FOREIGN KEY (to_third_party_id) REFERENCES endpoints (id);