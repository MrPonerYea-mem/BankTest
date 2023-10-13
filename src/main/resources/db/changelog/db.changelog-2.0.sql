--liquibase formatted sql

-- changeset mrponeryea:1
create sequence CARD_NUMBER_SEQ
    start with 10000000;

-- changeset mrponeryea:2
create table NUMBER_SEQUENCE
(
    ID BIGINT not null
        primary key
);

-- changeset mrponeryea:3
create table ACCOUNT
(
    BALANCE     NUMERIC(38, 2),
    CODE        VARCHAR(255)                not null,
    CREATED_AT  TIMESTAMP WITH TIME ZONE,
    ID          BIGINT auto_increment
        primary key,
    MODIFIED_AT TIMESTAMP WITH TIME ZONE,
    NUMBER_ID   BIGINT
        unique,
    NAME        CHARACTER VARYING(255) not null
        unique,
    constraint FKNA50GMFJJ49FPJ3OT85AXUHSE
        foreign key (NUMBER_ID) references NUMBER_SEQUENCE
);

-- changeset mrponeryea:4
create table TRANSACTION
(
    AMOUNT         NUMERIC(38, 2),
    CREATED_AT     TIMESTAMP WITH TIME ZONE,
    ID             BIGINT auto_increment
        primary key,
    MODIFIED_AT    TIMESTAMP WITH TIME ZONE,
    FROM_ACCOUNT   BIGINT,
    TO_ACCOUNT     BIGINT,
    TYPE_OPERATION CHARACTER VARYING(255),
    check ("TYPE_OPERATION" IN ('DEPOSIT', 'WITHDRAW', 'TRANSFER'))
);

