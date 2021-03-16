CREATE TABLE clients
(
    client_id       UUID                NOT NULL,
    first_name      VARCHAR(100)        NOT NULL,
    last_name       VARCHAR(100)        NOT NULL,
    patronymic      VARCHAR(100)        NOT NULL,
    phone_number    VARCHAR(15) UNIQUE  NOT NULL,
    email           VARCHAR(100) UNIQUE NOT NULL,
    passport_number INT UNIQUE          NOT NULL,
    PRIMARY KEY (client_id)
);



CREATE TABLE credits
(
    credit_id     UUID                NOT NULL,
    name          VARCHAR(100) UNIQUE NOT NULL,
    loan_limit    INT                 NOT NULL,
    interest_rate DOUBLE              NOT NULL,
    PRIMARY KEY (credit_id)
);



CREATE TABLE bank
(
    client_id UUID NOT NULL,
    credit_id UUID NOT NULL,
    PRIMARY KEY (client_id, credit_id),
    FOREIGN KEY (client_id) REFERENCES clients (client_id),
    FOREIGN KEY (credit_id) REFERENCES credits (credit_id)
);



CREATE TABLE credit_offers
(
    credit_offer_id UUID   NOT NULL,
    client_id       UUID   NOT NULL,
    credit_id       UUID   NOT NULL,
    credit_amount   DOUBLE NOT NULL,
    PRIMARY KEY (credit_offer_id),
    FOREIGN KEY (client_id) REFERENCES clients (client_id) ON DELETE CASCADE,
    FOREIGN KEY (credit_id) REFERENCES credits (credit_id) ON DELETE CASCADE
);



CREATE TABLE payment_schedules
(
    payment_schedule_id          UUID                                NOT NULL,
    credit_offer_id              UUID                                NOT NULL,
    date                         TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    amount_payment               DOUBLE                              NOT NULL,
    repayment_amount_loan_body   DOUBLE                              NOT NULL,
    repayment_amount_percentages DOUBLE                              NOT NULL,
    indebtedness                 DOUBLE                              NOT NULL,
    PRIMARY KEY (payment_schedule_id),
    FOREIGN KEY (credit_offer_id) REFERENCES credit_offers (credit_offer_id) ON DELETE CASCADE
);
