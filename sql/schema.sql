CREATE SEQUENCE status_sequence START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE roles_sequence START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE memberships_sequence START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE membership_benefits_sequence START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE users_sequence START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE companies_sequence START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE owners_sequence START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE stores_sequence START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE services_sequence START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE store_employee_sequence START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE user_favorite_stores_sequence START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE status_appointments_sequence START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE client_appointments_sequence START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE rate_appointment_sequence START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE config_employee_schedule_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE status (
  status_id BIGINT PRIMARY KEY DEFAULT nextval('status_sequence'),
  name VARCHAR(50) NOT NULL,
  created_at TIMESTAMP DEFAULT NOW()
);

CREATE TABLE roles (
  role_id BIGINT PRIMARY KEY DEFAULT nextval('roles_sequence'),
  name VARCHAR(50) NOT NULL,
  created_at TIMESTAMP DEFAULT NOW()
);

CREATE TABLE memberships (
  membership_id BIGINT PRIMARY KEY DEFAULT nextval('memberships_sequence'),
  membership_type	VARCHAR(80) NOT NULL,
  price NUMERIC(12, 2) NOT NULL,
  description TEXT NOT NULL,
  status_id BIGINT NOT NULL,
  created_at TIMESTAMP DEFAULT NOW(),
  CONSTRAINT fk_status FOREIGN KEY (status_id) REFERENCES status(status_id)
);

CREATE TABLE membership_benefits (
  membership_benefit_id BIGINT PRIMARY KEY DEFAULT nextval('membership_benefits_sequence'),
  benefit TEXT NOT NULL,
  description TEXT NOT NULL,
  membership_id BIGINT NOT NULL,
  created_at TIMESTAMP DEFAULT NOW(),
  CONSTRAINT fk_memberships FOREIGN KEY (membership_id) REFERENCES memberships(membership_id)
);

CREATE TABLE users(
  user_id BIGINT PRIMARY KEY DEFAULT nextval('users_sequence'),
  email VARCHAR(100) NOT NULL UNIQUE,
  status_id BIGINT NOT NULL,
  role_id BIGINT NOT NULL,
  created_at TIMESTAMP DEFAULT NOW(),
  CONSTRAINT fk_status FOREIGN KEY (status_id) REFERENCES status(status_id),
  CONSTRAINT fk_roles FOREIGN KEY (role_id) REFERENCES roles(role_id)
);

CREATE TABLE companies(
  company_id BIGINT PRIMARY KEY DEFAULT nextval('companies_sequence'),
  name TEXT NOT NULL,
  logo TEXT NOT NULL,
  phone_number VARCHAR(15),
  instagram_url TEXT,
  facebook_url TEXT,
  membership_id BIGINT NOT NULL,
  status_id BIGINT NOT NULL,
  created_at TIMESTAMP DEFAULT NOW(),
  CONSTRAINT fk_memberships FOREIGN KEY (membership_id) REFERENCES memberships(membership_id),
  CONSTRAINT fk_status FOREIGN KEY (status_id) REFERENCES status(status_id)
);

CREATE TABLE owners(
  owner_id BIGINT PRIMARY KEY DEFAULT nextval('owners_sequence'),
  user_id BIGINT NOT NULL,
  company_id BIGINT NOT NULL,
  owner_hierarchy VARCHAR(90),
  created_at TIMESTAMP DEFAULT NOW(),
  CONSTRAINT fk_users FOREIGN KEY (user_id) REFERENCES users(user_id),
  CONSTRAINT fk_companies FOREIGN KEY (company_id) REFERENCES companies(company_id)
);

CREATE TABLE stores(
  store_id BIGINT PRIMARY KEY DEFAULT nextval('stores_sequence'),
  name TEXT,
  address VARCHAR(200),
  description TEXT,
  coordinates VARCHAR(400),
  status_id BIGINT NOT NULL,
  company_id BIGINT NOT NULL,
  created_at TIMESTAMP DEFAULT NOW(),
  CONSTRAINT fk_status FOREIGN KEY (status_id) REFERENCES status(status_id),
  CONSTRAINT fk_companies FOREIGN KEY (company_id) REFERENCES companies(company_id)
);

CREATE TABLE services(
  service_id BIGINT PRIMARY KEY DEFAULT nextval('services_sequence'),
  name TEXT NOT NULL,
  price NUMERIC(12, 2) NOT NULL,
  store_id BIGINT NOT NULL,
  status_id BIGINT NOT NULL,
  created_at TIMESTAMP DEFAULT NOW(),
  CONSTRAINT fk_status FOREIGN KEY (status_id) REFERENCES status(status_id),
  CONSTRAINT fk_stores FOREIGN KEY (store_id) REFERENCES stores(store_id)
);

CREATE TABLE store_employee(
  store_employee_id BIGINT PRIMARY KEY DEFAULT nextval('store_employee_sequence'),
  user_id BIGINT NOT NULL,
  store_id BIGINT NOT NULL,
  status_id BIGINT NOT NULL,
  created_at TIMESTAMP DEFAULT NOW(),
  CONSTRAINT fk_users FOREIGN KEY (user_id) REFERENCES users(user_id),
  CONSTRAINT fk_stores FOREIGN KEY (store_id) REFERENCES stores(store_id),
  CONSTRAINT fk_status FOREIGN KEY (status_id) REFERENCES status(status_id)
);

CREATE TABLE user_favorite_stores(
  user_favorite_store_id BIGINT PRIMARY KEY DEFAULT nextval('user_favorite_stores_sequence'),
  user_id BIGINT NOT NULL,
  store_id BIGINT NOT NULL,
  created_at TIMESTAMP DEFAULT NOW(),
  CONSTRAINT fk_users FOREIGN KEY (user_id) REFERENCES users(user_id),
  CONSTRAINT fk_stores FOREIGN KEY (store_id) REFERENCES stores(store_id)
);

CREATE TABLE status_appointments(
  status_appointment_id BIGINT PRIMARY KEY DEFAULT nextval('status_appointments_sequence'),
  name TEXT NOT NULL,
  created_at TIMESTAMP DEFAULT NOW()
);

CREATE TABLE client_appointments(
  client_appointment_id BIGINT PRIMARY KEY DEFAULT nextval('client_appointments_sequence'),
  user_appointment TIMESTAMP,
  status_appointment_id BIGINT NOT NULL,
  service_id BIGINT NOT NULL,
  client_id BIGINT NOT NULL,
  store_employee_id BIGINT NOT NULL,
  total_paid DECIMAL,
  created_at TIMESTAMP DEFAULT NOW(),
  CONSTRAINT fk_status_appointments FOREIGN KEY (status_appointment_id) REFERENCES status_appointments(status_appointment_id),
  CONSTRAINT fk_services FOREIGN KEY (service_id) REFERENCES services(service_id),
  CONSTRAINT fk_client FOREIGN KEY (client_id) REFERENCES users(user_id),
  CONSTRAINT fk_store_employee FOREIGN KEY (store_employee_id) REFERENCES store_employee(store_employee_id)
);

CREATE TABLE rate_appointment(
  rate_appointment_id BIGINT PRIMARY KEY DEFAULT nextval('rate_appointment_sequence'),
  comment TEXT,
  rate NUMERIC(3, 1) NOT NULL,
  client_appointment_id BIGINT NOT NULL,
  created_at TIMESTAMP DEFAULT NOW(),
  CONSTRAINT fk_client_appointments FOREIGN KEY (client_appointment_id) REFERENCES client_appointments(client_appointment_id)
);

CREATE TABLE config_employee_schedule(
  config_employee_schedule_id BIGINT PRIMARY KEY DEFAULT nextval('config_employee_schedule_sequence'),
  day_of_week INTEGER NOT NULL,
  start_time  TIME    NOT NULL,
  end_time    TIME    NOT NULL,
  start_time_break  TIME NOT NULL,
  end_time_break    TIME NOT NULL,
  interval_in_minutes INTEGER NOT NULL,
  store_employee_id INTEGER  NOT NULL,
  default_status_date_id INTEGER  NOT NULL,
  appointments_per_day INTEGER NOT NULL,
  created_at TIMESTAMP DEFAULT NOW(),
  CONSTRAINT fk_store_employee FOREIGN KEY (store_employee_id) REFERENCES store_employee(store_employee_id),
  CONSTRAINT fk_status_appointments FOREIGN KEY (default_status_date_id) REFERENCES status_appointments(status_appointment_id)

);
