
INSERT INTO users (id, name, username, email, role, password, created_at, updated_at,user_status,deleted_at) VALUES (1, 'Administrator', 'admin', 'admin@cowork.co.mz','ADMIN', '$2a$10$EJVw5HLLZg6njyh9M2qHQutabTtkJTLcqYuk0V30M2NhWVYd22RMa', current_timestamp, current_timestamp,'ACTIVE',null);

INSERT INTO currencies(name,abbreviation) values('Dolar','DOL');
INSERT INTO currencies(name,abbreviation) values('Metical','MZN');
INSERT INTO currencies(name,abbreviation) values('Euro','EUR');
INSERT INTO currencies(name,abbreviation) values('Libra','LIB');
