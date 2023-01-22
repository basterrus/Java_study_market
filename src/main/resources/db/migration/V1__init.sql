CREATE TABLE users
(
    id         bigserial primary key,
    username   varchar(255),
    password   varchar(255),
    first_name varchar(255),
    last_name  varchar(255),
    email      varchar(255),
);

CREATE TABLE categories
(
    id bigserial primary key,
    name   varchar(255),
)

CREATE TABLE products
(
    id bigserial primary key,
    name   varchar(255),
)