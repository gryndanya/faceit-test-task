CREATE TABLE IF NOT EXISTS test.cuisine_types
(
    id      BIGINT AUTO_INCREMENT      NOT NULL,
    name    VARCHAR(50)         UNIQUE NOT NULL,
    CONSTRAINT cuisine_type_pk PRIMARY KEY (id)
);

INSERT INTO test.cuisine_types(name) VALUES ( 'POLISH'), ('MEXICAN'), ('ITALIAN')