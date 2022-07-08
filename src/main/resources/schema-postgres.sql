CREATE SCHEMA IF NOT EXISTS api_test_app;

CREATE TABLE IF NOT EXISTS api_test_app.phone_number
(
    id           BIGSERIAL PRIMARY KEY,
    phone_number VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS api_test_app.address
(
    id     BIGSERIAL PRIMARY KEY,
    first  VARCHAR,
    second VARCHAR
);

CREATE TABLE IF NOT EXISTS api_test_app.user
(
    id       BIGSERIAL PRIMARY KEY,
    role_id  INTEGER,
    nickname VARCHAR(10),
    password VARCHAR
);

CREATE TABLE IF NOT EXISTS api_test_app.department
(
    id              BIGSERIAL PRIMARY KEY,
    name            VARCHAR(200),
    boss            BIGINT,
    address_id      BIGINT,
    phone_number_id BIGINT
);

CREATE TABLE IF NOT EXISTS api_test_app.employee
(
    id              BIGSERIAL PRIMARY KEY,
    first_name      VARCHAR(60),
    last_name       VARCHAR(60),
    patronymic      VARCHAR(60),
    phone_number_id BIGINT,
    department_id   BIGINT
);

CREATE TABLE IF NOT EXISTS api_test_app.department_employee
(
    employee_id   BIGINT NOT NULL,
    department_id BIGINT NOT NULL,
    FOREIGN KEY (employee_id) REFERENCES api_test_app.employee (id),
    FOREIGN KEY (department_id) REFERENCES api_test_app.department (id),
    PRIMARY KEY (employee_id, department_id)
);
