CREATE TABLE IF NOT EXISTS test.drinks
(
    id      BIGINT AUTO_INCREMENT NOT NULL,
    name    VARCHAR(50) UNIQUE    NOT NULL,
    price   DECIMAL(10, 2) 	      NOT NULL,
    CONSTRAINT drinks_pk PRIMARY KEY (id)
);

INSERT INTO test.drinks(name, price) VALUES ('water1', 310);
INSERT INTO test.drinks(name, price) VALUES ('water2', 320);
INSERT INTO test.drinks(name, price) VALUES ('water3', 330);