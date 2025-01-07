-- Habilitar la salida de DBMS_OUTPUT
SET SERVEROUTPUT ON;

-- Crear secuencias
BEGIN
    EXECUTE IMMEDIATE 'CREATE SEQUENCE sequence_id_user START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE';
    DBMS_OUTPUT.PUT_LINE('Secuencia sequence_id_user creada.');
EXCEPTION
    WHEN OTHERS THEN
        IF SQLCODE != -955 THEN
            RAISE;
        ELSE
            DBMS_OUTPUT.PUT_LINE('Secuencia sequence_id_user ya existe.');
        END IF;
END;
/

BEGIN
    EXECUTE IMMEDIATE 'CREATE SEQUENCE sequence_id_client START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE';
    DBMS_OUTPUT.PUT_LINE('Secuencia sequence_id_client creada.');
EXCEPTION
    WHEN OTHERS THEN
        IF SQLCODE != -955 THEN
            RAISE;
        ELSE
            DBMS_OUTPUT.PUT_LINE('Secuencia sequence_id_client ya existe.');
        END IF;
END;
/

BEGIN
    EXECUTE IMMEDIATE 'CREATE SEQUENCE sequence_id_inspection START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE';
    DBMS_OUTPUT.PUT_LINE('Secuencia sequence_id_inspection creada.');
EXCEPTION
    WHEN OTHERS THEN
        IF SQLCODE != -955 THEN
            RAISE;
        ELSE
            DBMS_OUTPUT.PUT_LINE('Secuencia sequence_id_inspection ya existe.');
        END IF;
END;
/

BEGIN
    EXECUTE IMMEDIATE 'CREATE SEQUENCE sequence_id_pay START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE';
    DBMS_OUTPUT.PUT_LINE('Secuencia sequence_id_pay creada.');
EXCEPTION
    WHEN OTHERS THEN
        IF SQLCODE != -955 THEN
            RAISE;
        ELSE
            DBMS_OUTPUT.PUT_LINE('Secuencia sequence_id_pay ya existe.');
        END IF;
END;
/

BEGIN
    EXECUTE IMMEDIATE 'CREATE SEQUENCE sequence_id_detail_pay START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE';
    DBMS_OUTPUT.PUT_LINE('Secuencia sequence_id_detail_pay creada.');
EXCEPTION
    WHEN OTHERS THEN
        IF SQLCODE != -955 THEN
            RAISE;
        ELSE
            DBMS_OUTPUT.PUT_LINE('Secuencia sequence_id_detail_pay ya existe.');
        END IF;
END;
/

-- Crear tablas
BEGIN
    EXECUTE IMMEDIATE '
    CREATE TABLE roles (
        id NUMBER PRIMARY KEY,
        name VARCHAR2(50) NOT NULL
    )';
    DBMS_OUTPUT.PUT_LINE('Tabla roles creada.');
EXCEPTION
    WHEN OTHERS THEN
        IF SQLCODE != -955 THEN
            RAISE;
        ELSE
            DBMS_OUTPUT.PUT_LINE('Tabla roles ya existe.');
        END IF;
END;
/

BEGIN
    EXECUTE IMMEDIATE '
    CREATE TABLE users (
        id NUMBER PRIMARY KEY,
        username VARCHAR2(50) NOT NULL,
        password VARCHAR2(50) NOT NULL,
        role_id NUMBER NOT NULL,
        FOREIGN KEY (role_id) REFERENCES roles(id)
    )';
    DBMS_OUTPUT.PUT_LINE('Tabla users creada.');
EXCEPTION
    WHEN OTHERS THEN
        IF SQLCODE != -955 THEN
            RAISE;
        ELSE
            DBMS_OUTPUT.PUT_LINE('Tabla users ya existe.');
        END IF;
END;
/

BEGIN
    EXECUTE IMMEDIATE '
    CREATE TABLE clients (
        id NUMBER PRIMARY KEY,
        name VARCHAR2(50) NOT NULL,
        email VARCHAR2(50) NOT NULL,
        phone VARCHAR2(50) NOT NULL,
        address VARCHAR2(50) NOT NULL
    )';
    DBMS_OUTPUT.PUT_LINE('Tabla clients creada.');
EXCEPTION
    WHEN OTHERS THEN
        IF SQLCODE != -955 THEN
            RAISE;
        ELSE
            DBMS_OUTPUT.PUT_LINE('Tabla clients ya existe.');
        END IF;
END;
/

BEGIN
    EXECUTE IMMEDIATE '
    CREATE TABLE type_inspections (
        id NUMBER PRIMARY KEY,
        name VARCHAR2(50) NOT NULL,
        cost NUMBER(10, 2) NOT NULL
    )';
    DBMS_OUTPUT.PUT_LINE('Tabla type_inspections creada.');
EXCEPTION
    WHEN OTHERS THEN
        IF SQLCODE != -955 THEN
            RAISE;
        ELSE
            DBMS_OUTPUT.PUT_LINE('Tabla type_inspections ya existe.');
        END IF;
END;
/

BEGIN
    EXECUTE IMMEDIATE '
    CREATE TABLE inspections (
        id NUMBER PRIMARY KEY,
        client_id NUMBER NOT NULL,
        user_id NUMBER NOT NULL,
        type_inspection_id NUMBER NOT NULL,
        inspection_date DATE NOT NULL,
        FOREIGN KEY (client_id) REFERENCES clients(id),
        FOREIGN KEY (user_id) REFERENCES users(id),
        FOREIGN KEY (type_inspection_id) REFERENCES type_inspections(id)
    )';
    DBMS_OUTPUT.PUT_LINE('Tabla inspections creada.');
EXCEPTION
    WHEN OTHERS THEN
        IF SQLCODE != -955 THEN
            RAISE;
        ELSE
            DBMS_OUTPUT.PUT_LINE('Tabla inspections ya existe.');
        END IF;
END;
/

BEGIN
    EXECUTE IMMEDIATE '
    CREATE TABLE pays (
        id NUMBER PRIMARY KEY,
        client_id NUMBER NOT NULL,
        amount NUMBER(10, 2) NOT NULL,
        pay_date DATE NOT NULL,
        FOREIGN KEY (client_id) REFERENCES clients(id)
    )';
    DBMS_OUTPUT.PUT_LINE('Tabla pays creada.');
EXCEPTION
    WHEN OTHERS THEN
        IF SQLCODE != -955 THEN
            RAISE;
        ELSE
            DBMS_OUTPUT.PUT_LINE('Tabla pays ya existe.');
        END IF;
END;
/

BEGIN
    EXECUTE IMMEDIATE '
    CREATE TABLE detail_pays (
        id NUMBER PRIMARY KEY,
        pay_id NUMBER NOT NULL,
        inspection_id NUMBER NOT NULL,
        FOREIGN KEY (inspection_id) REFERENCES inspections(id),
        FOREIGN KEY (pay_id) REFERENCES pays(id)
    )';
    DBMS_OUTPUT.PUT_LINE('Tabla detail_pays creada.');
EXCEPTION
    WHEN OTHERS THEN
        IF SQLCODE != -955 THEN
            RAISE;
        ELSE
            DBMS_OUTPUT.PUT_LINE('Tabla detail_pays ya existe.');
        END IF;
END;
/

-- Insertar datos iniciales
BEGIN
    EXECUTE IMMEDIATE 'INSERT INTO roles (id, name) VALUES (1, ''USER'')';
    EXECUTE IMMEDIATE 'INSERT INTO roles (id, name) VALUES (2, ''SYSTEM'')';
    EXECUTE IMMEDIATE 'INSERT INTO type_inspections (id, name, cost) VALUES (1, ''INSPECTION 1'', 100.00)';
    DBMS_OUTPUT.PUT_LINE('Datos iniciales insertados.');
EXCEPTION
    WHEN OTHERS THEN
        IF SQLCODE != -1 THEN
            RAISE;
        ELSE
            DBMS_OUTPUT.PUT_LINE('Datos iniciales ya existen.');
        END IF;
END;
/