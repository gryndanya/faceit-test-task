CREATE TABLE IF NOT EXISTS cuisine_types
(
    id      BIGINT AUTO_INCREMENT      NOT NULL,
    name    VARCHAR(50)         UNIQUE NOT NULL,
    CONSTRAINT cuisine_type_pk PRIMARY KEY (id)
);

