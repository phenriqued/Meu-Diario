CREATE TABLE tb_diary(
     id BIGINT AUTO_INCREMENT PRIMARY KEY,
     text TEXT NOT NULL,
     entry_date DATE NOT NULL,
     user_id BINARY(16) NOT NULL,
     CONSTRAINT fk_tb_diary_user FOREIGN KEY (user_id) REFERENCES tb_user(id)
);