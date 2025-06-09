ALTER TABLE tb_user
ADD COLUMN uuid_token_activation VARCHAR(6) UNIQUE,
ADD COLUMN uuid_token_expiration DATETIME,
ADD COLUMN is_enable BOOLEAN NOT NULL;