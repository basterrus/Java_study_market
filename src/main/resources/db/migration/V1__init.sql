CREATE TABLE roles
(
    id   serial PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE users
(
    id         bigserial PRIMARY KEY,
    username   VARCHAR(255),
    password   VARCHAR(255),
    first_name VARCHAR(255),
    last_name  VARCHAR(255),
    email      VARCHAR(255)

);

CREATE TABLE users_roles
(
    user_id bigint NOT NULL REFERENCES users (id),
    role_id INT    NOT NULL REFERENCES roles (id),
    PRIMARY KEY (user_id, role_id)
);

CREATE TABLE categories
(
    id          bigserial PRIMARY KEY,
    name        VARCHAR(255),
    description VARCHAR(255)
);

CREATE TABLE products
(
    id          bigserial PRIMARY KEY,
    category_id INT REFERENCES categories (id),
    name        VARCHAR(255),
    description VARCHAR(255),
    quantity    INT,
    price       INT
);

CREATE TABLE orders
(
    id               bigserial PRIMARY KEY,
    user_id          VARCHAR(255) REFERENCES users (id),
    customer_name    VARCHAR(255),
    customer_phone   VARCHAR(255),
    customer_address VARCHAR(255),
    price            INT
);

CREATE TABLE order_items
(
    id         bigserial PRIMARY KEY,
    product_id bigint REFERENCES products (id),
    order_id   bigint REFERENCES orders (id),
    quantity   INT,
    price      INT
);

INSERT INTO roles(name)
VALUES ('ROLE_ADMIN'),
       ('ROLE_MANAGER'),
       ('ROLE_CUSTOMER');

insert into users (username, password, first_name, last_name, email)
values ('admin', 'Qwer1234', 'Mark', 'Zuckerberg', 'admin@study_market.com'),
       ('manager1', 'Qwer1234', 'Olaf', 'Snowmen', 'manager1@study_market.com'),
       ('user1', 'Qwer1234', 'Mike', 'Johnson', 'MikeJohnson@study_market.com');

INSERT INTO users_roles(user_id, role_id)
VALUES (1, 1),
       (3, 2);

INSERT INTO categories (name, description)
VALUES ('Процессоры', 'Категория "Процессоры"'),
       ('Материнские платы', 'Категория "Материнские платы"'),
       ('Оперативная память', 'Категория "Оперативная память"'),
       ('Жесткие диски', 'Категория "Жесткие диски"');

INSERT INTO products (name, category_id, description, quantity, price)
VALUES ('Процессор Intel Celeron G4900 OEM', 1, 'Celeron G4900', 5, 500),
       ('Процессор AMD FX-4300 BOX', 1, 'AMD FX-4300 BOX', 8, 800),
       ('Материнская плата Esonic G31CHL3', 2, 'Esonic G31CHL3', 8, 900),
       ('Материнская плата MSI H310M PRO-VDH', 2, 'MSI H310M PRO-VDH', 8, 1000),
       ('Оперативная память AMD Radeon R5 Entertainment Series [R532G1601U1S-U] 2 ГБ', 3, 'AMD Radeon R5 Entertainment Series [R532G1601U1S-U]', 8, 1200),
       ('Оперативная память QUMO [QUM4U-4G2666C19] 4 ГБ', 3, 'QUMO [QUM4U-4G2666C19] 4 ГБ', 8, 1400),
       ('0.5 ТБ Жесткий диск WD Black [WD5000LPSX]', 4, 'WD Black [WD5000LPSX]', 8, 1600),
       ('1 ТБ Жесткий диск Toshiba L200 Slim [HDWL110UZSVA]', 4, 'Toshiba L200 Slim [HDWL110UZSVA]', 8, 1800);
