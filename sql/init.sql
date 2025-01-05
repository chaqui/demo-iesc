CREATE SEQUENCE IF NOT EXISTS sequence_id_user START 1;
CREATE SEQUENCE IF NOT EXISTS sequence_id_client START 1;
CREATE SEQUENCE IF NOT EXISTS sequence_id_inspection START 1;
CREATE SEQUENCE IF NOT EXISTS sequence_id_pay START 1;
CREATE SEQUENCE IF NOT EXISTS sequence_id_detail_pay START 1;


CREATE TABLE IF NOT EXISTS roles (
    id INT PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS users (
    id INT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL
    role_id INT NOT NULL,
    FOREIGN KEY (role_id) REFERENCES roles(id)
);

CREATE TABLE IF NOT EXISTS clients(
    id INT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    phone VARCHAR(50) NOT NULL,
    address VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS type_inspections(
    id INT PRIMARY KEY,
    name VARCHAR(50) NOT NULL
    cost DECIMAL(10,2) NOT NULL
);

CREATE TABLE IF NOT EXISTS inspections(
    id INT PRIMARY KEY,
    client_id INT NOT NULL,
    type_inspection_id INT NOT NULL,
    date DATE NOT NULL,
    FOREIGN KEY (client_id) REFERENCES clients(id),
    FOREIGN KEY (type_inspection_id) REFERENCES type_inspections(id)
);

CREATE TABLE IF NOT EXISTS pay(
    id INT PRIMARY KEY,
    client_id INT NOT NULL,
    amount DECIMAL(10,2) NOT NULL,
    date DATE NOT NULL,
    FOREIGN KEY (client_id) REFERENCES clients(id)
);

CREATE TABLE IF NOT EXISTS detail_pay(
    id INT PRIMARY KEY,
    pay_id INT NOT NULL,
    inspection_id INT NOT NULL,
    FOREIGN KEY (inspection_id) REFERENCES inspections(id),
    FOREIGN KEY (pay_id) REFERENCES pay(id)
);

INSERT INTO roles (id, name) VALUES (1, 'USER');
INSERT INTO roles (id, name) VALUES (2, 'SYSTEM');


INSERT INTO type_inspections (id, name, cost) VALUES (1, 'INSPECTION 1', 100.00);
INSERT INTO type_inspections (id, name, cost) VALUES (2, 'INSPECTION 2', 200.00);
INSERT INTO type_inspections (id, name, cost) VALUES (3, 'INSPECTION 3', 300.00);
INSERT INTO type_inspections (id, name, cost) VALUES (4, 'INSPECTION 4', 400.00);
INSERT INTO type_inspections (id, name, cost) VALUES (5, 'INSPECTION 5', 500.00);

