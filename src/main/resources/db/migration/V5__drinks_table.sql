CREATE TABLE IF NOT EXISTS drinks
(
    id      BIGINT AUTO_INCREMENT NOT NULL,
    name    VARCHAR(50)           NOT NULL,
    price   DECIMAL(10, 2) 	      NOT NULL,
    ice     BOOLEAN   	          NOT NULL,
    lemon   BOOLEAN   	          NOT NULL,
    CONSTRAINT drinks_pk PRIMARY KEY (id)
);