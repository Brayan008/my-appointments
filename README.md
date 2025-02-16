<h1 align="center">My appointment Services</h1>

### Setting Up
To use the system, it is necessary to start the services in a specific order. The process is detailed below:

1. Start the Config Server
* The first service to be started is the Config Server, located in the infrastructure module. This service provides configuration for the system's microservices.

**Important Note:** Since the repository is private, the following steps must be followed:
* Create a file named `config.properties` in the root of the project.
* Add the following properties:  `git.repository` `git.username` `git.password` `security.name` `security.password`
* Follow the repository administrator's instructions to obtain the required properties.

3. Start the Eureka Server
4. Start the Gateway Server

**Important Note:** To start the services, it is necessary to configure the appropriate profile in the `application.yml` file of each project. The profiles are described below:

**Local Profile:** When using this profile, the service will automatically use the H2 database, so it will not be necessary to start the PostgreSQL database container.

**Dev Profile:** If this profile is configured, you will need to follow the steps outlined to create and start the database container.

### Steps to setting up the database

1. **Create the container:** To create the container, run the following command in the terminal: `docker-compose up -d`. Once the creation is complete, go to the following pgAdmin URL in your browser: [http://localhost:8080/login?next=/](http://localhost:8080/login?next=/).

2. **Enter credentials from the .env file:** Before proceeding, you need to enter your credentials from the `.env` file to access pgAdmin. Look for the `PGA_EMAIL` and `PGA_PASSWORD` variables and enter them during login.

3. **Create the server in pgAdmin:**  In the pgAdmin interface, go to the "Servers" section. Right-click to open a menu, then select "Register" and "Server."

4. **Server configuration:** In the server configuration window, fill in the following details:

   #### General
    - **name:** postgres (name of the PostgreSQL container)

   #### Connection
    - **Host name/address:** postgres (name of the PostgreSQL container)
    - **Post:** 5432
    - **Maintenance database:** appointment_db
    - **username:** appointment_user
    - **password:** YXNhbGF6YXJqQGdtYWlsLmNvbToqR

After completing the details, click "Save" to establish the connection.

**Note:** Remember that these details are stored in the `.env`, file, where your credentials were previously configured.
