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



CREATE TABLE credits
(
    credit_id     VARCHAR(36) NOT NULL,
    loan_limit    INT         NOT NULL,
    interest_rate DOUBLE      NOT NULL,
    PRIMARY KEY (credit_id),
);



CREATE TABLE banks
(
    client_id VARCHAR(36) NOT NULL,
    credit_id VARCHAR(36) NOT NULL,
    FOREIGN KEY (client_id) REFERENCES clients (client_id),
    FOREIGN KEY (credit_id) REFERENCES credits (credit_id)
);



CREATE TABLE credit_offers
(
    credit_offer_id VARCHAR(36) NOT NULL,
    client_id       VARCHAR(36) NOT NULL,
    credit_id       VARCHAR(36) NOT NULL,
    credit_amount   DOUBLE      NOT NULL,
    PRIMARY KEY (credit_offer_id),
    FOREIGN KEY (client_id) REFERENCES clients (client_id),
    FOREIGN KEY (credit_id) REFERENCES credits (credit_id)
);



CREATE TABLE payment_schedules
(
    payment_schedule_id          VARCHAR(36) NOT NULL,
    credit_offer_id              VARCHAR(36) NOT NULL,
    date                         TIMESTAMP   NOT NULL,
    amount_payment               DOUBLE      NOT NULL,
    repayment_amount_loan_body   DOUBLE      NOT NULL,
    repayment_amount_percentages DOUBLE      NOT NULL,
    PRIMARY KEY (payment_schedule_id),
    FOREIGN KEY (credit_offer_id) REFERENCES credit_offers (credit_offer_id)
);
