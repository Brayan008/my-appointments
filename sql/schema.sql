CREATE SEQUENCE status_sequence START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE roles_sequence START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE memberships_sequence START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE customers_sequence START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE companies_sequence START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE owners_sequence START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE stores_sequence START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE services_sequence START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE employees_sequence START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE user_favorite_stores_sequence START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE status_dates_sequence START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE client_dates_sequence START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE rate_date_sequence START WITH 1 INCREMENT BY 1;

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
    description TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT NOW()
);

CREATE TABLE customers(
	user_id BIGINT PRIMARY KEY DEFAULT nextval('customers_sequence'),
	status_id BIGINT NOT NULL,
	role_id BIGINT NOT NULL,
	created_at TIMESTAMP DEFAULT NOW(),
	CONSTRAINT fk_status FOREIGN KEY (status_id) REFERENCES status(status_id),
	CONSTRAINT fk_roles FOREIGN KEY (role_id) REFERENCES roles(role_id)
);

CREATE TABLE companies(
	company_id BIGINT PRIMARY KEY DEFAULT nextval('companies_sequence'),
	name TEXT NOT NULL,
	logo VARCHAR TEXT NOT NULL,
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
	created_at TIMESTAMP DEFAULT NOW(),
	CONSTRAINT fk_customers FOREIGN KEY (user_id) REFERENCES customers(user_id),
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
	created_at TIMESTAMP DEFAULT NOW(),
	CONSTRAINT fk_stores FOREIGN KEY (store_id) REFERENCES stores(store_id)
);

CREATE TABLE employees(
	employee_id BIGINT PRIMARY KEY DEFAULT nextval('employees_sequence'),
	user_id BIGINT NOT NULL,
	store_id BIGINT NOT NULL,
	created_at TIMESTAMP DEFAULT NOW(),
	CONSTRAINT fk_customers FOREIGN KEY (user_id) REFERENCES customers(user_id),
	CONSTRAINT fk_stores FOREIGN KEY (store_id) REFERENCES stores(store_id)
);

CREATE TABLE user_favorite_stores(
	employee_id BIGINT PRIMARY KEY DEFAULT nextval('user_favorite_stores_sequence'),
	user_id BIGINT NOT NULL,
	store_id BIGINT NOT NULL,
	status_id BIGINT NOT NULL,
	created_at TIMESTAMP DEFAULT NOW(),
	CONSTRAINT fk_customers FOREIGN KEY (user_id) REFERENCES customers(user_id),
	CONSTRAINT fk_status FOREIGN KEY (status_id) REFERENCES status(status_id),
	CONSTRAINT fk_stores FOREIGN KEY (store_id) REFERENCES stores(store_id)
);

CREATE TABLE status_dates(
	status_date_id BIGINT PRIMARY KEY DEFAULT nextval('status_dates_sequence'),
	name TEXT NOT NULL,
	created_at TIMESTAMP DEFAULT NOW()
);

CREATE TABLE client_dates(
	date_id BIGINT PRIMARY KEY DEFAULT nextval('client_dates_sequence'),
	user_date TIMESTAMP,
	status_date_id BIGINT NOT NULL,
	service_id BIGINT NOT NULL,
	client_id BIGINT NOT NULL,
	store_id BIGINT NOT NULL,
	employee_id BIGINT NOT NULL,
	created_at TIMESTAMP DEFAULT NOW(),
	CONSTRAINT fk_status_dates FOREIGN KEY (status_date_id) REFERENCES status_dates(status_date_id),
	CONSTRAINT fk_services FOREIGN KEY (service_id) REFERENCES services(service_id),
	CONSTRAINT fk_employees FOREIGN KEY (employee_id) REFERENCES employees(employee_id),
	CONSTRAINT fk_stores FOREIGN KEY (store_id) REFERENCES stores(store_id)
);

CREATE TABLE rate_date(
	rate_date_id BIGINT PRIMARY KEY DEFAULT nextval('rate_date_sequence'),
	comment TEXT,
	rate NUMERIC(3, 1) NOT NULL,
	date_id BIGINT NOT NULL,
	created_at TIMESTAMP DEFAULT NOW(),
	CONSTRAINT fk_client_dates FOREIGN KEY (date_id) REFERENCES client_dates(date_id)
);
