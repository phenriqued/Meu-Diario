CREATE TABLE tb_user(
     id BINARY(16) NOT NULL,
     nickname VARCHAR(20) NOT NULL UNIQUE,
     password VARCHAR(255) NOT NULL,
     phone_number VARCHAR(11) NOT NULL UNIQUE,
     created_at DATETIME NOT NULL,

      PRIMARY KEY(id)
);