INSERT INTO roles(name) VALUES ('ROL_ADMIN');
INSERT INTO roles (name) VALUES ('ROL_USER');

INSERT INTO tenants(id, active, name) VALUES (1, false, 'SAMPLE');

INSERT INTO public.users(email, name, password, tenant_id) VALUES ('test@test.com', 'test', 'test', 1);
