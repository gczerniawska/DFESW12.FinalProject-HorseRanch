drop table if exists horses CASCADE;
CREATE TABLE horses (
    id BIGINT AUTO_INCREMENT,
    age INTEGER NOT NULL,
    breed VARCHAR(255),
    gender VARCHAR(255),
    name VARCHAR(255),
    PRIMARY KEY (id)
);