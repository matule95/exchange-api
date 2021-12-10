
INSERT INTO users (id, name, username, email, role, password, created_at, updated_at,user_status,deleted_at) VALUES (1, 'Administrator', 'admin', 'admin@cowork.co.mz','ADMIN', '$2a$10$EJVw5HLLZg6njyh9M2qHQutabTtkJTLcqYuk0V30M2NhWVYd22RMa', current_timestamp, current_timestamp,'ACTIVE',null);

INSERT INTO providers(id,name,email) VALUES(1,'FNB','fnb@fnb.com');

-- CURRENCIES INSERTIONS
INSERT INTO currencies(id,name,iso_code,provider_id) VALUES(1,'Mozambican Metical','MZN',1);
INSERT INTO currencies(id,name,iso_code,provider_id) VALUES(2,'United States Dollar','USD',1);
INSERT INTO currencies(id,name,iso_code,provider_id) VALUES(3,'Euro','EUR',1);
INSERT INTO currencies(id,name,iso_code,provider_id) VALUES(4,'British Pound','GBP',1);
INSERT INTO currencies(id,name,iso_code,provider_id) VALUES(5,'Angolan Kwanza','AOA',1);
INSERT INTO currencies(id,name,iso_code,provider_id) VALUES(6,'Brasilian Real','BRL',1);
INSERT INTO currencies(id,name,iso_code,provider_id) VALUES(7,'South African Rand','ZAR',1);

-- INSERT METICAL RATES

INSERT INTO rates(id,base_currency_id,target_currency_id,sale,purchase) VALUES(1,1,2,63,34);
INSERT INTO rates(id,base_currency_id,target_currency_id,sale,purchase) VALUES(2,1,3,63,34);
INSERT INTO rates(id,base_currency_id,target_currency_id,sale,purchase) VALUES(3,1,4,63,34);
INSERT INTO rates(id,base_currency_id,target_currency_id,sale,purchase) VALUES(4,1,5,63,34);
INSERT INTO rates(id,base_currency_id,target_currency_id,sale,purchase) VALUES(5,1,6,63,34);
INSERT INTO rates(id,base_currency_id,target_currency_id,sale,purchase) VALUES(6,1,7,63,34);


-- INSERT RATE HISTORY

INSERT INTO rate_history(id,rate_id,sale,purchase) VALUES(1,1,63,34);
INSERT INTO rate_history(id,rate_id,sale,purchase) VALUES(2,2,63,34);
INSERT INTO rate_history(id,rate_id,sale,purchase) VALUES(3,3,63,34);
INSERT INTO rate_history(id,rate_id,sale,purchase) VALUES(4,4,63,34);
INSERT INTO rate_history(id,rate_id,sale,purchase) VALUES(5,5,63,34);
INSERT INTO rate_history(id,rate_id,sale,purchase) VALUES(6,6,63,34);
