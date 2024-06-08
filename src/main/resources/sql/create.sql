create table customer
(
    id         bigint auto_increment primary key,
    name       varchar(255),
    surname    varchar(255),
    birth_date date,
    country    varchar(255)
);