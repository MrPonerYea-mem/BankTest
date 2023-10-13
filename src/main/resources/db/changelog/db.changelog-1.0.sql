--liquibase formatted sql

-- changeset mrponeryea:1
create sequence card_number_seq
    start with 10000000;

-- changeset mrponeryea:2
create table if not exists number_sequence
(
    id bigint not null
    primary key
);

-- changeset mrponeryea:3
create table if not exists account
(
    balance     numeric(38, 2),
    created_at  timestamp(6) with time zone,
                                 id          bigserial
                                 primary key,
                                 modified_at timestamp(6) with time zone,
                                 number_id   bigint
                                 unique
                                 constraint fkna50gmfjj49fpj3ot85axuhse
                                 references number_sequence,
                                 code        integer not null,
    name        varchar(255) not null
    unique
    );

-- changeset mrponeryea:4
create table if not exists transaction
(
    amount         numeric(38, 2),
    created_at     timestamp(6) with time zone,
                                    id             bigserial
                                    primary key,
                                    modified_at    timestamp(6) with time zone,
                                    from_account   varchar(255),
    to_account     varchar(255),
    type_operation varchar(255)
    constraint transaction_type_operation_check
    check ((type_operation)::text = ANY
((ARRAY ['DEPOSIT'::character varying, 'WITHDRAW'::character varying, 'TRANSFER'::character varying])::text[]))
    );