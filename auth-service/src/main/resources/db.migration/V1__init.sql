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
       (2, 2);