CREATE TABLE clients
(
    client_id       VARCHAR(36)  NOT NULL,
    first_name      VARCHAR(100) NOT NULL,
    last_name       VARCHAR(100) NOT NULL,
    patronymic      VARCHAR(100) NOT NULL,
    phone_number    VARCHAR(15)  NOT NULL,
    email           VARCHAR(100) NOT NULL,
    passport_number INT          NOT NULL,
    PRIMARY KEY (client_id)
);
