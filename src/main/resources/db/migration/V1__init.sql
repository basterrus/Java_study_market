CREATE TABLE categories
(
    id         bigserial PRIMARY KEY,
    title      VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO categories (title)
VALUES ('Процессоры'),
       ('Материнские платы'),
       ('Оперативная память'),
       ('Жесткие диски');

CREATE TABLE products
(
    id          bigserial PRIMARY KEY,
    category_id bigint REFERENCES categories (id),
    title       VARCHAR(255),
    description VARCHAR(255),
    quantity    INT,
    price       INT,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO products (category_id, title, description, quantity, price)
VALUES (1, 'Процессор Intel Celeron G4900 OEM', 'Celeron G4900', 5, 500),
       (1, 'Процессор AMD FX-4300 BOX', 'AMD FX-4300 BOX', 8, 800),
       (2, 'Материнская плата Esonic G31CHL3', 'Esonic G31CHL3', 8, 900),
       (2, 'Материнская плата MSI H310M PRO-VDH', 'MSI H310M PRO-VDH', 8, 1000),
       (3, 'Оперативная память AMD Radeon R5 Entertainment Series [R532G1601U1S-U] 2 ГБ',
        'AMD Radeon R5 Entertainment Series [R532G1601U1S-U]', 8, 1200),
       (3, 'Оперативная память QUMO [QUM4U-4G2666C19] 4 ГБ', 'QUMO [QUM4U-4G2666C19] 4 ГБ', 8, 1400),
       (4, '0.5 ТБ Жесткий диск WD Black [WD5000LPSX]', 'WD Black [WD5000LPSX]', 8, 1600),
       (4, '1 ТБ Жесткий диск Toshiba L200 Slim [HDWL110UZSVA]', 'Toshiba L200 Slim [HDWL110UZSVA]', 8, 1800);

CREATE TABLE users
(
    id       bigserial PRIMARY KEY,
    username varchar(255) not null,
    password varchar(255) not null,
    email    varchar(255)
);

CREATE TABLE roles
(
    id   bigserial PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE users_roles
(
    user_id bigint NOT NULL REFERENCES users (id),
    role_id bigint NOT NULL REFERENCES roles (id),
    PRIMARY KEY (user_id, role_id)
);

INSERT INTO roles(name)
VALUES ('ROLE_ADMIN'),
       ('ROLE_MANAGER'),
       ('ROLE_CUSTOMER');

INSERT INTO users (username, password)
VALUES ('Bob', '$2y$10$8tK2L7s92T3wBEEVtkb3..560RXHsP9tPoO6iI6FNFOZ0BYlVeVY.'),
       ('John', '$2y$10$8tK2L7s92T3wBEEVtkb3..560RXHsP9tPoO6iI6FNFOZ0BYlVeVY.');

INSERT INTO users_roles(user_id, role_id)
VALUES (1, 1),
       (3, 2);


CREATE TABLE orders
(
    id          bigserial PRIMARY KEY,
    user_id     bigint        not null references users (id),
    total_price numeric(8, 2) not null,
    address     varchar(255),
    phone       varchar(255),
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE order_items
(
    id                BIGSERIAL PRIMARY KEY,
    product_id        BIGINT        NOT NULL REFERENCES products (id),
    order_id          bigint        NOT NULL REFERENCES orders (id),
    quantity          INT           NOT NULL,
    price_per_product NUMERIC(8, 2) NOT NULL,
    price             NUMERIC(8, 2) NOT NULL,
    created_at        TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at        TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
