INSERT INTO roles (id, name, description) VALUES (nextval('roles_id_sequence'), 'ADMIN', 'System Administrator') ON CONFLICT (name) DO NOTHING;
INSERT INTO roles (id, name, description) VALUES (nextval('roles_id_sequence'), 'USER', 'Normal System User') ON CONFLICT (name) DO NOTHING;

INSERT INTO users (id, name, username, email, password, created_at, updated_at) VALUES (nextval('users_id_sequence'), 'Administrator', 'admin', 'admin@cowork.co.mz', '$2a$10$EJVw5HLLZg6njyh9M2qHQutabTtkJTLcqYuk0V30M2NhWVYd22RMa', current_timestamp, current_timestamp) ON CONFLICT (username, email) DO NOTHING;
INSERT INTO users (id, name, username, email, password, created_at, updated_at) VALUES (nextval('users_id_sequence'), 'Amandio Nhamande', 'anhamande', 'anhamande@technoplus.co.mz', '$2a$10$EJVw5HLLZg6njyh9M2qHQutabTtkJTLcqYuk0V30M2NhWVYd22RMa', current_timestamp, current_timestamp) ON CONFLICT (username, email) DO NOTHING;
INSERT INTO users (id, name, username, email, password, created_at, updated_at) VALUES (nextval('users_id_sequence'), 'John Doe', 'jdoe', 'jdoe@cowork.co.mz', '$2a$10$EJVw5HLLZg6njyh9M2qHQutabTtkJTLcqYuk0V30M2NhWVYd22RMa', current_timestamp, current_timestamp) ON CONFLICT (username, email) DO NOTHING;

INSERT INTO user_roles (user_id, role_id) VALUES (1, 1) ON CONFLICT (user_id, role_id) DO NOTHING;
INSERT INTO user_roles (user_id, role_id) VALUES (2, 1) ON CONFLICT (user_id, role_id) DO NOTHING;
INSERT INTO user_roles (user_id, role_id) VALUES (3, 2) ON CONFLICT (user_id, role_id) DO NOTHING;