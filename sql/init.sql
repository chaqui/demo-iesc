-- Habilitar la salida de DBMS_OUTPUT
SET SERVEROUTPUT ON;

-- Eliminar el usuario c##demo si existe
BEGIN
    EXECUTE IMMEDIATE 'DROP USER c##demo CASCADE';
    DBMS_OUTPUT.PUT_LINE('Usuario c##demo eliminado.');
EXCEPTION
    WHEN OTHERS THEN
        IF SQLCODE != -1918 THEN
            RAISE;
        ELSE
            DBMS_OUTPUT.PUT_LINE('Usuario c##demo no existe.');
        END IF;
END;
/


-- Crear esquema (usuario local en el PDB)
BEGIN
    EXECUTE IMMEDIATE 'CREATE USER c##demo IDENTIFIED BY demo_password DEFAULT TABLESPACE USERS';
    DBMS_OUTPUT.PUT_LINE('Esquema c##demo creado.');
EXCEPTION
    WHEN OTHERS THEN
        IF SQLCODE != -1920 THEN
            RAISE;
        ELSE
            DBMS_OUTPUT.PUT_LINE('Esquema c##demo ya existe.');
        END IF;
END;
/

-- Otorgar privilegios al esquema
BEGIN
    EXECUTE IMMEDIATE 'GRANT CONNECT, RESOURCE TO c##demo';
    EXECUTE IMMEDIATE 'ALTER USER c##demo QUOTA UNLIMITED ON USERS';
    DBMS_OUTPUT.PUT_LINE('Privilegios otorgados al esquema c##demo.');
EXCEPTION
    WHEN OTHERS THEN
        IF SQLCODE != -1920 THEN
            RAISE;
        ELSE
            DBMS_OUTPUT.PUT_LINE('Privilegios ya otorgados al esquema c##demo.');
        END IF;
END;
/

-- Otorgar privilegios de INSERT al usuario c##demo
BEGIN
    EXECUTE IMMEDIATE 'GRANT INSERT ON c##demo.roles TO c##demo';
    EXECUTE IMMEDIATE 'GRANT INSERT ON c##demo.users TO c##demo';
    EXECUTE IMMEDIATE 'GRANT INSERT ON c##demo.clients TO c##demo';
    EXECUTE IMMEDIATE 'GRANT INSERT ON c##demo.type_inspections TO c##demo';
    EXECUTE IMMEDIATE 'GRANT INSERT ON c##demo.inspections TO c##demo';
    EXECUTE IMMEDIATE 'GRANT INSERT ON c##demo.pays TO c##demo';
    EXECUTE IMMEDIATE 'GRANT INSERT ON c##demo.detail_pays TO c##demo';
    DBMS_OUTPUT.PUT_LINE('Privilegios de INSERT otorgados al usuario c##demo.');
EXCEPTION
    WHEN OTHERS THEN
        RAISE;
END;
/

-- Crear secuencias
BEGIN
    EXECUTE IMMEDIATE 'CREATE SEQUENCE c##demo.sequence_id_user START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE';
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
    EXECUTE IMMEDIATE 'CREATE SEQUENCE c##demo.sequence_id_client START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE';
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

-- Crear tabla roles
BEGIN
    EXECUTE IMMEDIATE '
    CREATE TABLE c##demo.roles (
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

-- Crear tabla users
BEGIN
    EXECUTE IMMEDIATE '
    CREATE TABLE c##demo.users (
        id NUMBER PRIMARY KEY,
        username VARCHAR2(50) NOT NULL,
        password VARCHAR2(50) NOT NULL,
        role_id NUMBER NOT NULL,
        FOREIGN KEY (role_id) REFERENCES c##demo.roles(id)
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

-- Crear tabla clients
BEGIN
    EXECUTE IMMEDIATE '
    CREATE TABLE c##demo.clients (
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

-- Crear tabla type_inspections
BEGIN
    EXECUTE IMMEDIATE '
    CREATE TABLE c##demo.type_inspections (
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

-- Crear tabla inspections
BEGIN
    EXECUTE IMMEDIATE '
    CREATE TABLE c##demo.inspections (
        id NUMBER PRIMARY KEY,
        client_id NUMBER NOT NULL,
        user_id NUMBER NOT NULL,
        type_inspection_id NUMBER NOT NULL,
        inspection_date DATE NOT NULL,
        FOREIGN KEY (client_id) REFERENCES c##demo.clients(id),
        FOREIGN KEY (user_id) REFERENCES c##demo.users(id),
        FOREIGN KEY (type_inspection_id) REFERENCES c##demo.type_inspections(id)
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

-- eliminar tabla pays
BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE c##demo.pays';
    DBMS_OUTPUT.PUT_LINE('Tabla pays eliminada.');
EXCEPTION
    WHEN OTHERS THEN
        IF SQLCODE != -942 THEN
            RAISE;
        ELSE
            DBMS_OUTPUT.PUT_LINE('Tabla pays no existe.');
        END IF;
END;
/
-- Crear tabla pays
BEGIN
    EXECUTE IMMEDIATE '
    CREATE TABLE c##demo.pays (
        id NUMBER PRIMARY KEY,
        client_id NUMBER NOT NULL,
        status NUMBER(1) NOT NULL,
        amount NUMBER(10, 2) NOT NULL,
        pay_date DATE NOT NULL,
        FOREIGN KEY (client_id) REFERENCES c##demo.clients(id)
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

-- Crear tabla detail_pays
BEGIN
    EXECUTE IMMEDIATE '
    CREATE TABLE c##demo.detail_pays (
        id NUMBER PRIMARY KEY,
        pay_id NUMBER NOT NULL,
        inspection_id NUMBER NOT NULL,
        FOREIGN KEY (inspection_id) REFERENCES c##demo.inspections(id),
        FOREIGN KEY (pay_id) REFERENCES c##demo.pays(id)
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
    EXECUTE IMMEDIATE 'INSERT INTO c##demo.type_inspections (id, name, cost) VALUES (2, ''INSPECTION 2'', 200.00)';
    EXECUTE IMMEDIATE 'INSERT INTO c##demo.type_inspections (id, name, cost) VALUES (3, ''INSPECTION 2'', 300.00)';
    EXECUTE IMMEDIATE 'INSERT INTO c##demo.roles (id, name) VALUES (1, ''USER'')';
    EXECUTE IMMEDIATE 'INSERT INTO c##demo.roles (id, name) VALUES (2, ''SYSTEM'')';
    EXECUTE IMMEDIATE 'INSERT INTO c##demo.type_inspections (id, name, cost) VALUES (1, ''INSPECTION 1'', 100.00)';
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