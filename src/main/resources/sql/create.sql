create table customer
(
    id         bigint not null auto_increment primary key,
    name       varchar(255),
    surname    varchar(255),
    birth_date date,
    country    varchar(255),
    created_at  timestamp,
    updated_at  timestamp
);