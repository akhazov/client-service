--liquibase formatted sql

--changeset akhazov:08-06-23-create_table-clients
CREATE TABLE IF NOT EXISTS clients_schema.clients
(
    id                   UUID NOT NULL,
    bank_id              UUID,
    first_name           VARCHAR(255),
    middle_name          VARCHAR(255),
    last_name            VARCHAR(255),
    birthdate            DATE,
    passport_id          VARCHAR(11),
    birthplace           VARCHAR(512),
    phone_number         VARCHAR(36),
    email                VARCHAR(255),
    registration_address VARCHAR(512),
    residential_address  VARCHAR(512),
    create_date          TIMESTAMP WITHOUT TIME ZONE,
    update_date          TIMESTAMP WITHOUT TIME ZONE,
    version              INTEGER,
    CONSTRAINT pk_clients PRIMARY KEY (id)
);