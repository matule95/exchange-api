
INSERT INTO users (id, name, username, email, role, password, created_at, updated_at,user_status,deleted_at) VALUES (1, 'Administrator', 'admin', 'admin@cowork.co.mz','ADMIN', '$2a$10$EJVw5HLLZg6njyh9M2qHQutabTtkJTLcqYuk0V30M2NhWVYd22RMa', current_timestamp, current_timestamp,'ACTIVE',null);

-- CURRENCIES INSERTIONS
INSERT INTO currencies(id,name,iso_code) VALUES(1,'Mozambican Metical','MZN');
INSERT INTO currencies(id,name,iso_code) VALUES(2,'United States Dollar','USD');
INSERT INTO currencies(id,name,iso_code) VALUES(3,'Euro','EUR');
INSERT INTO currencies(id,name,iso_code) VALUES(4,'British Pound','GBP');
INSERT INTO currencies(id,name,iso_code) VALUES(5,'Angolan Kwanza','AOA');
INSERT INTO currencies(id,name,iso_code) VALUES(6,'Brasilian Real','BRL');
INSERT INTO currencies(id,name,iso_code) VALUES(7,'South African Rand','ZAR');

-- INSERT METICAL RATES

INSERT INTO rates(base_currency_id,target_currency_id,sale,purchase) VALUES(1,2,63,34);
INSERT INTO rates(base_currency_id,target_currency_id,sale,purchase) VALUES(1,3,63,34);
INSERT INTO rates(base_currency_id,target_currency_id,sale,purchase) VALUES(1,4,63,34);
INSERT INTO rates(base_currency_id,target_currency_id,sale,purchase) VALUES(1,5,63,34);
INSERT INTO rates(base_currency_id,target_currency_id,sale,purchase) VALUES(1,6,63,34);
INSERT INTO rates(base_currency_id,target_currency_id,sale,purchase) VALUES(1,7,63,34);


-- INSERT USD RATES

INSERT INTO rates(base_currency_id,target_currency_id,sale,purchase) VALUES(2,1,63,34);
INSERT INTO rates(base_currency_id,target_currency_id,sale,purchase) VALUES(2,3,63,34);
INSERT INTO rates(base_currency_id,target_currency_id,sale,purchase) VALUES(2,4,63,34);
INSERT INTO rates(base_currency_id,target_currency_id,sale,purchase) VALUES(2,5,63,34);
INSERT INTO rates(base_currency_id,target_currency_id,sale,purchase) VALUES(2,6,63,34);
INSERT INTO rates(base_currency_id,target_currency_id,sale,purchase) VALUES(2,7,63,34);
