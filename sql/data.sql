INSERT INTO status(name) VALUES ('ENABLED'), ('DISABLED'), ('PENDING');

INSERT INTO roles(name) VALUES ('USER'), ('ADMIN');

INSERT INTO users(email, status_id, role_id) VALUES ('armando@gmail.com', 1, 2),
('mares.bueno.brayan@gmail.com', 1, 2), ('fredy@gmail.com', 1, 1), ('eddy@gmail.com', 1, 1);

INSERT INTO memberships(membership_type, price, description, status_id) VALUES
('Free', 0, 'The basics for individuals and organizations', 1),
('Team', 4, 'Advanced individuals and organizations', 1),
('Enterprise', 21, 'Security, compliance, and flexible services', 1);

INSERT INTO membership_benefits(benefit, description, membership_id) VALUES
('Unlimited public/private repositories', 'Host open source projects in public GitHub repositories, accessible via web or command line. Public repositories are accessible to anyone at', 1),
('Automatic security and version updates', 'Keep projects secure by automatically opening pull requests that update vulnerable dependencies to secure versions, and update out-of-date dependencies.', 1),
('Everything included in Free', '', 2),
('Access to GitHub Codespaces', 'Blazing fast cloud developer environments with flexible compute and pre-configured containers, developers can code, collaborate, and debug from any browser.', 2),
('Everything included in Team', '', 3),
('Data residency', 'GitHub Enterprise Cloud offers a multi-tenant enterprise SaaS solution on Microsoft Azure, allowing you to choose a regional cloud deployment for data residency, so your in-scope data is stored at rest in a designated location.', 3);

INSERT INTO companies(name, logo, phone_number, instagram_url, facebook_url, membership_id, status_id)
VALUES('Barber', 'barber.png', '6544922933', 'https://www.instagram.com/', 'https://www.facebook.com/', 1, 1);

INSERT INTO owners(user_id, company_id, owner_hierarchy) VALUES(1, 1, 'OWNER');

INSERT INTO stores(name, address, description, latitude, longitude, status_id,
                   company_id)
VALUES ('Peluqueria A',
        'Av. Tecnológico Ciudad Industrial, Las Aves, 38010 Celaya, Gto',
        'Barberia profecional', 20.547389, -100.821518, 1, 1),
       ('Peluqueria B', 'Centro Dolores Hidalgo, GTO.',
        'Baberia El cortesito.', 21.157229, -100.934892, 1, 1),
       ('Barberia Mares', 'Av. de los Heroes Dolores Hidalgo.',
        'Esto es una barberia chida.', 21.158226, -100.940752, 1, 1);

INSERT INTO store_services(name, price, store_id, status_id) VALUES('Corte de pelo', 120, 1, 1);
INSERT INTO store_services(name, price, store_id, status_id) VALUES('Corte de barba', 50, 1, 1);

INSERT INTO store_employee(user_id, store_id, status_id) VALUES (3, 1, 1);

INSERT INTO user_favorite_stores(user_id, store_id) VALUES (3, 1);

INSERT INTO status_appointments(name) VALUES ('PENDING'), ('ACCEPTED'), ('IN_PROCESS'), ('CANCELED'), ('COMPLETED'), ('NOPAID'), ('AVAILABLE');

INSERT INTO client_appointments(user_appointment, status_appointment_id, store_service_id, client_id, store_employee_id, total_paid)
VALUES ('2025-02-26 14:00:00.84322', 1, 1, 1,1, 88.8);
INSERT INTO client_appointments(user_appointment, status_appointment_id, store_service_id, client_id, store_employee_id, total_paid)
VALUES ('2025-02-26 14:00:00.84322', 1, 1, 2, 1, 2);

INSERT INTO rate_appointment(comment, rate, client_appointment_id) VALUES('Muy buen servicio, personas muy amables', 4, 1);

INSERT INTO config_employee_schedule(day_of_week, start_time, end_time, start_time_break, end_time_break, interval_in_minutes, store_employee_id, default_status_appointment_id, appointments_per_client, status_id)
VALUES (1, '9:00', '18:00', '12:00', '14:00', 30, 1, 1, 1, 1),
(3, '9:00', '18:00', '12:00', '14:00', 30, 1, 1, 1, 1),
(5, '8:00', '18:00', '12:00', '14:00', 30, 1, 1, 2, 1);

