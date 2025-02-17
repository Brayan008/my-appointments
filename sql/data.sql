INSERT INTO status(name) VALUES ('ENABLED'), ('DISABLED');

INSERT INTO roles(name) VALUES ('USER'), ('ADMIN');

INSERT INTO users(email, status_id, role_id) VALUES ('armando@gmail.com', 1, 2), ('brayan@gmail.com', 1, 2);

INSERT INTO memberships(description, status_id) VALUES ('PREMIUM', 1), ('BASIC', 1);

INSERT INTO companies(name, logo, phone_number, instagram_url, facebook_url, membership_id, status_id)
VALUES('Appointments', 'appointments.png', '6544922933', 'https://www.instagram.com/', 'https://www.facebook.com/', 1, 1);

INSERT INTO owners(user_id, company_id, owner_hierarchy) VALUES(1, 1, 'OWNER');

INSERT INTO stores(name, address, description, coordinates, status_id, company_id)
VALUES('Developers', 'Av. Tecnológico Ciudad Industrial, Las Aves, 38010 Celaya, Gto',
'Una empresa de desarrollo de software', '1234', 1, 1);

INSERT INTO services (name, price, store_id) VALUES('Mobile Developer', 1000.20, 1);

INSERT INTO employees (user_id, store_id) VALUES (1, 1);
