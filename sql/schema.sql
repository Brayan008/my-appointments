CREATE TABLE status (
    status_id VARCHAR(15) PRIMARY KEY NOT NULL,
    name VARCHAR(15) NOT NULL,
    created_at TIMESTAMP DEFAULT NOW()
);

CREATE TABLE roles (
    role_id VARCHAR(15) PRIMARY KEY NOT NULL,
    name VARCHAR(15) NOT NULL,
    created_at TIMESTAMP DEFAULT NOW()
);

CREATE TABLE memberships (
    membership_id VARCHAR(15) PRIMARY KEY NOT NULL,
    description VARCHAR(15) NOT NULL,
    created_at TIMESTAMP DEFAULT NOW()
);

CREATE TABLE customers(
	user_id VARCHAR(50) PRIMARY KEY NOT NULL,
	status_id VARCHAR (15) NOT NULL,
	role_id VARCHAR (15) NOT NULL,
	created_at TIMESTAMP DEFAULT NOW(),
	CONSTRAINT fk_status FOREIGN KEY (status_id) REFERENCES status(status_id),
	CONSTRAINT fk_roles FOREIGN KEY (role_id) REFERENCES roles(role_id)
);

CREATE TABLE companies(
	company_id VARCHAR(15) PRIMARY KEY,
	name VARCHAR (15) NOT NULL,
	logo VARCHAR (15) NOT NULL,
	phone_number VARCHAR(15),
	instagram_url TEXT,
	facebook_url TEXT,
	membership_id VARCHAR (15) NOT NULL,
	status_id VARCHAR (15) NOT NULL,
	created_at TIMESTAMP DEFAULT NOW(),
	CONSTRAINT fk_memberships FOREIGN KEY (membership_id) REFERENCES memberships(membership_id),
	CONSTRAINT fk_status FOREIGN KEY (status_id) REFERENCES status(status_id)
);


CREATE TABLE owners(
	owner_id VARCHAR(15) PRIMARY KEY NOT NULL,
	user_id VARCHAR (50) NOT NULL,
	company_id VARCHAR (15) NOT NULL,
	created_at TIMESTAMP DEFAULT NOW(),
	CONSTRAINT fk_customers FOREIGN KEY (user_id) REFERENCES customers(user_id),
	CONSTRAINT fk_companies FOREIGN KEY (company_id) REFERENCES companies(company_id)
);

CREATE TABLE stores(
	store_id VARCHAR(15) PRIMARY KEY,
	name VARCHAR (50),
	address VARCHAR(200),
	description TEXT,
	coordinates VARCHAR(400),
	status_id VARCHAR (15) NOT NULL,
	company_id VARCHAR (15) NOT NULL,
	created_at TIMESTAMP DEFAULT NOW(),
	CONSTRAINT fk_status FOREIGN KEY (status_id) REFERENCES status(status_id),
	CONSTRAINT fk_companies FOREIGN KEY (company_id) REFERENCES companies(company_id)
);

CREATE TABLE services(
	service_id VARCHAR(15) PRIMARY KEY NOT NULL,
	name TEXT NOT NULL,
	price NUMERIC(12, 2) NOT NULL,
	store_id VARCHAR(15) NOT NULL,
	created_at TIMESTAMP DEFAULT NOW(),
	CONSTRAINT fk_stores FOREIGN KEY (store_id) REFERENCES stores(store_id)
);

CREATE TABLE employees(
	employee_id VARCHAR(15) PRIMARY KEY NOT NULL,
	user_id VARCHAR(50) NOT NULL,
	store_id VARCHAR(15) NOT NULL,
	created_at TIMESTAMP DEFAULT NOW(),
	CONSTRAINT fk_customers FOREIGN KEY (user_id) REFERENCES customers(user_id),
	CONSTRAINT fk_stores FOREIGN KEY (store_id) REFERENCES stores(store_id)
);

CREATE TABLE user_favorite_stores(
	employee_id VARCHAR(15) PRIMARY KEY NOT NULL,
	user_id VARCHAR(50) NOT NULL,
	store_id VARCHAR(15) NOT NULL,
	status_id VARCHAR (15) NOT NULL,
	created_at TIMESTAMP DEFAULT NOW(),
	CONSTRAINT fk_customers FOREIGN KEY (user_id) REFERENCES customers(user_id),
	CONSTRAINT fk_status FOREIGN KEY (status_id) REFERENCES status(status_id),
	CONSTRAINT fk_stores FOREIGN KEY (store_id) REFERENCES stores(store_id)
);

CREATE TABLE status_dates(
	status_date_id VARCHAR(15) PRIMARY KEY NOT NULL,
	name VARCHAR(50) NOT NULL,
	created_at TIMESTAMP DEFAULT NOW()
);

CREATE TABLE client_dates(
	date_id VARCHAR(15) PRIMARY KEY NOT NULL,
	user_date TIMESTAMP,
	status_date_id VARCHAR(15) NOT NULL,
	service_id VARCHAR(15) NOT NULL,
	client_id VARCHAR(15) NOT NULL,
	store_id VARCHAR(15) NOT NULL,
	employee_id VARCHAR(15) NOT NULL,
	created_at TIMESTAMP DEFAULT NOW(),
	CONSTRAINT fk_status_dates FOREIGN KEY (status_date_id) REFERENCES status_dates(status_date_id),
	CONSTRAINT fk_services FOREIGN KEY (service_id) REFERENCES services(service_id),
	CONSTRAINT fk_employees FOREIGN KEY (employee_id) REFERENCES employees(employee_id),
	CONSTRAINT fk_stores FOREIGN KEY (store_id) REFERENCES stores(store_id)
);

CREATE TABLE rate_date(
	rate_date_id VARCHAR(15) PRIMARY KEY NOT NULL,
	comment TEXT,
	rate NUMERIC(3, 1) NOT NULL,
	date_id VARCHAR(15) NOT NULL,
	created_at TIMESTAMP DEFAULT NOW(),
	CONSTRAINT fk_client_dates FOREIGN KEY (date_id) REFERENCES client_dates(date_id)
);
